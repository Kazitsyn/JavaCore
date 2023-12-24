package ru.geekbrains.homework3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Freelancer extends Worker{

    public Freelancer(String name, int age, int experience) {
        super.age = age;
        super.name = name;
        super.experience = experience;
        super.paymentRate = 400;
    }

    @Override
    public float salary(){
        return this.paymentRate*20*8*(100+experience)/100;
    }




}
