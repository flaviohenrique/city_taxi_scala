package controllers

import javax.inject.Inject

import forms.CreatePassengerForm
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._
import services.PassengerService

import scala.concurrent.{ExecutionContext, Future}

class PassengerController @Inject() (passengerService: PassengerService, val messagesApi: MessagesApi)
                               (implicit ec: ExecutionContext)
  extends Controller with I18nSupport {

  def create = Action.async {
    implicit request =>
      CreatePassengerForm.bindFromRequest.fold(
        formWithErrors => {
          Future.successful(BadRequest(formWithErrors.errorsAsJson))
        },
        form => {
          passengerService.create(form).map(t => Ok(Json.toJson(t)))
        }
      )
  }
}