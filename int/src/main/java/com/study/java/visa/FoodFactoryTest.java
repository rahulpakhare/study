package com.study.java.visa;

public class FoodFactoryTest {

    public static void main(String args[]) {
        FoodFactory foodFactory = new FoodFactory();
        Food food1 = foodFactory.getFood("FastFood");
        Food food2 = foodFactory.getFood("Fruit");
        System.out.println("My name is " + food1.getClass().getName());
        System.out.println("My name is " + food2.getClass().getName());
        System.out.println("Our superclass is: " + food1.getClass().getSuperclass().getName());
        food1.serveFood();
        food1.serveFood();
    }
}
