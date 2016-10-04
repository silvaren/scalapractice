package week6

object Exercises {

  def isPrime(n: Int): Boolean = (2 until n).forall(x => n % x > 0)
}
