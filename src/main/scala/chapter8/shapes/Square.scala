package chapter8.shapes

class Square(val sx: Int, val sy: Int, val sWidth: Int) extends java.awt.Rectangle(sx, sy, sWidth, sWidth) {

  def this(width: Int) {
    this(0, 0, width)
  }

  def this() {
    this(0, 0, 0)
  }
}
