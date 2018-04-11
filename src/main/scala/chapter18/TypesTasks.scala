package chapter18

object TypesTasks {

  def middle[T](iterable: Iterable[T]): T = {
    iterable.drop(iterable.size / 2).head
  }

  def main(args: Array[String]): Unit = {
    println(middle("World"))
  }
}
