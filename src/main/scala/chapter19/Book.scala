package chapter19


case object Title
case object Author

class Book (var title: String = "", var author: String = "") {
  private var last: Any = null

  def set(obj: Title.type): this.type = {
    last = Title
    this
  }

  def set(obj: Author.type): this.type = {
    last = Author
    this
  }

  def to(str: String): this.type = {
    last match {
      case Title  => title = str
      case Author => author = str
    }
    this
  }

  override def toString = s"Book($title, $author)"
}
