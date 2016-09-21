package week3

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ConsListSuite extends FunSuite {

  test("list creation") {
    assertResult(5) { new Cons[Int](5, new Nil[Int]).head }
    assertResult(10) { new Cons[Int](0, new Cons[Int](10, new Nil[Int])).tail.head }
  }

  test("nth returns nth element") {
    val testList = new Cons[Int](0, new Cons[Int](10, new Cons[Int](20, new Nil[Int])))
    assertResult(0) {testList.nth(0)}
    assertResult(10) {testList.nth(1)}
    assertResult(20) {testList.nth(2)}

    assertThrows[IndexOutOfBoundsException] { // Result type: Assertion
      testList.nth(3)
    }
  }
}
