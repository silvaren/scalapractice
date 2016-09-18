object ParenthesesBalancingApp {
  def main(args: Array[String]): Unit = {
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

    def balance(chars: List[Char]): Boolean = {
      balanceValue(chars, 0) == 0
    }

    def testBalance(string: String) = {
      println(balance(string.toList));
    }

    testBalance("(if (zero? x) max (/ 1 x))")
    testBalance("I told him (that it’s not (yet) done). (But he wasn’t listening)")
    testBalance(":-)")
    testBalance("))(")
    testBalance("(()()()())")
    testBalance("(((())))")
    testBalance("(()((())()))")
    testBalance("((((((()))")
    testBalance("()))")
    testBalance("(()()(()")
  }
}
