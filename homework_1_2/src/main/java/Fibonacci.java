package main.java;

import java.util.Scanner;

public class Fibonacci {
    static void printFibonacci(int quantity) {
        if (quantity <= 0) {
            return;
        }
        System.out.println(0);
        if (quantity == 1) {

            return;
        }
        System.out.println(1);
        int first = 0;
        int second = 1;
        int temp;
        for (int i = 0; i < quantity - 2; ++i) {
            temp = first + second;
            first = second;
            second = temp;
            System.out.println(second);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter required quantity of Fibonacci numbers: ");
        String inp = scan.next();
        try {
            int num = Integer.parseInt(inp);
            printFibonacci(num);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");
        }
        scan.close();
    }
}