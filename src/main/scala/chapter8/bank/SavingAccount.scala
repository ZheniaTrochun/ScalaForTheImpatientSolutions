package chapter8.bank

class SavingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  private var interest = 0

  def earnMonthlyInterest(): Unit = { interest = 3 }

  override def deposit(amount: Double): Double = {
    if (interest != 0) {
      interest -= 1
      super.deposit(amount)

    } else
      super.deposit(amount - 1)
  }

  override def withdraw(amount: Double): Double = {
    if (interest != 0) {
      interest -= 1
      super.deposit(amount)

    } else
      super.deposit(amount - 1)
  }
}
