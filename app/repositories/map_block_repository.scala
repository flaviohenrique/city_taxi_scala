package repositories

import javax.inject.{Inject, Singleton}

import com.github.tototoshi.slick.PostgresJodaSupport._
import driver.api._
import models.{Map, MapBlock}
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class MapBlockTable(tag: Tag) extends Table[MapBlock](tag, "map_blocks") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def row = column[Int]("row")
  def col = column[Int]("col")
  def map_id = column[Int]("map_id")
  def created_at = column[DateTime]("created_at")
  def updated_at = column[DateTime]("updated_at")
  def map = foreignKey("map", map_id, TableQuery[MapTable])(_.id)

  def * = (id, row, col, map_id, created_at, updated_at) <> ((MapBlock.apply _).tupled, MapBlock.unapply)
}

@Singleton()
class MapBlockRespository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                              (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  private val map_blocks = TableQuery[MapBlockTable]

  def getByMapId(id: Int): Future[Seq[MapBlock]] = db.run {
    map_blocks.filter(_.map_id === id).result
  }
}