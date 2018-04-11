package chapter4

import java.util.Calendar

import chapter3.ArrayTasks

import scala.collection.mutable

object MapsAndTuplesTasks {
  def discount(products: Map[String, Double], discount: Int): Map[String, Double] =
    products.mapValues(_ * discount * 1.01)

  def printMap(map: Map[_ <: Any, _ <: Any]): Unit = for ((k, v) <- map) println(s"$k\t-\t$v")
  def printMap(map: mutable.Map[_ <: Any, _ <: Any]): Unit = for ((k, v) <- map) println(s"$k\t-\t$v")

  def linkedHashMapTest(): (mutable.Map[String, Int], mutable.Map[String, Int]) = {
    val linkedMap = mutable.LinkedHashMap(
      "Monday" -> Calendar.MONDAY,
      "Tuesday" -> Calendar.TUESDAY,
      "Wednesday" -> Calendar.WEDNESDAY,
      "Thursday" -> Calendar.THURSDAY,
      "Friday" -> Calendar.FRIDAY,
      "Saturday" -> Calendar.SATURDAY,
      "Sunday" -> Calendar.SUNDAY
    )

    val hashMap: mutable.HashMap[String, Int] = mutable.HashMap(
      "Monday" -> Calendar.MONDAY,
      "Tuesday" -> Calendar.TUESDAY,
      "Wednesday" -> Calendar.WEDNESDAY,
      "Thursday" -> Calendar.THURSDAY,
      "Friday" -> Calendar.FRIDAY,
      "Saturday" -> Calendar.SATURDAY,
      "Sunday" -> Calendar.SUNDAY
    )

    (linkedMap, hashMap)
  }

  def minMax(arr: Array[Int]): (Int, Int) = (arr.min, arr.max)

  def lteqgt(arr: Array[Int], v: Int): (Array[Int], Array[Int], Array[Int]) =
    (arr.filter(_ < v), arr.filter(_ == v), arr.filter(_ > v))


  def main(args: Array[String]): Unit = {
    val map = Map(("A", 12.0), ("B", 10.0), ("C", 1.0), ("D", 60.0))

    printMap(discount(map, 10))

    println()
    printMap(HistogramBuilder.build("src\\main\\scala\\chapter4\\MapsAndTuplesTasks.scala"))

    val maps = linkedHashMapTest()
    println()
    printMap(maps _1)
    println()
    printMap(maps _2)


    val arr = ArrayTasks.randomArray(20)
    val tuple = lteqgt(arr, ArrayTasks.calculateAverage(arr).toInt)
    println()
    ArrayTasks.printVector(tuple _1)
    println()
    ArrayTasks.printVector(tuple _2)
    println()
    ArrayTasks.printVector(tuple _3)


  }
}
