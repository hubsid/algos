package com.sidh.practice.algo.sequenceGenerator;

import com.sidh.practice.algo.sequenceGenerator.impl.FileObjectMapperSequenceSupplier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileObjectMapperSequenceSupplier sequenceSupplier = new FileObjectMapperSequenceSupplier();
        Generator generator = new Generator(sequenceSupplier, sequenceSupplier);
        String c;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            c = scanner.next();
            if (c.equals("g"))
                System.out.println(generator.generate());
            else if (c.equals("s"))
                System.out.println(sequenceSupplier.get());
            else if (c.equals("d")) {
                int id = scanner.nextInt();
                generator.delete(id);
            }
        }
    }
}
