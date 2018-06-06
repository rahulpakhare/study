package com.study.java.visa;

import java.util.Scanner;

public class PowerFunction {

    public static void main(String[] args) {
        System.out.println("Enter number for power");
        Scanner scanner = new Scanner(System.in);
        int n , p;
        while(scanner.hasNext()) {
            n = scanner.nextInt();
            p = scanner.nextInt();
            try {
                power(n, p);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static int power (int n, int p) throws Exception {
        int result = 1;
        if (p == 0)
            return result;

        if (n < 0 || p < 0) {
            throw new Exception("n and p should be non-negative");
        }

        for (; p != 0; --p) {
            result *= n;
        }

        System.out.println(result);
        return result;
    }
}
