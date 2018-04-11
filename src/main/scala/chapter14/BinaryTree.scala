package chapter14

sealed abstract class BinaryTree

case class Leaf(value: Int) extends BinaryTree
case class Node(left: Option[BinaryTree], right: Option[BinaryTree]) extends BinaryTree

object Node {
  def apply(left: BinaryTree, right: BinaryTree): Node = new Node(Some(left), Some(right))
  def apply(left: BinaryTree): Node = new Node(Some(left), None)
}

object BinaryTree {
  def computeValue(tree: BinaryTree): Int = tree match {
    case Leaf(v) => v

    case Node(Some(l), Some(r)) => computeValue(l) + computeValue(r)

    case Node(Some(l), None) => computeValue(l)

    case Node(None, Some(r)) => computeValue(r)

    case _ => 0
  }
}