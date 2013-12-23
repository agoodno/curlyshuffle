package controllers

import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.i18n.Messages
import play.api.mvc._
import models._

object Items extends Controller {

  val itemForm = Form(
    mapping(
      "id" -> of[Long],
      "name" -> nonEmptyText,
      "size" -> nonEmptyText,
      "price" -> of[Double],
      "sold" -> of[Boolean]
    )(Item.apply)(Item.unapply)
  )

  def refreshDb = Action {
    ItemData()
    Ok(views.html.items.index(Item.findAll))
  }

  def index = Action {
    Ok(views.html.items.index(Item.findAll))
  }

  def add() = Action {
    val addForm = itemForm.fill(Item())
    Ok(views.html.items.add(addForm))
  }

  def create() = Action { implicit request =>
    itemForm.bindFromRequest.fold(
      formWithErrors => BadRequest("Oh noes, invalid submission!"),
      value => {
        Item.insert(value)
        Ok(views.html.items.index(Item.findAll))
      }
    )
  }

  def edit(id: Long) = Action { implicit request =>
    Item.find(id) match {
      case Some(existing) => {
        val editForm = itemForm.fill(existing)
        Ok(views.html.items.edit(id, editForm))
      }
      case _ => BadRequest("Couldn't find item %s".format(id))
    }
  }

  def update(id: Long) = Action { implicit request =>
    itemForm.bindFromRequest.fold(
      formWithErrors => BadRequest("Oh noes, invalid submission!"),
      value => {
        Item.update(value)
        Ok(views.html.items.index(Item.findAll))
      }
    )
  }

  def delete(id: Long) = Action { implicit request =>
    Item.delete(id)
    Ok(views.html.items.index(Item.findAll))
  }

  def sold(id: Long) = Action { implicit request =>
    Item.find(id) match {
      case Some(existing) => {
        Item.update(existing.copy(sold = true))
        Ok(views.html.items.index(Item.findAll))
      }
      case _ => BadRequest("Couldn't find item %s".format(id))
    }
  }

}
