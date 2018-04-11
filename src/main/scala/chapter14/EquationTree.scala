package chapter14

sealed trait EquationTree

case class Value(value: Int) extends EquationTree
case class Operation(char: Char, left: EquationTree, right: Option[EquationTree] = None) extends EquationTree

object Operation {
  def apply(char: Char, left: EquationTree, right: EquationTree): Operation = new Operation(char, left, Some(right))
}

object EquationTree {

  def eval(tree: EquationTree): Int = {
    val expr = recursiveConstruct(tree)

    val res = recursiveEval(tree)

    println(s"$expr = $res")

    res
  }

  private def recursiveConstruct(tree: EquationTree): String = tree match {
    case Value(v) =>
      s"$v"

    case Operation(oper, left, Some(right)) if oper == '+' || oper == '-' =>
      s"${recursiveConstruct(left)} $oper ${recursiveConstruct(right)}"

    case Operation(oper, left, Some(right)) =>
      s"(${recursiveConstruct(left)} $oper ${recursiveConstruct(right)})"

    case Operation('-', left, None) =>
      s"(-${recursiveConstruct(left)})"

    case _ => ""
  }

  def recursiveEval(tree: EquationTree): Int = tree match {
    case Value(v) =>
      v

    case Operation('+', left, Some(right)) =>
      recursiveEval(left) + recursiveEval(right)

    case Operation('-', left, Some(right)) =>
      recursiveEval(left) - recursiveEval(right)

    case Operation('*', left, Some(right)) =>
      recursiveEval(left) * recursiveEval(right)

    case Operation('/', left, Some(right)) =>
      recursiveEval(left) / recursiveEval(right)

    case Operation('-', left, None) =>
      - recursiveEval(left)

    case _ => 0
  }
}