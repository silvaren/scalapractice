package progfun1.week3.exercises

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import progfun1.week3.Nil

@RunWith(classOf[JUnitRunner])
class ConsListSuite extends FunSuite {

  test("list creation") {
    assertResult(5) { new Cons(5, new Nil).head }
    assertResult(10) { new Cons(0, new Cons(10, new Nil)).tail.head }
  }

  test("nth returns nth element") {
    val testList = new Cons(0, new Cons(10, new Cons(20, new Nil)))
    assertResult(0) {testList.nth(0)}
    assertResult(10) {testList.nth(1)}
    assertResult(20) {testList.nth(2)}

    assertThrows[IndexOutOfBoundsException] { // Result type: Assertion
      testList.nth(3)
    }

    assertThrows[IndexOutOfBoundsException] { // Result type: Assertion
      testList.nth(-1)
    }
  }
}
