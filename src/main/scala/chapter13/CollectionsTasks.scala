package chapter13

import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.collection.immutable
import scala.collection.mutable.ListBuffer


object CollectionsTasks {

  def letterIndexMapFunctional(str: String): Map[Char, immutable.SortedSet[Int]] = {
    str.toCharArray.toSet.foldLeft(Map.empty[Char, immutable.SortedSet[Int]]) { (map, item) =>
      map + (item -> str.indices.filter(str(_) == item).to[immutable.SortedSet])
    }
  }


  def removeOdd(buffer: ListBuffer[Any]): ListBuffer[Any] = {
    for (i <- buffer.indices if i % 2 == 0) buffer.remove(i)

    buffer
  }

  def removeOddWithCopy[A](buffer: ListBuffer[A]): ListBuffer[A] = {
    (for (i <- buffer.indices if i % 2 == 1) yield buffer(i)).to[ListBuffer]
  }

  def removeOddWithCopy_1[A](buffer: ListBuffer[A]): ListBuffer[A] = {
    buffer.indices.foldLeft(ListBuffer.empty[A])((buf, ind) => if (ind % 2 == 1) buf += buffer(ind) else buf)
  }

  implicit class MapCorresponds(arr: Array[String]) {
    def correspondsMap(map: Map[String, Int]): Array[Int] = {
      arr.foldLeft(List.empty[Int])((acc, str) => map.get(str).fold(acc)(n => n :: acc)).toArray
    }
  }

  def mkString[A](arr: Array[A], func: (A) => String, delim: String = ""): String = {
    arr.foldLeft("")(_ + delim + func(_))
  }

  def paralellize(map: mutable.HashMap[Char, Int]): mutable.HashMap[Char, Int] = {
    map.par.aggregate(mutable.HashMap[Char, Int]())((m, e) => { m(e._1) = e._2 + 1; m }, _ ++: _)
  }

  def getMaxTimezonesArea(): String = {
    java.util.TimeZone.getAvailableIDs
      .groupBy(_.split("/")(0))
      .maxBy(_._2.length)._1
  }

  def main(args: Array[String]): Unit = {
    java.util.TimeZone.getAvailableIDs.foreach(println)

    println()

    println(getMaxTimezonesArea())

    val map = mutable.HashMap[Char, Int]('a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4)

    paralellize(map).foreach(println)
  }
}
