package models

import org.joda.time.{DateTime, DateTimeZone}
import play.api.libs.json._

case class MapBlock(id: Int,
                    row: Int,
                    col: Int,
                    map_id: Int,
                    created_at: DateTime,
                    updated_at: DateTime)

object MapBlock {
  implicit val datetimeWrites = Writes.jodaDateWrites("yyyy-MM-dd'T'HH:mm:ss'Z'")
  implicit val mapBlockFormat = Json.format[MapBlock]

  def create(row: Int, col: Int, map_id: Int): MapBlock =
    new MapBlock(0, row, col, map_id, DateTime.now(DateTimeZone.UTC), DateTime.now(DateTimeZone.UTC))
}