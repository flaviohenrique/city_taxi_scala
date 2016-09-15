package libs.city_map

import libs.city_map.navigation.{Graph, Node}

class Navigator(val graph: Graph) {

  def taxiDistance(taxi : Node, passenger : Node) : Option[(Int, Node)] = {
    graph.shortestPath(taxi, passenger) match {
      case Some(list) => Some(list.size, taxi)
      case _ => None
    }
  }

  def nextNode(src : Node, dest : Node) : Option[Node] = {
    graph.shortestPath(src, dest).get match {
      case (first :: second :: _) => Some(second)
      case _ => None
    }
  }

  def nearestTaxi(taxis : Seq[Node], passenger: Node) = {
    taxis.flatMap(taxi => taxiDistance(taxi,passenger))
      .reduce(nearestTaxiDistance)._2
  }

  private def nearestTaxiDistance(x: (Int, Node), y: (Int, Node)) = {
    if (x._1 < y._1) x else y
  }
}

object Navigator{

  def create(map : models.Map, blocks : Seq[models.MapBlock]) = {
    val graph = new Graph()

    val blockedNodes = blocks.map{ b => Node(b.row, b.col) }

    (0 until map.rows).foreach(row => (0 until map.cols).foreach(col => {
      graph.addNode(row, col)
    }))

    val nodes = graph.getNodes

    nodes.keys.foreach(node => {
      if(! blockedNodes.contains(node)){
        var siblings = List[Node]()

        if (node.col > 0) siblings = Node(node.row, node.col -1 ) :: siblings
        if (node.row > 0) siblings = Node(node.row - 1, node.col) :: siblings

        siblings.foreach( sibling => {
          if(nodes.contains(sibling) && !blockedNodes.contains(sibling)){
            graph.addMutuallyEdge(node, sibling)
          }
        })

      }
    })

    new Navigator(graph)
  }
}