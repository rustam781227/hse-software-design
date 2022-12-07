package main.java;

import java.util.Scanner;

public class Factorial {
    static void getFactorial(int num) {
        if (num < 0) {
            return;
        }
        if (num == 0 || num == 1) {
            System.out.println(1);
            return;
        }
        int cur = 1;
        for (int i = 2; i <= num; ++i) {
            cur *= i;
        }
        System.out.println(cur);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter required factorial: ");
        String inp = scan.next();
        try {
            int num = Integer.parseInt(inp);
            getFactorial(num);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");
        }
        scan.close();
    }
}