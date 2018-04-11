package chapter6

class Point(var x: Int, var y: Int) {

}

object Point {
  def apply(x: Int, y: Int): Point = new Point(x, y)
}