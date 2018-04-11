package chapter15

object Annotations {
  def main(args: Array[String]): Unit = {
    val depecated = new DeprecatedClass[Int](123)

    depecated.method()
    depecated.depr
  }
}
