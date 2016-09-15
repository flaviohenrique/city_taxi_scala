package models

import org.joda.time.{DateTime, DateTimeZone}
import play.api.libs.json._

case class Taxi(id: Int,
                name: String,
                row: Int,
                col: Int,
                map_id: Int,
                status: Int,
                created_at: DateTime,
                updated_at: DateTime)

object Taxi {
  implicit val datetimeWrites = Writes.jodaDateWrites("yyyy-MM-dd'T'HH:mm:ss'Z'")
  implicit val taxiFormat = Json.format[Taxi]

  def create(name: String,
            row: Int,
            col: Int,
            map_id: Int): Taxi = new Taxi(0, name, row, col, map_id, 0, DateTime.now(DateTimeZone.UTC), DateTime.now(DateTimeZone.UTC))
}
