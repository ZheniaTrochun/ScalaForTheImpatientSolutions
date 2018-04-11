package chapter5

class Time(val hours: Int, val minutes: Int) {
  private[Time] var minutesAfterMidnight = hours * 60 + minutes

//  def before(other: Time): Boolean = other.hours > hours || (other.hours == hours && other.minutes > minutes)

  def before(other: Time): Boolean = other.minutesAfterMidnight > minutesAfterMidnight
}
