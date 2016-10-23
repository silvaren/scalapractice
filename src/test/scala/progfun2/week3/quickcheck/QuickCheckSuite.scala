package progfun2.week3.quickcheck

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers
import org.scalacheck.Arbitrary._
import org.scalacheck.{Prop, Properties}
import org.scalacheck.Prop._
import org.scalatest.exceptions.TestFailedException

object QuickCheckBinomialHeap extends QuickCheckHeap with BinomialHeap

@RunWith(classOf[JUnitRunner])
class QuickCheckSuite extends FunSuite with Checkers {
  def checkBogus(p: Properties) {
    var ok = false
    try {
      p.check()
    } catch {
      case e: TestFailedException =>
        ok = true
    }
    assert(ok, "A bogus heap should NOT satisfy all properties. Try to find the bug!")
  }

  test("Binomial heap satisfies properties.") {
    (new QuickCheckHeap with progfun2.week3.quickcheck.test.BinomialHeap).check()
  }

  test("Bogus (1) binomial heap does not satisfy properties.") {
    checkBogus(new QuickCheckHeap with progfun2.week3.quickcheck.test.Bogus1BinomialHeap)
  }

  test("Bogus (2) binomial heap does not satisfy properties.") {
    checkBogus(new QuickCheckHeap with progfun2.week3.quickcheck.test.Bogus2BinomialHeap)
  }

  test("Bogus (3) binomial heap does not satisfy properties.") {
    checkBogus(new QuickCheckHeap with progfun2.week3.quickcheck.test.Bogus3BinomialHeap)
  }

  test("Bogus (4) binomial heap does not satisfy properties.") {
    checkBogus(new QuickCheckHeap with progfun2.week3.quickcheck.test.Bogus4BinomialHeap)
  }

  test("Bogus (5) binomial heap does not satisfy properties.") {
    checkBogus(new QuickCheckHeap with progfun2.week3.quickcheck.test.Bogus5BinomialHeap)
  }
}
