package forms

import play.api.data.{Form}
import play.api.data.Forms._
import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{MultipartFormData, Request}

object CreateMapForm{
  def form(file : Option[FilePart[TemporaryFile]]) : Form[CreateMapForm] = Form {
    mapping(
      "name" -> nonEmptyText,
      "file" ->  ignored(file).verifying("error.required", _.isDefined)
    )(CreateMapForm.apply)(CreateMapForm.unapply)
  }

  def bindFromRequest(implicit request : Request[MultipartFormData[TemporaryFile]]) = {
    form(request.body.file("file")).bindFromRequest
  }
}

case class CreateMapForm(name: String,
                         file: Option[FilePart[TemporaryFile]])
