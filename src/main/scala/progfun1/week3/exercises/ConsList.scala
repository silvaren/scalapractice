package progfun1.week3.exercises

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def nth(n: Int): T
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false

  override def nth(n: Int): T = {
    def loop(l: List[T], current: Int): T  =
      if (l.isEmpty) throw new IndexOutOfBoundsException
      else if (current == n) l.head
      else loop(l.tail, current + 1)

    loop(this, 0)
  }
}

class Nil[T] extends List[T] {
  override def isEmpty: Boolean = true

  override def head: T = throw new NoSuchElementException("Nil.head")

  override def tail: List[T] = throw new NoSuchElementException("Nil.tail")

  override def nth(n: Int): T = throw new NoSuchElementException("Nil.nth")
}