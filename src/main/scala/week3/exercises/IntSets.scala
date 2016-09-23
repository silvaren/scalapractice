package week3.exercises

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class NonEmptyIntSet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def incl(x: Int): IntSet =
    if (x < elem) new NonEmptyIntSet(elem, left incl x, right)
    else if (x > elem) new NonEmptyIntSet(elem, left, right incl x)
    else this

  override def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else x == elem

  override def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}

object EmptyIntSet extends IntSet {
  override def incl(x: Int): IntSet = new NonEmptyIntSet(x, EmptyIntSet, EmptyIntSet)

  override def contains(x: Int): Boolean = false

  override def union(other: IntSet): IntSet = other
}
