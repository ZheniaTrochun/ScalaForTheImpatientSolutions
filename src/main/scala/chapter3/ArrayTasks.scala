package chapter3

import java.util.TimeZone
import java.awt.datatransfer._

import scala.collection.mutable.ArrayBuffer

object ArrayTasks {

  def randomArray(n: Int): Array[Int] = {
    val buf = ArrayBuffer.empty[Int]

    for (i <- 0 to n)
      buf += (Math.random() * n).toInt

    buf.toArray
  }

  def swap(arr: Array[Int]): Unit = {
    for (i <- 1 until arr.length if i % 2 == 1) {
      val tmp = arr(i-1)
      arr(i-1) = arr(i)
      arr(i) = tmp
    }
  }

  def swapWitCreation(arr: Array[Int]): Array[Int] = {
    (for (i <- 1 until arr.length; j <- ((i - 1) to i).reverse if i % 2 == 1)
      yield arr(j)).toArray
  }

  def sortBySigma(arr: Array[Int]): Array[Int] =
    Array.concat(arr.filter(_ > 0), arr.filter(_ == 0), arr.filter(_ < 0))

  def calculateAverage(arr: Array[Double]): Double = arr.sum / arr.length
  def calculateAverage(arr: Array[Int]): Double = arr.sum / arr.length.toDouble

  def sortReverse(arr: Array[Int]): Array[Int] = arr.sorted.reverse

  def sortReverse(arr: ArrayBuffer[Int]): Array[Int] = arr.sorted.reverse.toArray

  def printVector(arr: Array[_ <: Any]): Unit = for (i <- arr) println(i)

  def removeDuplicates(arr: Array[_ <: Any]): Array[_ <: Any] = arr.distinct

  def getAmericanTimeZones:Array[String] = {
    TimeZone.getAvailableIDs.filter(_.contains("America")).map(_.replace("America/", "")).sorted
  }

  def lastStrangeTask():ArrayBuffer[Any] = {
    val flavors = SystemFlavorMap.getDefaultFlavorMap
                                  .asInstanceOf[SystemFlavorMap]
                                  .getNativesForFlavor(DataFlavor.imageFlavor)

    ArrayBuffer(flavors)
  }

  def main(args: Array[String]): Unit = {
    val arr = randomArray(5)

    for (i <- arr)
      println(i)

    println()
    swap(arr)

    for (i <- arr)
      println(i)

    println()
    val arrNew = swapWitCreation(arr)

    for (i <- arrNew)
      println(i)

    println()
    for (i <- sortBySigma(Array(-10, 1, -4, 0, 0, 9 , 4)))
      println(i)

    println()
    printVector(sortReverse(arr))

    println()
    printVector(removeDuplicates(arr))

    println()
    printVector(arr)

    println()
    printVector(getAmericanTimeZones)
  }

}
