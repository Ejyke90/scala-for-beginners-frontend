package com.example

import com.google.inject.AbstractModule
import play.api.ApplicationLoader.Context
import play.api.mvc.{ControllerComponents, EssentialFilter}
import play.api.{BuiltInComponentsFromContext, Environment, Mode}
import play.api.routing.Router
import play.core.DefaultWebCommands

class Module extends AbstractModule {
  override def configure(): Unit = {
    val context = Context.create(Environment.simple(mode = Mode.Dev))
    bind(classOf[ControllerComponents]).toInstance(new BuiltInComponentsFromContext(context) {
      override def router: Router = Router.empty
      override def httpFilters: Seq[EssentialFilter] = Seq.empty
    }.controllerComponents)
  }
}