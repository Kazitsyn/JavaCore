package ru.geekbrains.homework4;

public class Transaction {
    private Account src;
    private Account out;


    public Transaction(Account src, Account out, double balance) throws TransactionException {
        this.src = src;
        this.out = out;
        transaction(balance);
    }

    private void transaction(double balance) throws TransactionException{
        try {
            src.withdraw(balance);
            out.addBalance(balance);
        }
        catch (InsufficientFundsException e){
            throw new TransactionException("Ошибка транзакции: " + e.getMessage());
        }
        catch (IllegalArgumentException e){
            throw new TransactionException("Ошибка транзакции: " + e.getMessage());
        }
    }
}
