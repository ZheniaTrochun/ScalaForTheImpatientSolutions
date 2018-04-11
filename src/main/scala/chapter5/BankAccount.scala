package chapter5

class BankAccount {
  private var balance = 0

  def deposit(value: Int) { balance = if (value > 0) balance + value else balance }

  def withdraw(value: Int) { balance = if (value > 0) balance - value else balance }
}
