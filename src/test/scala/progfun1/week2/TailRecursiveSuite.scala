package progfun1.week2

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TailRecursiveSuite extends FunSuite {

  import progfun1.week2.TailRecursiveSum._

  test("product returns product of the application of the f to parameters") {
    assert(sum(x => x * x)(3,5) === 50)
  }
}
