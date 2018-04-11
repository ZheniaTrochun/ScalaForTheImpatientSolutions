package chapter6.enums

object PlayingCardSuits extends Enumeration {
  val First = Value("♣")
  val Second = Value("♦")
  val Third = Value("♥")
  val Fourth = Value("♠")

  def isRed(suit: PlayingCardSuits.Value): Boolean = {
    suit match {
      case First => false
      case Second => true
      case Third => true
      case Fourth => false
    }
  }
}
