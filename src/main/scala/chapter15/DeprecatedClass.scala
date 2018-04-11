package chapter15

@deprecated
class DeprecatedClass[@deprecated T](@deprecated val deprecated: T) {
  @deprecated val depr = 0
  @deprecated def method(): Unit = {}

  def method(example: Int = 0): Unit = {}
}
