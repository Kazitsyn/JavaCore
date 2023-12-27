package ru.geekbrains.homework4;

public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = examination(balance);
    }
    public Account(int balance)  {
        this.balance = examination(balance);
    }

    private double examination(double balance)  throws IllegalArgumentException{
        if (balance < 0){
            throw new IllegalArgumentException("Попытка создать счет с отрицательным начальным балансом");
        }else {
            return balance;
        }
    }


    public double getBalance() {
        return balance;
    }


    public void addBalance(double balance) throws IllegalArgumentException{
        if (balance < 0){
            throw new IllegalArgumentException("Отрицательное число");
        }else {
            this.balance += balance;
        }
    }

    public void  withdraw(double balance) throws InsufficientFundsException {
        if (this.balance - balance < 0){
            throw new InsufficientFundsException("Недостаточно средств");
        }else {
            this.balance -= balance;
        }
    }
}
