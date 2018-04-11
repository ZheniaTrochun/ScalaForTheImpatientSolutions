package chapter12

object HigherOrderFunctions {

  def values(fun: (Int) => Int, low: Int, high: Int): Seq[(Int, Int)] =
    (low to high).map(item => item -> fun(item))


  def values_faster(fun: (Int) => Int, low: Int, high: Int): Seq[(Int, Int)] =
    for (i <- low to high) yield i -> fun(i)

  def max(x: Int, y: Int): Int = if (x > y) x else y

  def largestWithReduce(arr: Seq[Int]): Int = {
    arr.reduceLeft(max)
  }

  def factorial(n: Int) = (1 to n) reduceLeft(_ * _)

  def safeFactorial(n: Int) = (1 to n).foldLeft(1)(_ * _)

  def largest(fun: (Int) => Int, inputs: Seq[Int]) = inputs.reduceLeft((a, b) => max(a, fun(b)))

  def indexWithLargest(fun: (Int) => Int, inputs: Seq[Int]) = inputs.reduceLeft((a, b) => if (fun(a) > fun(b)) a else b)

  def adjustToPair(fun: (Int, Int) => Int): ((Int, Int)) => Int =
    (pair: (Int, Int)) => fun(pair._1, pair._2)

  def unless(condition: => Boolean)(block: => Unit) = {
    if (!condition) block
  }

  def unless1[A](condition: => Boolean)(block: => A) = {
    if (!condition) block else null
  }


  def main(args: Array[String]): Unit = {
    println(bench(values(x => x * x, -500000, 500000)))

    println(largestWithReduce(1 :: 2 :: 3 :: 51 :: 4 :: 2 :: Nil))

    println()
    println(factorial(5))
    println(safeFactorial(5))
    println(safeFactorial(-5))
    println()

    println(largest(x => 10 * x - x * x, 1 to 10))
    println(indexWithLargest(x => 10 * x - x * x, 1 to 10))

    println()
    println(adjustToPair(_ * _)((7, 6)))

    val sumOfPairs = (1 to 10) zip (11 to 20) map adjustToPair(_ + _) sum

    println(sumOfPairs)

    println(("a"::"aa"::"aaaa"::"aaa"::Nil).corresponds(1::2::4::3::Nil)(_.length == _))

    unless(false) {
      println("test unless: false - printed")
    }

    unless(true) {
      println("test unless: true - not printed")
    }
  }

  def benchTest(f: ((Int) => Int, Int, Int) => Any)(fun: (Int) => Int, low: Int, high: Int): Long = {
    val start = System.currentTimeMillis()
    f(fun, low, high)
    System.currentTimeMillis() - start
  }

  def bench(f: => Unit): Long = {
    val start = System.currentTimeMillis()
    f
    System.currentTimeMillis() - start
  }
}
