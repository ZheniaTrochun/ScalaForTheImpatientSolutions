package chapter5

class Counter {
  private var value = 0

  def increment(): Unit = { value = if(value == Int.MaxValue) value else value + 1}

  def current(): Int = value
}
