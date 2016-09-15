package test

import fixtures.graphFixtures
import libs.city_map.Navigator
import libs.city_map.navigation.{Graph, Node}
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}


class NavigatorSpec extends Spec  with BeforeAndAfter
  with OneInstancePerTest {

  val graph = graphFixtures.default
  val taxi = Node(0,3)
  val subject = new Navigator(graph)

  describe("#taxiDistance"){
    it("should return some number of steps to destination"){
      val passenger = Node(2,3)
      subject.taxiDistance(taxi, passenger) shouldBe Some(3, taxi)
    }
    it("should return none when can't go to destination"){
      val passenger = Node(1,1)
      subject.taxiDistance(taxi, passenger) shouldBe None
    }
  }

  describe ("#nextNode"){
    it("should return the next node to destination path"){
      val passenger = Node(2,3)
      subject.nextNode(taxi, passenger) shouldBe Some(Node(1,3))
    }
  }

  describe("#nearestTaxi"){
    val taxi2 = Node(1,3)

    it("should return the nearest taxi to destination"){
      val passenger = Node(2,3)
      subject.nearestTaxi(List(taxi, taxi2), passenger) shouldBe taxi2
    }
  }

  //nearest_taxi(taxis : Seq[Node], passenger: Node)
}