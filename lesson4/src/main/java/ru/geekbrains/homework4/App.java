package ru.geekbrains.homework4;

import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("Создаем счет, введите первоначальный баланс");
            Account account = new Account(scanner.nextInt());
            System.out.println("Ваш баланс: "+account.getBalance());
            System.out.println("Добавьте на счет сумму: ");
            account.addBalance(scanner.nextInt());
            System.out.println("Ваш баланс: "+account.getBalance());
            System.out.println("Снять со счета: ");
            account.withdraw(scanner.nextInt());
            System.out.println("Ваш баланс: "+account.getBalance());
            System.out.println("Создаем второй счет, введите первоначальный баланс");
            Account account2 = new Account(scanner.nextInt());
            System.out.println("Ваш баланс второго счета: "+account2.getBalance());
            System.out.println("Переводим с первого счета на второй");
            Transaction transaction = new Transaction(account, account2, (double) scanner.nextInt());
            System.out.println("Ваш баланс первого счета: "+account.getBalance());
            System.out.println("Ваш баланс второго счета: "+account2.getBalance());


        } catch (InsufficientFundsException e){
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        } catch (TransactionException e) {
            System.err.println(e.getMessage());
        }


    }
}
