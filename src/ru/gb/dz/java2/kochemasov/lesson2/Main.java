package ru.gb.dz.java2.kochemasov.lesson2;

import ru.gb.dz.java2.kochemasov.lesson2.exception.MyArrayDataException;
import ru.gb.dz.java2.kochemasov.lesson2.exception.MyArraySizeException;
import ru.gb.dz.java2.kochemasov.lesson2.massive.Massive;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Massive.checkMassive(Massive.massive));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}

