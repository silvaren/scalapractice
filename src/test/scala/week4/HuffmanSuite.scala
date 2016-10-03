package week4

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import week4.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("times should return ocurrence count") {
    val input = List('a', 'b', 'a')
    assert(times(input).sortBy(pair => pair._2) === List(('b', 1), ('a', 2)))
  }

  test("codetree from a list of chars") {
    new TestTrees {
      assert(createCodeTree(List('a','b','a','b','b')) === t1)
      assert(createCodeTree(List('a','d','b','d','a','d','b','d','b')) === t2)
    }
  }

  test("decode from tree and list of bits") {
    new TestTrees {
      assert(decode(t1,List(1,0,1,0,0,1)) === List('b','a','b','a','a','b'))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("codebits should return bits for a char") {
    val table = List(('a',List(0)),('b',List(1)))
    assert(codeBits(table)('a') === List(0))
    assert(codeBits(table)('b') === List(1))
  }

  test("convert should convert codetree to codetable") {
    new TestTrees {
      assert(convert(t1) === List(('a',List(0)),('b',List(1))))
      assert(convert(t2) === List(('a',List(0,0)),('b',List(0,1)),('d',List(1))))
    }
  }

  test("quickencode should encode a tree with codetable") {
    new TestTrees {
      assert(quickEncode(t1)(List('b','a','b','b')) === List(1,0,1,1))
      assert(quickEncode(t2)(List('b','a','b','b','d')) === List(0,1,0,0,0,1,0,1,1))
    }
  }

  test("decodedSecret contains the secret message") {
    assert(decodedSecret === List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
  }

}
