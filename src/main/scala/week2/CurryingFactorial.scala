package week2

object CurryingFactorial {
  def main(args: Array[String]): Unit = {
    def product(f: Int => Int)(a: Int, b: Int): Int = {
      if (a > b) 1
      else f(a) * product(f)(a+1,b)
    }

    def factorial(x: Int) = product(y => y)(1,x)
    println(product(x => x * x)(3,4))
    println(factorial(5))

    def generalizedOp(f: Int => Int, op: (Int, Int) => Int)(a: Int, b: Int): Int = {
      if (a > b) 1
      else op(f(a), generalizedOp(f, op)(a+1,b))
    }

    def factorial2(x: Int) = generalizedOp(y => y, (u,v) => u * v)(1,x)
    println(generalizedOp(x => x * x, (u,v) => u * v)(3,4))
    println(factorial2(5))
  }
}
