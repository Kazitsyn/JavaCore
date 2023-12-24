package ru.geekbrains.homework3;

import java.util.Collection;

public abstract class Worker implements Employee{
    protected String  name;
    protected int age;
    protected int experience;
    protected float paymentRate;

    protected float salary(){
        return this.paymentRate*(100+experience)/100;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", experience=" + experience +
                ", salary=" + salary() +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    public float getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(float paymentRate) {
        this.paymentRate = paymentRate;
    }
}
