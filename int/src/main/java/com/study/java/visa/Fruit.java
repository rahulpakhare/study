package com.study.java.visa;

public class Fruit extends Food {
    public static final String name = "Fruit";

    @Override
    public void serveFood() {
        System.out.println("I'm serving " + name);
    }
}
