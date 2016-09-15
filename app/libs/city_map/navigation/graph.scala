package libs.city_map.navigation

import scala.collection.mutable._

case class Node(row : Int, col : Int){
  override def toString() = s"$row x $col"
}

class Graph {
  private var nodes: Map[Node, List[Node]] = Map()

  def getEdges(node : Node) = nodes.get(node).getOrElse(List())

  def getNodes = nodes

  def addMutuallyEdge(node : Node, node2: Node) = {
    (addEdge(node, node2), addEdge(node2, node))
  }

  def addEdge(node: Node, node2: Node) = {
    addNode(node, node2 :: getEdges(node))
  }


  def addNode(row : Int, col : Int) : (Node, List[Node]) = {
    addNode(Node(row, col))
  }

  def addNode(node : Node) : (Node, List[Node]) = {
    addNode(node, List())
  }

  private def addNode(node : Node, edges : List[Node]) : (Node, List[Node]) = {
    val pair = node -> edges
    nodes += pair
    pair
  }

  def shortestPath(src : Node, dest : Node): Option[List[Node]] ={
    val visited = new HashSet[Node]
    val queued = new Queue[List[Node]]

    visited.add(src)
    queued.enqueue(List(src))

    while (! queued.isEmpty){
      val listNode = queued.dequeue()
      val currNode = listNode.head

      if (currNode == dest) return Some(listNode.reverse)

      getEdges(currNode).foreach(edgeNode => {
        if (! visited.contains(edgeNode)) {
          visited.add(edgeNode)
          queued.enqueue(edgeNode :: listNode)
        }
      })
    }
    None
  }
}

object Graph {}