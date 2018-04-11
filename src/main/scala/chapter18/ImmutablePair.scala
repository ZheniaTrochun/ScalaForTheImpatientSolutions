package chapter18

class ImmutablePair[T, S] (override var first: T, override var second: S) extends Pair[T, S] {

  def this(pair: (T, S)) = {
    this(pair _1, pair _2)
  }

  override def swap(): ImmutablePair[S, T] =
    ImmutablePair(second, first)
}

object ImmutablePair {

  def apply[T, S](first: T, second: S): ImmutablePair[T, S] =
    new ImmutablePair(first, second)
}