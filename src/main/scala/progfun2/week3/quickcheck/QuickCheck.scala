package progfun2.week3.quickcheck

import progfun2.week3.common._
import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

import scala.annotation.tailrec

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    x <- arbitrary[Int]
    h <- oneOf(const(empty), genHeap)
  } yield insert(x, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("minoftwo") = forAll { (a: Int, b: Int) =>
    val h = insert(a, empty)
    val h2 = insert(b, h)
    findMin(h2) == scala.math.min(a,b)
  }

  property("deleteMinElement") = forAll { a: Int =>
    val h = insert(a, empty)
    deleteMin(h) == empty
  }

  property("sortedMins") = forAll { h: H =>
    @tailrec
    def mins(h: H, minsList: List[Int]): List[Int] = {
      if (isEmpty(h)) minsList
      else {
        val theMin = findMin(h)
        mins(deleteMin(h), minsList :+ theMin)
      }
    }
    val theMins = mins(h, List())
    theMins == theMins.sorted
  }

  property("meldMin") = forAll { (h1: H, h2: H) =>
    findMin(meld(h1, h2)) == findMin(h1) || findMin(meld(h1, h2)) == findMin(h2)
  }

  property("associative meld") = forAll { (h:H, i:H, j:H) =>
    val a = meld(meld(h, i), j)
    val b = meld(h, meld(i, j))
    toList(a) == toList(b)
  }

  def toList(h:H):List[Int] = if (isEmpty(h)) Nil else findMin(h) :: toList(deleteMin(h))

}
