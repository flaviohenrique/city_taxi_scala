package repositories

import javax.inject.{Inject, Singleton}

import com.github.tototoshi.slick.PostgresJodaSupport._
import models.{Map, MapBlock}
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import driver.api._

class MapTable(tag: Tag) extends Table[Map](tag, "maps") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def rows = column[Int]("rows")
  def cols = column[Int]("cols")
  def time = column[Int]("time")
  def created_at = column[DateTime]("created_at")
  def updated_at = column[DateTime]("updated_at")

  def * = (id, name, rows, cols, time, created_at, updated_at) <> ((Map.apply _).tupled, Map.unapply)
}

@Singleton()
class MapRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                               (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  private val maps = TableQuery[MapTable]
  private val map_blocks = TableQuery[MapBlockTable]

  def create(map : Map, blocks : Seq[MapBlock]) : Future[(Map, Seq[MapBlock])] = db.run{
    for {
      m <- createMap(map)
      b <- createMapBlocks(m, blocks)
    } yield (m, b)
  }

  private def createMap(map: Map) = {
    (maps returning maps.map(_.id) into ((m, id) => m.copy(id = id))) += map
  }

  private def createMapBlocks(map: Map, blocks : Seq[MapBlock]) = {
    (map_blocks returning map_blocks.map(_.id) into ((m, id) => m.copy(id = id))) ++=
      blocks.map( _.copy(map_id = map.id))
  }

  def getById(id: Int) : Future[Map] = db.run(maps.filter(_.id === id).result).map(_.head)
}
