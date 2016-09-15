package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.Files.TemporaryFile
import play.api.mvc.{MultipartFormData, Request}

object CreateTaxiForm {
  def form : Form[CreateTaxiForm] = Form{
    mapping(
      "name" -> nonEmptyText,
      "row" ->  number,
      "col" -> number,
      "map_id" -> number
    )(CreateTaxiForm.apply)(CreateTaxiForm.unapply)
  }

  def bindFromRequest(implicit request : Request[Any]) = {
    form.bindFromRequest
  }

}

case class CreateTaxiForm(name: String,
                          row: Int,
                          col: Int,
                          map_id: Int)
