package libs.city_map

import libs.city_map.navigation.{Graph, Node}


case class CityMapNode(blocked: Boolean, row: Int, col: Int, node : Node)
