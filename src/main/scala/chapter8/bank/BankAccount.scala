package chapter8.bank

class BankAccount(initialBalance: Double) {
  protected var balance: Double = initialBalance

  def deposit(amount: Double) = { balance += amount; balance }
  def withdraw(amount: Double) = { balance -= amount; balance }
}
