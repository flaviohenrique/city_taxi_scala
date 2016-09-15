package services

import javax.inject.Inject

import forms.CreateMapForm
import libs.city_map.{CityMap, MapFile, Navigator}
import repositories.{MapBlockRespository, MapRepository}

import scala.concurrent.Await
import scala.concurrent.duration.Duration


class MapService @Inject()(mapRepository : MapRepository, blocksRepository : MapBlockRespository, mapFile : MapFile)() {
  def move(id: Int) = {
    val map = Await.result(mapRepository.getById(id), Duration.Inf)
    val blocks = Await.result(blocksRepository.getByMapId(id), Duration.Inf)



    val navigator = Navigator.create(map, blocks)

    new CityMap(navigator)
  }


  def create(form: CreateMapForm) = {
    val result = mapFile.read(form.name, form.file.get.ref.file)
    mapRepository.create(result._1, result._2)
  }
}

