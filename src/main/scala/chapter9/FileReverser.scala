package chapter9

import java.io.PrintWriter

import scala.io.Source

object FileReverser {

  def reverse(path: String): Unit = {
    val source = Source.fromFile(path)
    val arr = source.getLines.toArray
    source.close()

    val out = new PrintWriter(path)
    arr.reverse.foreach(out.println)
    out.close()
  }

  def tabsReplacement(path: String): Unit = {
    val source = Source.fromFile(path)
    val content = source.getLines.toArray
    source.close()

    val out = new PrintWriter(path)
    content.foreach(line => out.println(line.replaceAll("\t+", " ")) )
    out.close()
  }

  def printAllLonger(path: String, len: Int): Unit =
    Source.fromFile(path).mkString.split("[^\\w]").filter(_.length >= len).foreach(println)

  def readFloatsFromFile(path: String): Array[Double] =
    Source.fromFile(path).mkString.split("[^0-9.-]").map(_.toDouble)

  def readNonFloatsFromFile(path: String): Array[String] =
    Source.fromFile(path).mkString.split("[0-9.- \t\n]")

  def powersOfTwo(path: String): Unit = {
    val out = new PrintWriter(path)

    for (i <- 0 to 20) out.println("%7d\t\t%1.9f".format(Math.pow(2, i) toInt, Math.pow(2, -i)))

    out.close()
  }

  def main(args: Array[String]): Unit = {
    reverse("src/main/scala/chapter9/test.txt")

    tabsReplacement("src/main/scala/chapter9/test.txt")

    printAllLonger("src/main/scala/chapter9/fileReverser.scala", 12)

    powersOfTwo("src/main/scala/chapter9/powers.txt")
  }
}
