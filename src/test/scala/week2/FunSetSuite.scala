package week2

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * This class is a test suite for the methods in object FunSets. To run
  * the test suite, you can either:
  *  - run the "test" command in the SBT console
  *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    *   val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */
  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val set2 = singletonSet(2)
    val set30 = singletonSet(30)
    val union2and30 = union(set2, set30)
    val union2and30and40 = union(union2and30, singletonSet(40))
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1") {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets {
      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains only all common elements of each set") {
    new TestSets {
      assert(contains(union2and30and40,40))
      assert(contains(intersect(union2and30and40,union2and30),2))
      assert(contains(intersect(union2and30and40,union2and30),30))
      assert(contains(intersect(union2and30and40,union2and30),40) === false)
    }
  }

  test("diff contains all elements of the first set not in the second set") {
    new TestSets {
      val diffShouldContainonly40 = diff(union2and30and40, union2and30)
      assert(contains(diffShouldContainonly40,40))
      assert(contains(diffShouldContainonly40,2) === false)
      assert(contains(diffShouldContainonly40,30) === false)
    }
  }

  test("filter all elements that the predicate evaluates to true") {
    new TestSets {
      assert(contains(filter(union2and30and40, x => x < 15),2))
      assert(contains(filter(union2and30and40, x => x < 15),30) === false)
      assert(contains(filter(union2and30and40, x => x < 15),40) === false)
      assert(contains(filter(union2and30and40, x => x < 35),2))
      assert(contains(filter(union2and30and40, x => x < 35),30))
      assert(contains(filter(union2and30and40, x => x < 35),40) === false)
    }
  }

  test("forall that the predicate is true for all elements") {
    new TestSets {
      assert(forall(union2and30and40, x => x == 2 || x == 30 || x == 40))
      assert(forall(union2and30and40, x => x == 2 || x == 30 || x == 31) === false)
      assert(forall(union2and30and40, x => x == 40) === false)
    }
  }

  test("exists one element in the set that the predicate is true") {
    new TestSets {
      assert(exists(union2and30and40, x=> x == 2))
      assert(exists(union2and30and40, x=> x == 30))
      assert(exists(union2and30and40, x=> x == 40))
      assert(exists(union2and30and40, x=> x == 3) === false)
    }
  }

  test("map the function to all elements of the set") {
    new TestSets {
      val timestwomap = map(union2and30and40, x=> x * 2)
      assert(contains(timestwomap,4))
      assert(contains(timestwomap,60))
      assert(contains(timestwomap,80))
      assert(contains(timestwomap,2) === false)
      assert(contains(timestwomap,30) === false)
      assert(contains(timestwomap,40) === false)
    }
  }
}
