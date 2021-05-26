package ru.gb.dz.java2.kochemasov.lesson2.massive;

import ru.gb.dz.java2.kochemasov.lesson2.exception.MyArrayDataException;
import ru.gb.dz.java2.kochemasov.lesson2.exception.MyArraySizeException;

public class Massive {
    public static final String[][] massive =
            {{"1", "2", "-4", "2"}, {"3", "2", "0", "6"}};
    //            {{"1", "2", "-4", "ы"},{"3", "2", "0", "6"}};
    //            {{"1", "2", "0.2", "2"},{"3", "2", "0", "6"}};
    //            {{"1", "2", "0,4", "2"},{"3", "2", "0", "6"}};

    public static int checkMassive(String[][] massive) {
        int sum = 0;
        if (massive.length != 2 || massive[0].length != 4) {
            throw new MyArraySizeException();
        } else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (massive[i][j].matches("(([-])?[0-9]+?)+")) {
                        sum += Integer.parseInt(massive[i][j]);
                    } else throw new MyArrayDataException();
                }
            }
        }
        return sum;
    }
}