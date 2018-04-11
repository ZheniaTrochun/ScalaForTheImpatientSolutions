package chapter14

object PatternMatching {

  def swap[A](pair: (A, A)): (A, A) = pair match {
    case (a, b) => b -> a
  }

  def swapFirsTwo(arr: Array[Int]): Array[Int] = arr match {
    case Array(a, b, rest @ _*) =>
      (b +: a +: rest) toArray

    case _ =>
      arr
  }

  def sumListValues(list: List[Any]): Int = {
    recursiveSum(list)
  }

  def recursiveSum(element: Any): Int = element match {
    case list: List[Any] =>
      list.map(e => recursiveSum(e)).sum[Int]

    case number: Int =>
      number

    case _ => 0
  }

  def sumSome(list: List[Option[Int]]): Int = {
    list.map {
      case Some(v)  => v
      case None     => 0
    } sum
  }

  def compose(g: (Double) => Option[Double], f: (Double) => Option[Double]): (Double) => Option[Double] = {
    (x: Double) => f(x) match {
      case Some(fx)  => g(fx)
      case None         => None
    }
  }

  def main(args: Array[String]): Unit = {
    def list = (3 :: 8 :: Nil) :: 2 :: (5 :: Nil)

    println(sumListValues(list))

    val tree = Operation('+', Operation('*', Value(3), Value(8)), Operation('+', Value(2), Operation('-', Value(5))))
    EquationTree.eval(tree)


    println(sumSome(Some(1) :: Some(2) :: None :: Some(3) :: None :: Nil))

    def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
    def g(x: Double) = if (x >= 0) Some(Math.sqrt(x)) else None

    val h = compose(g, f)

    println(h(2))
    println(h(1))
    println(h(0))
  }
}
