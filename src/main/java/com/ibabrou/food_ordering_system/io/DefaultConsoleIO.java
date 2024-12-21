package com.ibabrou.food_ordering_system.io;

import java.util.Scanner;

public class DefaultConsoleIO implements ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public int readInt() {
        return scanner.nextInt();
    }

    @Override
    public long readLong() {
        return scanner.nextLong();
    }
}