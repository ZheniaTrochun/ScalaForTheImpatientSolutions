package chapter18

class MutablePair[T] (override var first: T, override var second: T) extends Pair[T, T] {

  def this(pair: (T, T)) =
    this(pair _1, pair _2)

  override def swap(): this.type = {
    val tmp: T = first
    first = second
    second = tmp

    this
  }
}
