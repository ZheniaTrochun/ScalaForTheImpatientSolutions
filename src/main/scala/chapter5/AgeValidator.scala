package chapter5

class AgeValidator(val name: String, _age:Int) {
  val age = if (_age < 0) 0 else _age
}
