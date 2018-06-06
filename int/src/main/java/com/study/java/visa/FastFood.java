package com.study.java.visa;

public class FastFood extends Food {
    public static final String name = "FastFood";

    @Override
    public void serveFood() {
        System.out.println("I'm serving " + name);
    }
}
