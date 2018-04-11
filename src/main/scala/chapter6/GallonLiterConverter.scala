package chapter6

object GallonLiterConverter extends UnitConverter {
  def toGallons(liters: Double): Double = liters / 3.79

  def toLiters(gallons: Double): Double = gallons * 3.79
}
