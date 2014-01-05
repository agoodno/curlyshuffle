package controllers

import org.squeryl.PrimitiveTypeMode._
import play.api._
import play.api.mvc._
import models.Database

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }
  
  def initDb = Action {
    transaction {
      Database.drop
      Database.create
    }
    Redirect(routes.Application.index)
  }

}
