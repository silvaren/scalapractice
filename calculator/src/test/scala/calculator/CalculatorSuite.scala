package calculator

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import org.scalatest._

import TweetLength.MaxTweetLength

@RunWith(classOf[JUnitRunner])
class CalculatorSuite extends FunSuite with ShouldMatchers {

  /******************
   ** TWEET LENGTH **
   ******************/

  def tweetLength(text: String): Int =
    text.codePointCount(0, text.length)

  test("tweetRemainingCharsCount with a constant signal") {
    val result = TweetLength.tweetRemainingCharsCount(Var("hello world"))
    assert(result() == MaxTweetLength - tweetLength("hello world"))

    val tooLong = "foo" * 200
    val result2 = TweetLength.tweetRemainingCharsCount(Var(tooLong))
    assert(result2() == MaxTweetLength - tweetLength(tooLong))
  }

  test("tweetRemainingCharsCount with a supplementary char") {
    val result = TweetLength.tweetRemainingCharsCount(Var("foo blabla \uD83D\uDCA9 bar"))
    assert(result() == MaxTweetLength - tweetLength("foo blabla \uD83D\uDCA9 bar"))
  }


  test("colorForRemainingCharsCount with a constant signal") {
    val resultGreen1 = TweetLength.colorForRemainingCharsCount(Var(52))
    assert(resultGreen1() == "green")
    val resultGreen2 = TweetLength.colorForRemainingCharsCount(Var(15))
    assert(resultGreen2() == "green")

    val resultOrange1 = TweetLength.colorForRemainingCharsCount(Var(12))
    assert(resultOrange1() == "orange")
    val resultOrange2 = TweetLength.colorForRemainingCharsCount(Var(0))
    assert(resultOrange2() == "orange")

    val resultRed1 = TweetLength.colorForRemainingCharsCount(Var(-1))
    assert(resultRed1() == "red")
    val resultRed2 = TweetLength.colorForRemainingCharsCount(Var(-5))
    assert(resultRed2() == "red")
  }

  test("computeDelta") {
    val delta = Polynomial.computeDelta(Var(1), Var(-2), Var(-3))
    assert(delta() == (-2.0 * -2.0) - (4.0 * -3))
  }

  test("computeSolutions with 2 solutions") {
    val solutions = Polynomial.computeSolutions(Var(1), Var(-2), Var(-3), Var(16))
    assert(solutions() == Set(-1.0, 3.0))
  }

  test("computeSolutions with 1 solution") {
    val solutions = Polynomial.computeSolutions(Var(1), Var(4), Var(4), Var(0))
    assert(solutions() == Set(-2.0))
  }

  test("computeSolutions with no real solution") {
    val solutions = Polynomial.computeSolutions(Var(2), Var(4), Var(4), Var(-64))
    assert(solutions() == Set())
  }

  test("eval") {
    val evaled = Calculator.eval(Plus(Literal(1), Ref("a")), Map("a" -> Var(Literal(2))))
    assert(evaled == 3)
  }

  test("computeValues") {
    val values = Calculator.computeValues(
      Map("a" -> Var(Literal(1)), "b" -> Var(Literal(2)), "c" -> Var(Plus(Ref("a"), Ref("b")))))
    val evaled = values.map{ case (name, signal) => (name, signal())}
    assert(evaled == Map("a" -> 1.0, "b" -> 2.0, "c" -> 3.0))
  }

}
