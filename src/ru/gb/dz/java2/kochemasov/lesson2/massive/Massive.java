package ru.gb.dz.java2.kochemasov.lesson2.massive;

import ru.gb.dz.java2.kochemasov.lesson2.exception.MyArrayDataException;
import ru.gb.dz.java2.kochemasov.lesson2.exception.MyArraySizeException;

public class Massive {
    public static final String[][] massive =
            {{"1", "2", "-4", "2"}, {"3", "2", "0", "6"}, {"-3", "45", "2", "-6"}, {"23", "3", "0", "-6"}};
    public static final int SIZE = 4;

    public static int checkMassive(String[][] massive) {
        int sum = 0;
        if (massive.length != SIZE || massive[0].length != SIZE) {
            throw new MyArraySizeException();
        } else {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (massive[i][j].matches("(([-])?[0-9]+?)+")) {
                        sum += Integer.parseInt(massive[i][j]);
                    } else throw new MyArrayDataException();
                }
            }
        }
        return sum;
    }
}