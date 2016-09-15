package models

import org.joda.time.{DateTime, DateTimeZone}
import play.api.libs.json._

case class Passenger(id: Int,
                     name: String,
                     row: Int,
                     col: Int,
                     dest_row: Int,
                     dest_col: Int,
                     map_id: Int,
                     status: Int,
                     created_at: DateTime,
                     updated_at: DateTime)

object Passenger {
  implicit val datetimeWrites = Writes.jodaDateWrites("yyyy-MM-dd'T'HH:mm:ss'Z'")
  implicit val passengerFormat = Json.format[Passenger]

  def create(name: String, row: Int, col: Int, dest_row: Int, dest_col: Int, map_id: Int): Passenger = {
    new Passenger(0, name, row, col, dest_row, dest_col, map_id, 0, DateTime.now(DateTimeZone.UTC), DateTime.now(DateTimeZone.UTC))
  }
}
