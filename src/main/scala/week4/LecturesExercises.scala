package week4

import week3.exercises._

object LecturesExercises {

  def main[T](args: Array[String]) {
    val a: Array[NonEmptyIntSet] = Array(new NonEmptyIntSet()(1, EmptyIntSet, EmptyIntSet))
    val b: Array[IntSet] = a
    b(0) = EmptyIntSet
    val s: NonEmptyIntSet = a(0)
  }
}
