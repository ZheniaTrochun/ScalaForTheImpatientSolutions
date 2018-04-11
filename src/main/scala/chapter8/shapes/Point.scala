package chapter8.shapes

class Point(val x: Double, val y: Double) {

  override def toString = s"($x, $y)"
}

class LabeledPoint(val label: String, override val x: Double, override val y: Double) extends Point(x, y) {

  override def toString = s"LabeledPoint($label, $x, $y)"
}