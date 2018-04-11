package chapter6

object MilesToKilometersConverter extends UnitConverter {
  def toKilometers(miles: Double): Double = miles * 1.6

  def toMiles(km: Double): Double = km / 1.6
}
