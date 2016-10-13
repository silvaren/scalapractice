package progfun1.week2

object CurryingFactorial {
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a+1,b)
  }

  def factorial(x: Int) = product(y => y)(1,x)

  def generalizedOp(f: Int => Int, op: (Int, Int) => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else op(f(a), generalizedOp(f, op)(a+1,b))
  }

  def factorial2(x: Int) = generalizedOp(y => y, (u,v) => u * v)(1,x)
}
