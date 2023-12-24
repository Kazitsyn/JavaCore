package ru.geekbrains.homework3;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Freelancer programmer = new Freelancer("Matvei", 35, 1);
        Staffer staffer = new Staffer("Sergey", 34, 2);

        System.out.println(programmer.salary());
        System.out.println(programmer.toString());
        programmer.setPaymentRate(500);
        System.out.println(programmer.salary());
        System.out.println(staffer.salary());

        List<Worker> workerList = new ArrayList<>();
        workerList.add(programmer);
        workerList.add(staffer);

        WorkerGroupIterator workerGroup = new WorkerGroupIterator(workerList);

        for (WorkerGroupIterator it = workerGroup; it.hasNext(); ) {
            Worker item = it.next();
            System.out.println(item);
        }



    }
}
