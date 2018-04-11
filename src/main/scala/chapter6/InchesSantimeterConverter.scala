package chapter6

object InchesSantimeterConverter extends UnitConverter {
  def toSantimeters(inches: Double): Double = inches * 2.54

  def toInches(santimeters: Double): Double = santimeters / 2.54
}
