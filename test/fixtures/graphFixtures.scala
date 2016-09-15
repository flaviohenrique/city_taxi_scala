package fixtures

import libs.city_map.navigation.Graph

object graphFixtures {

  def default: Graph = {
    /* Test Map
     I, 2, 3, 4
     5, x, 7, 8
     9, 10,11, D
     */
    val graph = new Graph
    val node1  = graph.addNode(0,0)._1
    val node2  = graph.addNode(0,1)._1
    val node3  = graph.addNode(0,2)._1
    val node4  = graph.addNode(0,3)._1
    val node5  = graph.addNode(1,0)._1
    val node6  = graph.addNode(1,1)._1
    val node7  = graph.addNode(1,2)._1
    val node8  = graph.addNode(1,3)._1
    val node9  = graph.addNode(2,0)._1
    val node10 = graph.addNode(2,1)._1
    val node11 = graph.addNode(2,2)._1
    val node12 = graph.addNode(2,3)._1

    graph.addMutuallyEdge(node1, node2)
    graph.addMutuallyEdge(node3, node2)
    graph.addMutuallyEdge(node4, node3)
    graph.addMutuallyEdge(node5, node1)
    graph.addMutuallyEdge(node7, node3)
    graph.addMutuallyEdge(node8, node7)
    graph.addMutuallyEdge(node8, node4)
    graph.addMutuallyEdge(node9, node5)
    graph.addMutuallyEdge(node10, node9)
    graph.addMutuallyEdge(node11, node10)
    graph.addMutuallyEdge(node11, node7)
    graph.addMutuallyEdge(node12, node11)
    graph.addMutuallyEdge(node12, node8)

    graph
  }
}
