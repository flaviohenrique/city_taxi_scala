package repositories

import javax.inject.{Inject, Singleton}

import com.github.tototoshi.slick.PostgresJodaSupport._
import driver.api._
import models.Passenger
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class PassengerTable(tag: Tag) extends Table[Passenger](tag, "passengers") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def row = column[Int]("row")
  def col = column[Int]("col")
  def dest_row = column[Int]("dest_row")
  def dest_col = column[Int]("dest_col")
  def status = column[Int]("status")
  def created_at = column[DateTime]("created_at")
  def updated_at = column[DateTime]("updated_at")

  def map_id = column[Int]("map_id")
  def map = foreignKey("map", map_id, TableQuery[MapTable])(_.id)

  def * = (id, name, row, col, dest_row, dest_col, map_id, status, created_at, updated_at) <> ((Passenger.apply _).tupled, Passenger.unapply)
}

@Singleton()
class PassengerRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                                    (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  private val passengers = TableQuery[PassengerTable]

  def create(passenger : Passenger): Future[Passenger] = db.run {
    (passengers returning passengers.map(_.id) into ((p, id) => p.copy(id = id))) += passenger
  }
}
