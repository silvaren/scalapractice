
object PascalTriangleApp {
  def main(args: Array[String]): Unit = {
    def pascal(c: Int, r: Int): Int = {
      if (c < 0 || r < c) 0
      else if(c == 0 || r == 0) 1
      else pascal(c-1,r-1) + pascal(c, r-1)
    }
    println(pascal(0,2))
    println(pascal(1,2))
    println(pascal(1,3))
    println(pascal(2,4))
    println(pascal(3,4))
  }
}
