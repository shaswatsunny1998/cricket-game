package com.game.cricket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String name = sc.nextLine("Enter Name");
        BigDecimal decimal = new BigDecimal((3%6)*0.1).setScale(1, BigDecimal.ROUND_FLOOR);
        System.out.println(decimal.doubleValue());
        System.out.println((3%6)*0.1);
    }
}
