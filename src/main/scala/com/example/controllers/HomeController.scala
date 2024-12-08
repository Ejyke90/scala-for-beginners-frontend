package com.example.controllers

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}

import javax.inject._
import play.api.libs.json._
import play.api.mvc._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  case class Payload(firstPerson: String, secondPerson: String, thirdPerson: String, fourthPerson: String)
  implicit val payloadFormat: OFormat[Payload] = Json.format[Payload]

  def display: Action[AnyContent] = Action {
    val payload = Payload("John", "Jane", "Doe", "Smith")
    val json = Json.prettyPrint(Json.toJson(payload))
    Ok(json).as("application/json")
  }

  def listEndpoints: Action[AnyContent] = Action {
    val endpoints = List(
      "GET /display",
      "GET /listEndpoints"
    )
    Ok(endpoints.mkString("\n"))
  }

  // Define the route method
  def route: Route =
    path("display") {
      get {
        complete {
          val payload = Payload("John", "Jane", "Doe", "Smith")
          val json = Json.prettyPrint(Json.toJson(payload))
          HttpEntity(ContentTypes.`application/json`, json)
        }
      }
    } ~ path("listEndpoints") {
      get {
        complete {
          val endpoints = List(
            "GET /display",
            "GET /listEndpoints"
          )
          HttpEntity(ContentTypes.`text/plain(UTF-8)`, endpoints.mkString("\n"))
        }
      }
    }
}