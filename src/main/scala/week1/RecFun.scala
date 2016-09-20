package week1

object RecFun {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0 || r < c) 0
    else if(c == 0 || r == 0) 1
    else pascal(c-1,r-1) + pascal(c, r-1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def parensValue(char: Char): Int = char match {
      case '(' => 1
      case ')' => -1
      case _ => 0
    }

    def balanceValue(chars: List[Char], currentSum: Int): Int = {
      if (currentSum < 0) -1
      else if (chars.isEmpty) currentSum
      else balanceValue(chars.tail, currentSum + parensValue(chars.head))
    }
    balanceValue(chars, 0) == 0
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    var sum = 0;
    coins.foreach(x =>
      if (money > x) sum += countChange(money - x, coins.filter(z => z >= x))
      else if (money == x) sum += 1)
    sum
  }
}
