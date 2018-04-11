package chapter8.shapes

object Main {
  def main(args: Array[String]): Unit = {
    val labeledPoint = new LabeledPoint("Black Thursday", 1929, 230.07)

    println(labeledPoint.label)
  }
}
