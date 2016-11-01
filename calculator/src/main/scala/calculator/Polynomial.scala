package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
                   c: Signal[Double]): Signal[Double] = {
    Signal{b() * b() - 4.0 * a() * c()}
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
                       c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    if (delta() < 0) Signal{Set()}
    else Signal{
      Set(
        ( -b() + Math.sqrt(delta()) ) / ( 2.0 * a() ),
        ( -b() - Math.sqrt(delta()) ) / ( 2.0 * a() )
      )
    }
  }
}
