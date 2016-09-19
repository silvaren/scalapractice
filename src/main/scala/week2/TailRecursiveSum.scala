package week2

import scala.annotation.tailrec

object TailRecursiveSum {
  def main(args: Array[String]): Unit = {
    def sum(f: Int => Int)(a: Int, b: Int): Int = {
      @tailrec
      def loop(a: Int, acc: Int): Int = {
        if (a > b) acc
        else loop(a + 1, acc * f(a))
      }
      loop(a, 0)
    }

    println(sum(x => x * x)(3,5))
  }
}
