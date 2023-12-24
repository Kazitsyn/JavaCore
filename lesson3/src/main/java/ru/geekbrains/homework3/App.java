package ru.geekbrains.homework3;

public class App {
    public static void main(String[] args) {
        Freelancer programmer = new Freelancer("Matvei", 35, 1);
        Staffer staffer = new Staffer("Sergey", 34, 2);

        System.out.println(programmer.salary());
        System.out.println(programmer.toString());
        programmer.setPaymentRate(500);
        System.out.println(programmer.salary());
        System.out.println(staffer.salary());

        Worker arrWorker[] = new Worker[2];
        arrWorker[0] = programmer;
        arrWorker[1] = staffer;

        System.out.println(arrWorker[0].toString());
        System.out.println(arrWorker[1].toString());


    }
}
