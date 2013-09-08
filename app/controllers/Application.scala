package controllers

import play.api.mvc._
import play.api.data.Forms._
import play.api.data.Form
import models.{PartyWeights, PartyWeight}

object Application extends Controller {

  val partyWeightsForm: Form[PartyWeights] = Form(
    mapping(
      "weights" -> seq(
        mapping(
          "party" -> nonEmptyText,
          "weight" -> number()
        )(PartyWeight.apply)(PartyWeight.unapply)
      )
    )(PartyWeights.apply)(PartyWeights.unapply)
  )

  def index = Action {
    val start: PartyWeights = PartyWeights(PartyWeights.findAll())
    Ok(views.html.index(partyWeightsForm.fill(start)))
  }

  def randomize() = Action {
    implicit request =>
      partyWeightsForm.bindFromRequest.fold(
        errors => BadRequest(views.html.index(errors)),
        contact => {
          if (contact.sum > 0) {
            val res = PartyWeights.randomize(contact.weights.toList)
            Ok(views.html.show(res))
          }
          else
            Redirect(routes.Application.index())
        }
      )
  }
}