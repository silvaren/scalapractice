package week3


abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else x == elem

  override def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}

object EmptySet extends IntSet {
  override def incl(x: Int): IntSet = new NonEmpty(x, EmptySet, EmptySet)

  override def contains(x: Int): Boolean = false

  override def union(other: IntSet): Unit = other
}
