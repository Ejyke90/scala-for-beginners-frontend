package com.example

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import com.google.inject.Guice
import org.slf4j.LoggerFactory
import play.api.libs.json.{Json, Writes}
import com.example.controllers.HomeController

import scala.io.StdIn

object WebServer extends App {
  val logger = LoggerFactory.getLogger(this.getClass)

  implicit val system = ActorSystem(Behaviors.empty, "efficient-server-system")
  implicit val executionContext = system.executionContext

  // Define the Payload case class within the WebServer object
  case class Payload(firstPerson: String, secondPerson: String, thirdPerson: String, fourthPerson: String)
  implicit val payloadWrites: Writes[Payload] = Json.writes[Payload]

  // Create the Guice injector with the new module
  val injector = Guice.createInjector(new Module)

  // Get the HomeController instance from the injector
  val homeController = injector.getInstance(classOf[HomeController])

  // Define a custom exception handler
  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case ex: Exception =>
        logger.error("An error occurred: ", ex)
        complete(StatusCodes.InternalServerError, "There was an internal server error.")
    }

  val route =
    path("submit") {
      get {
        val payload = Payload("John", "Jane", "Doe", "Smith")
        logger.info(s"Displaying payload: $payload")
        complete(HttpEntity(ContentTypes.`application/json`, Json.prettyPrint(Json.toJson(payload))))
      }
    } ~ homeController.route

  val bindingFuture = Http().newServerAt("localhost", 8081).bind(route)

  logger.info("Server online at http://localhost:8081/submit\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete { _ =>
      logger.info("Server stopped")
      system.terminate()
    }
}