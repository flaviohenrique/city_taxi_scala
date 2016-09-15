package test

import fixtures.graphFixtures
import libs.city_map.navigation.{Graph, Node}
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}


class GraphSpec extends Spec  with BeforeAndAfter
                              with OneInstancePerTest {

  val graph = new Graph
  val node = Node(0,0)
  val node1 = Node(1,1)

  describe("with nodes"){
    val subject = graph

    describe("#addNode"){
      it("should add new Node"){
        subject.addNode(node) should equal (node, List())
      }
      it("should add new node by indexes"){
        subject.addNode(0,0) should equal (node, List())
      }
    }

    describe("#getNodes"){
      it("should return a list of nodes"){
        graph.addNode(node)
        graph.addNode(node1)

        subject.getNodes should equal (Map(node -> List(), node1 -> List()))
      }
    }
  }

  describe("with edges"){
    val subject = {
      graph.addNode(node)
      graph
    }

    describe("#addEdges") {
      it("should link two nodes"){
        subject.addEdge(node, node1) should equal (node, List(node1))
      }
    }

    describe("#addMutuallyEdge"){
      it("should link 'node' with 'node1' and 'node1' with 'node'"){
        val result = subject.addMutuallyEdge(node, node1)
        result._1 should equal (node, List(node1))
        result._2 should equal (node1, List(node))
      }
    }

    describe("#getEdges") {
      it("should be empty when node don't be in graph yet"){
        subject.getEdges(node1) shouldBe empty
      }

      it("should list node edges") {
        subject.addEdge(node, node1)
        subject.getEdges(node) should equal (List(node1))
      }
    }
  }

  describe("#shortestPath") {
    val subject = graphFixtures.default

    it("should return a list of nodes to go to destination"){
      val path = List(Node(0,0), Node(1,0), Node(2,0), Node(2,1), Node(2,2), Node(2,3))
      subject.shortestPath(Node(0,0), Node(2,3)) shouldBe Some(path)
    }

    it("should return none when cant go to destination"){
      subject.shortestPath(Node(0,0), Node(1,1)) shouldBe None
    }
  }
}