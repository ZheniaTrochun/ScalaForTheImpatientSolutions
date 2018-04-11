package chapter18

trait Pair[T, S] {
  var first: T
  var second: S

  def swap(): Pair[S, T]
}

object Pair {
  def swap[T, S](pair: Pair[T, S]): Pair[S, T] = {
    ImmutablePair(pair.second, pair.first)
  }
}
