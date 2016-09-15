package repositories

import javax.inject.{Inject, Singleton}

import com.github.tototoshi.slick.PostgresJodaSupport._
import models.Taxi
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import driver.api._

import scala.concurrent.{ExecutionContext, Future}

class TaxiTable(tag: Tag) extends Table[Taxi](tag, "taxis") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def row = column[Int]("row")
  def col = column[Int]("col")
  def map_id = column[Int]("map_id")
  def status = column[Int]("status")
  def created_at = column[DateTime]("created_at")
  def updated_at = column[DateTime]("updated_at")
  def map = foreignKey("map", map_id, TableQuery[MapTable])(_.id)

  def * = (id, name, row, col, map_id, status, created_at, updated_at) <> ((Taxi.apply _).tupled, Taxi.unapply)
}

@Singleton()
class TaxiRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                               (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {


  private val taxis = TableQuery[TaxiTable]

  def create(taxi : Taxi): Future[Taxi] = db.run {
    (taxis returning taxis.map(_.id) into ((t, id) => t.copy(id = id))) += taxi
  }

  def list(): Future[Seq[Taxi]] = db.run {
    taxis.result
  }
}
