object CountingChangeApp {
  def main(args: Array[String]): Unit = {
    def countChange(money: Int, coins: List[Int]): Int = {
      var sum = 0;
      coins.foreach(x =>
        if (money > x) sum += countChange(money - x, coins.filter(z => z >= x))
        else if (money == x) sum += 1)
      sum
    }

    println(countChange(5, List(1,2,3)))
  }
}
