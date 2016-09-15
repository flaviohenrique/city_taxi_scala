package libs.city_map

import java.io.File
import javax.inject.Singleton

import com.github.tototoshi.csv.CSVReader
import models.Map.create
import models.MapBlock

@Singleton()
class MapFile {
  def read(name : String, file : File) = {
    val block_char = "x"
    val reader = CSVReader.open(file)

    try {
      val csv = reader.all
      val blocks = for {
        (row, row_idx) <- csv.zipWithIndex
        (col, col_idx) <- row.zipWithIndex
        if col == block_char
      } yield {
        MapBlock.create(row_idx, col_idx, 0)
      }
      (create(name, csv.size, csv.head.size), blocks)
    } finally {
      reader.close()
    }
  }
}