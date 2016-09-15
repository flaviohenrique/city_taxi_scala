package controllers

import javax.inject.Inject

import forms.CreateTaxiForm
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import services.TaxiService

class TaxiController @Inject() (taxiService: TaxiService, val messagesApi: MessagesApi)
                               (implicit ec: ExecutionContext)
                               extends Controller with I18nSupport {

  def create = Action.async {
    implicit request =>
      CreateTaxiForm.bindFromRequest.fold(
        formWithErrors => {
          Future.successful(BadRequest(formWithErrors.errorsAsJson))
        },
        form => {
          taxiService.create(form).map(t => Ok(Json.toJson(t)))
        }
      )
  }

  def getTaxi(id: Int) = Action {
    implicit request => Ok
  }

  def getTaxis = Action {
    implicit request => Ok
  }
}