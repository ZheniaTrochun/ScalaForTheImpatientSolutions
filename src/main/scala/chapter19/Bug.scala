package chapter19

//trait Fluent
//case object and extends Fluent
//case object then extends Fluent
//case object around extends Fluent

case object around
case object then
case object show

class Bug(var position: Int = 0, var direction: Boolean = true) {

  def move(len: Int): this.type = {
    position = if (direction) position + len else position - len
    this
  }

  def turn(obj: around.type): this.type = {
    direction = !direction
    this
  }

  def and(obj: show.type): this.type = {
    print(s"$position ")
    this
  }

  def and(obj: then.type): this.type = {
    this
  }
}
