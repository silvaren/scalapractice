package progfun2.week3.quickcheck

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers
import org.scalacheck.Arbitrary._
import org.scalacheck.{Prop, Properties}
import org.scalacheck.Prop._
import org.scalacheck.Test.{Result, _}
import org.scalatest.exceptions.TestFailedException
import progfun2.week3.quickcheck.test.BinomialHeap

object QuickCheckBinomialHeap extends QuickCheckHeap with BinomialHeap

@RunWith(classOf[JUnitRunner])
class QuickCheckSuite extends FunSuite with Checkers {
  def checkBogus(p: Properties) {
    val results: Seq[(String, Result)] = resultsFromPropertiesChecks(p)
    val ok = !results.forall{case (s: String, result: Result) => result.status == Passed}
    assert(ok, "A bogus heap should NOT satisfy all properties. Try to find the bug!")
  }

  def resultsFromPropertiesChecks(p: Properties): Seq[(String, org.scalacheck.Test.Result)] = {
    val results: Seq[(String, Result)] = checkProperties(Parameters.default, p)
    results
  }

  def checkAllProperties(p: Properties)= {
    val results: Seq[(String, Result)] = resultsFromPropertiesChecks(p)
    assert(results.forall{case (s: String, result: Result) => result.status == Passed})
  }

  test("Binomial heap satisfies properties.") {
    checkAllProperties(new QuickCheckHeap with progfun2.week3.quickcheck.test.BinomialHeap)
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
