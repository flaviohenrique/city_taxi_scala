package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Request

object CreatePassengerForm {
  def form : Form[CreatePassengerForm] = Form{
    mapping(
      "name" -> nonEmptyText,
      "row" ->  number,
      "col" -> number,
      "dest_row" ->  number,
      "dest_col" -> number,
      "map_id" -> number
    )(CreatePassengerForm.apply)(CreatePassengerForm.unapply)
  }

  def bindFromRequest(implicit request : Request[Any]) = {
    form.bindFromRequest
  }
}

case class CreatePassengerForm(name: String,
                               row: Int,
                               col: Int,
                               dest_row: Int,
                               dest_col: Int,
                               map_id: Int)
