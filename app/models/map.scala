package models

import org.joda.time.{DateTime, DateTimeZone}
import play.api.libs.json._

case class Map(id: Int,
                name: String,
                rows: Int,
                cols: Int,
                time: Int,
                created_at: DateTime,
                updated_at: DateTime
              )

object Map {
  implicit val datetimeWrites = Writes.jodaDateWrites("yyyy-MM-dd'T'HH:mm:ss'Z'")
  implicit val mapFormat = Json.format[Map]

  def create(name: String, rows: Int, cols: Int): Map = {
    new Map(0, name, rows, cols, 0, DateTime.now(DateTimeZone.UTC), DateTime.now(DateTimeZone.UTC))
  }
}