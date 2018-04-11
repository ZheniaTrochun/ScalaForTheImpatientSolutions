package chapter12

import Math._

object HigherOrderFunctionsTasks {

  def values[T <: AnyVal](fun: (Int) => T, lower: Int, upper: Int): List[(Int, T)] = {
    lower to upper map (i => i -> fun(i)) toList
  }

  def valuesForComprehensive[T <: AnyVal](fun: (Int) => T, lower: Int, upper: Int): List[(Int, T)] = {
    (for (i <- lower to upper) yield i -> fun(i)) toList
  }

  def  largestWithReduceLeft[T <: AnyVal with Comparable[Any]](arr: Array[T]): T = {
    arr.reduceLeft((acc, el) => if (el.compareTo(acc) > 0) acc else el)
  }

  def  largestWithReduceLeft(arr: Array[Int]): Int = {
    arr.reduceLeft(Math.max)
  }

  def factorial(n: Int): BigInt = {
    1 to n reduce(_ * _)
  }

  def saveFactorial(n: Int): BigInt = {
    (1 to n).foldLeft(1)(_ * _)
  }

  def largest(fun: (Int) => Int, lower: Int, upper: Int): Int = {
    lower to upper map fun max
  }

  def largestAt(fun: (Int) => Int, inputs: Seq[Int]): Int = {
    inputs.reduceLeft((acc, cur) => if (fun(cur) > fun(acc)) cur else acc)
  }

  def adjustToPair[T, R](fun: (T, T) => R): ((T, T)) => R =
    (pair: (T, T)) => fun(pair _1, pair _2)

  implicit class Corresponder[A](arr1: Array[A]) {
    def correspondsFunc(arr2: Array[A]): ((A, A) => Boolean) => Boolean = {
      (func: (A, A) => Boolean) => {
        (for (i <- arr1.indices)
          yield func(arr1(i), arr2(i)))
          .forall(_ == true)
      }
    }
  }

  def unless[A](condition: Boolean)(block: => A) = {
    if (!condition) {
      block
    }
  }

  def main(args: Array[String]): Unit = {
    println(largest(x => 10*x - x * x, 1, 10))

    unless(false) {
      println("OK")
    }

    println(Array("HELLO", "WORLD").correspondsFunc(Array("hello", "world"))(_.equalsIgnoreCase(_)))
    println(Array("HELLO", "WORLD").correspondsFunc(Array("hello", "world1"))(_.equalsIgnoreCase(_)))
    println(Array("HELLO", "WORLD").correspondsFunc(Array("hello", "world", "1"))(_.equalsIgnoreCase(_)))
  }
}
