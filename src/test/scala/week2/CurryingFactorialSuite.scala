package week2

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CurryingFactorialSuite extends FunSuite {

  import week2.CurryingFactorial._

  test("product returns product of the application of the f to parameters") {
    assert(product(x => x * x)(3, 4) === 144)
  }

  test("factorial computes the factorial of a int") {
    assert(factorial(5) === 120)
  }

  test("generalized operation computes generic operation of the application of the f to parameters") {
    assert(generalizedOp(x => x * x, (u,v) => u * v)(3,4) === 144)
  }

  test("factorial2 computes the factorial of a int using the generalizedOp") {
    assert(factorial2(5) === 120)
  }

}
