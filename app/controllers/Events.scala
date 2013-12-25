package controllers

import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.i18n.Messages
import play.api.mvc._
import models._

object Events extends Controller {

  val eventForm = Form(
    mapping(
      "id" -> of[Long],
      "description" -> nonEmptyText,
      "year" -> of[Int]
    )(Event.apply)(Event.unapply)
  )

  def refreshDb = Action {
    EventData()
    Redirect(routes.Events.index)
  }

  def index = Action {
    Ok(views.html.events.index(Event.findAll))
  }

  def add() = Action {
    val addForm = eventForm.fill(Event())
    Ok(views.html.events.add(addForm))
  }

  def create() = Action { implicit request =>
    eventForm.bindFromRequest.fold(
      formWithErrors => Ok(views.html.events.add(formWithErrors)),
      value => {
        Event.insert(value)
        Redirect(routes.Events.index)
      }
    )
  }

  def edit(id: Long) = Action { implicit request =>
    Event.find(id) match {
      case Some(existing) => {
        val editForm = eventForm.fill(existing)
        Ok(views.html.events.edit(id, editForm))
      }
      case _ => BadRequest("Couldn't find event %s".format(id))
    }
  }

  def update(id: Long) = Action { implicit request =>
    eventForm.bindFromRequest.fold(
      formWithErrors => Ok(views.html.events.edit(id, formWithErrors)),
      value => {
        Event.update(value)
        Redirect(routes.Events.index)
      }
    )
  }

  def delete(id: Long) = Action { implicit request =>
    Event.delete(id)
    Redirect(routes.Events.index)
  }

}
