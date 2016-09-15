package controllers

import javax.inject.Inject

import forms.CreateMapForm
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.MapService

import scala.concurrent.{ExecutionContext, Future}

class MapController @Inject() (mapService: MapService, val messagesApi: MessagesApi)
                              (implicit ec: ExecutionContext)
  extends Controller with I18nSupport {

  def create = Action.async(parse.multipartFormData) {
    implicit request => {

      CreateMapForm.bindFromRequest.fold(
        formWithErrors => {
          Future.successful(BadRequest(formWithErrors.errorsAsJson))
        },
        form => {
          mapService.create(form).map( m => Ok(Json.toJson(m._1)))
        }
      )
    }
  }

  def move(id: Int) = Action {
    implicit request => {
      val map = mapService.move(id)
      Ok(Json.toJson(map))
    }
  }

  def getMap(id: Int) = Action {
    implicit request => Ok
  }

  def getMaps = Action {
    implicit request => Ok
  }
}