package chapter8.items

import scala.collection.mutable.ArrayBuffer

class Bundle extends Item {
  override def price = items.map(_.price).sum

  override def description = "Bundle of: " + items.map(_.description).mkString(", ")

  private val items: ArrayBuffer[Item] = ArrayBuffer.empty[Item]

  def add(item: Item): Unit = items += item
}
