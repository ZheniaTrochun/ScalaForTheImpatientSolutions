package chapter5

class Car(val manufacturer: String, val model:String) {
  var license: String = ""
  private var _year: Int = -1

  def this(manufacturer: String, model: String, license: String) = {
    this(manufacturer, model)
    this.license = license
  }

  def this(manufacturer: String, model: String, year: Int) = {
    this(manufacturer, model)
    this._year = year
  }

  def this(manufacturer: String, model: String, license: String, year: Int) = {
    this(manufacturer, model)
    this.license = license
    this._year = year
  }

  def modelYear = _year
}
