package chapter16

import java.util.concurrent.Executors

import scala.math.BigInt
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.StdIn
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object FuturesTasks {

  def forToMap(): Unit = {
//    for (n1 <- Future { Thread.sleep(1000); 2 };
//         n2 <- Future { Thread.sleep(1000); 4 })
//      println(n1 + n2)

//    Future { Thread.sleep(1000); 2 } flatMap {el =>
//      Future { Thread.sleep(1000); 4 } map(el2 => el + el2)
//    } foreach println

    val n1 = Future { Thread.sleep(1000); 2 }
    val n2 = Future { Thread.sleep(1000); 4 }

    n1.flatMap(res => n2.map(_ + res)) foreach println
  }

  def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] = {
    (x: T) => f(x) flatMap { g(_) }
  }

//  def doInOrder[T, U, V](f: T => Future[U])(g: U => Future[V]): T => Future[V] = {
//    (x: T) => f(x) flatMap { g(_) }
//  }

  def doInOrder[T](seq: Seq[T => Future[T]]): T => Future[T] = {
    (x: T) => seq.foldLeft(Future.successful(x))((acc, f) => acc.flatMap(f))
  }

  def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] = {
//          for comprehensive way
//    (x: T) => for (fx <- f(x); gx <- g(x)) yield (fx, gx)

//        flatMap - map way
    (x: T) => {
      val fx = f(x)
      val gx = g(x)

      fx.flatMap(res => gx.map((res, _)))
    }
  }

  def traverseFutures[T](seq: Seq[Future[T]]): Future[Seq[T]] = {
    seq.foldLeft(Future.successful(Seq.empty[T]))((acc, res) => acc.flatMap(seq => res.map(seq :+ _)))
  }

  def repeat[T](action: => T, until: T => Boolean): Future[T] = {
    def repeater(prev: Future[T]): Future[T] = {
      prev.flatMap(res => {
        Future(until(res)).flatMap { cond =>
          if (!cond)
            repeater(Future(action))
          else
            Future.successful(res)}
      })
    }

    repeater(Future { action })
  }

  def primeCounter(n: Int): Future[Int] = {
    (1 to n).par
      .aggregate(Future.successful(0))((counter, curr) =>
        counter.map(c =>
          if (BigInt(curr).isProbablePrime(1))
            c + 1
          else
            c
        ), (a, b) =>
        a.flatMap(res =>
          b.map(res + _)
        )
      )
  }

  def countPrimes(n: Int): Future[Int] = {
    val counter = (start: Int, end: Int) =>
      (start to end).count(BigInt(_).isProbablePrime(1))

    val cores: Int = Runtime.getRuntime.availableProcessors

    val futures =
      0 until cores map { core =>
        Future { counter(n * core / cores + 1, n * (core + 1) / cores ) }
      }

    futures.fold(Future.successful(0))((acc, f) => acc.flatMap(res => f.map(_ + res)))
  }

  def timeLogger(implicit ec: ExecutionContext): Future[Long] = {
    Future { Thread.sleep(10000); println("10 seconds"); System.currentTimeMillis() }(ec)
  }

  def timeLoggerStarter(n: Int): Future[String] = {
    val pool = Executors.newCachedThreadPool()
    val ec: ExecutionContext = ExecutionContext.fromExecutor(pool)

    (1 to n)
      .map(_ => timeLogger(ec))
      .foldLeft(Future.successful(""))((acc, f) => acc.flatMap(res => f.map(t => s"$res\n$t")))
  }

  def main(args: Array[String]): Unit = {

//    Await.result(repeat(StdIn.readLine(), (in: String) => in == "secret"), Duration.Inf)
    println(Await.result(timeLoggerStarter(40), Duration.Inf))

//    println(Await.result(primeCounter(100500), Duration.Inf))
//    println(Await.result(countPrimes(100500), Duration.Inf))
  }
}
