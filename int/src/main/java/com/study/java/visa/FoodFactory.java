package com.study.java.visa;

public class FoodFactory {
    public Food getFood(String name) {
        if (name.equals(FastFood.name)) {
            return new FastFood();
        } else if (name.equals(Fruit.name)) {
            return new Fruit();
        }

        return null;
    }
}
