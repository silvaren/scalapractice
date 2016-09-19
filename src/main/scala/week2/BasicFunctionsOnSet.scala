package week2

import scala.annotation.tailrec

object BasicFunctionsOnSet {
  def main(args: Array[String]): Unit = {
    type Set = Int => Boolean
    def contains(s: Set, elem: Int): Boolean = s(elem)

    def singletonSet(a: Int): Set = x => x == a

    println("singletonSet tests")
    println(contains(singletonSet(2),2)) // true
    println(contains(singletonSet(3),2)) // false

    def multiSet(a: List[Set]): Set = x => a.exists(y => contains(y,x))

    val set2 = singletonSet(2)
    val set30 = singletonSet(30)
    val set2and30 = multiSet(List(set2, set30))

    println("multiset tests")
    println(contains(set2and30,2)) // true
    println(contains(set2and30,30)) // true
    println(contains(set2and30,3)) // false

    def union(a: Set, b: Set): Set = multiSet(List(a,b))
    def intersect(a: Set, b: Set): Set = x => contains(a,x) && contains(b,x)
    def diff(a: Set, b: Set): Set = x => contains(a,x) && !contains(b,x)

    val union2and30 = union(set2, set30)
    println("union tests")
    println(contains(union2and30,2)) // true
    println(contains(union2and30,30)) // true
    println(contains(union2and30,3)) // false

    val union2and30and40 = union(union2and30, singletonSet(40))
    println("intersect tests")
    println(contains(union2and30and40,40)) // true
    println(contains(intersect(union2and30and40,union2and30),2)) // true
    println(contains(intersect(union2and30and40,union2and30),30)) // true
    println(contains(intersect(union2and30and40,union2and30),40)) // false

    val diffShouldContainonly40 = diff(union2and30and40, union2and30)
    println("diff tests")
    println(contains(diffShouldContainonly40,40)) // true
    println(contains(diffShouldContainonly40,2)) // false
    println(contains(diffShouldContainonly40,30)) // false

    def filter(a: Set, p: Int => Boolean): Set = x => contains(a, x) && p(x)
    println("filter tests")
    println(contains(filter(union2and30and40, x => x < 15),2)) // true
    println(contains(filter(union2and30and40, x => x < 15),30)) // false
    println(contains(filter(union2and30and40, x => x < 15),40)) // false
    println(contains(filter(union2and30and40, x => x < 35),2)) // true
    println(contains(filter(union2and30and40, x => x < 35),30)) // true
    println(contains(filter(union2and30and40, x => x < 35),40)) // false

    def forall(s: Set, p: Int => Boolean): Boolean = {
      def inforall(s: Set, p: Int => Boolean, current: Int): Boolean = {
        if (current > 1000) true
        else if (contains(s, current) && !p(current)) false
        else inforall(s, p, current + 1)
      }
      inforall(s, p, -1000)
    }
    println("forall tests")
    println(forall(union2and30and40, x => x == 2 || x == 30 || x == 40)) // true
    println(forall(union2and30and40, x => x == 2 || x == 30 || x == 31)) // false
    println(forall(union2and30and40, x => x == 40)) // false

    def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x))
    println("exists tests")
    println(exists(union2and30and40, x=> x == 2)) // true
    println(exists(union2and30and40, x=> x == 30)) // true
    println(exists(union2and30and40, x=> x == 40)) // true
    println(exists(union2and30and40, x=> x == 3)) // false

    def map(s: Set, f: Int => Int): Set = x => exists(s, y => f(y) == x)

    println("map tests")
    val timestwomap = map(union2and30and40, x=> x * 2)
    println(contains(timestwomap,4)) // true
    println(contains(timestwomap,60)) // true
    println(contains(timestwomap,80)) // true
    println(contains(timestwomap,2)) // false
    println(contains(timestwomap,30)) // false
    println(contains(timestwomap,40)) // false
  }
}
