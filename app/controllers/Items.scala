package controllers

import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.mvc._
import models.Item

object Items extends Controller {

  def index = Action {
    Ok(views.html.items.index(Item.findAll))
  }

}
