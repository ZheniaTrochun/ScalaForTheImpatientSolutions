package chapter2

object recursivePowFunc {
  def recursivePow(x: Int, n: Int): Int = {
    if (n > 0) {
      if (n % 2 == 0)
        recursivePow(x, n / 2) * recursivePow(x, n / 2)
      else
        x * recursivePow(x, n - 1)
    } else {
      if (n == 0)
        1
      else
        1 / recursivePow(x, -n)
    }
  }

  def main(args: Array[String]): Unit = {
    println(recursivePow(2, 5))
  }
}
