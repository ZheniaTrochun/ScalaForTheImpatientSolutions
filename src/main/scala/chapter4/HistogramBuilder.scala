package chapter4

import java.io.File
import java.util.Scanner

import scala.collection.immutable.{ListMap, TreeMap}
import scala.collection.mutable

object HistogramBuilder {
  def build(path: String): Map[String, Int] = {
    val in = new Scanner(new File(path))

    var wordsArr: Array[String] = Array.empty[String]

    while (in.hasNext()) {
      wordsArr = Stream.concat(wordsArr.toStream, in.next().split("\\W").toStream).toArray
    }

    val histogram: mutable.Map[String, Int] = mutable.Map.empty[String, Int]

    wordsArr.foreach(word => {
      if (histogram.contains(word))
        histogram(word) += 1
      else
        histogram += (word -> 1)
    })

    TreeMap(histogram.toSeq :_*)
  }
}
