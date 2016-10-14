package progfun2.week1

object Exercises {

  case class Book(title: String, authors: List[String])

  def allBookTitlesThatHaveBirdAsAuthor(books: List[Book]): List[String]=
    for (b <- books; a <- b.authors if a.startsWith("Bird")) yield b.title

  def allBookTitlesThatHaveBirdAsAuthorHighOrder(books: List[Book]): List[String] =
    books.flatMap(
      b => b.authors.withFilter(a => a.startsWith("bird")).map(b.title)

}
