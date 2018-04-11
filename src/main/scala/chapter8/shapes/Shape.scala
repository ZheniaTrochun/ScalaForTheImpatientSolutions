package chapter8.shapes

abstract class Shape {
  def centerPoint: Point
}


class Rectangle(val topLeft: Point, val bottomRight: Point) extends Shape {
  override def centerPoint: Point =
    new LabeledPoint(s"Center of rect: $topLeft - $bottomRight", (topLeft.x - bottomRight.x) / 2, (bottomRight.y - topLeft.y) / 2)
}


class Sphere(val center: Point, val radius: Double) extends Shape {
  override def centerPoint: Point = new LabeledPoint(s"Center of sphere with radius: $radius", center.x, center.y)
}