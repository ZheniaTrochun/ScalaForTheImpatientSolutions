package chapter16

import scala.annotation.tailrec
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random

object Futures {

  def for2map() = {
//    was
//    for (n1 <- Future { Thread.sleep(1000); 2 };
//         n2 <- Future { Thread.sleep(1000); 40 })
//      println(n1 + n2)

//    equal
//    each works concurrently
//    println in Main thread
    val n1 = Future { Thread.sleep(1000); 2 }
    val n2 = Future { Thread.sleep(1000); 40 }

    println(n1.flatMap(res => n2.map(_ + res)))
  }

  def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] = {
    (x: T) => f(x) flatMap { g(_) }
  }

  def doSeqInOrder[T](funcs: Seq[T => Future[T]]): T => Future[T] = {
    (x: T) => funcs.foldLeft(Future.successful(x))((prev, f) => prev.flatMap(f))
  }

  def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] = {
    (x: T) => {
      val fx = f(x)
      val gx = g(x)

      fx.flatMap(f1 => gx.map(f2 => (f1, f2)))
    }
  }

  def doAllTogether[T](seq: Seq[Future[T]]): Future[Seq[T]] = {
    seq.foldLeft(Future.successful(Seq.empty[T]))((res, curr) => curr.flatMap(tmp => res.map(_ :+ tmp)))
  }

  def repeat[T](action: => T, until: => Boolean): Future[T] = {
    Future { until } flatMap { condition =>
      if (condition) {
        recursiveRepeat(action, until, Future { action })
      } else {
        Future.failed(new InterruptedException())
      }
    }
  }

  def recursiveRepeat[T](action: => T, until: => Boolean, prev: Future[T]): Future[T] = {
    Future { until } flatMap { condition =>
      if (!condition) {
        recursiveRepeat(action, until, Future { action })
      } else {
        prev
      }
    }
  }

  def main(args: Array[String]): Unit = {

    val n1 = (i: Int) => Future { Thread.sleep(1000); println(i); i }
    val n2 = (i: Int) => Future { Thread.sleep(2500); println(i + 1); i + 1 }
    val n3 = (i: Int) => Future { Thread.sleep(1000); println(i + 2); i + 2 }
    val n4 = (i: Int) => Future { Thread.sleep(1000); println(i + 3); i + 3 }


    val interval = BigInt.probablePrime(15, new Random())
    val p = interval / 4

    println(Await.result(doAllTogether(for (i <- 1 to 4) yield Future { ((i - 1) * p) to (i * p) sum }).map(seq => seq.sum), 10 seconds))

  }
}
