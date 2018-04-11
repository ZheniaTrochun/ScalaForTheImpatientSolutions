package chapter13

import scala.collection.mutable.ListBuffer

object Collections {
  def zipStringWithSetOfIndices(str: String): Map[Char, Set[Int]] = {
    str.toCharArray.toSet.foldLeft(Map.empty[Char, Set[Int]]) { (map, item) =>
      map + (item -> str.indices.filter(str(_) == item).toSet)
    }
  }

  def removeOdd[A](list: ListBuffer[A]): ListBuffer[A] =
    list.indices.foldLeft(ListBuffer.empty[A]) { (l: ListBuffer[A], i) => if (i % 2 == 0) l :+ list(i) else l }

  def removeOdd_iterator[A](list: ListBuffer[A]): ListBuffer[A] = {
    val iterator = list.iterator
    var i = false

    while (iterator.hasNext) {
      if (i)
        list.remove(list.indexOf(iterator.next()))

      i = !i
    }

    list
  }

  def main(args: Array[String]): Unit = {
    println(zipStringWithSetOfIndices("Mississippi"))

    println(removeOdd_iterator("Mississippi".to[ListBuffer]))
  }

  def bench(f: => Any): Long = {
    val start = System.currentTimeMillis()
    f
    System.currentTimeMillis() - start
  }
}
