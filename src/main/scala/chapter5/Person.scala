package chapter5

class Person(_name: String) {
  val (firstName, lastName) = _name.split(" ") match {
    case Array(x: String, y: String, _*) => (x, y)
    case _ => (null, null)
  }
}
