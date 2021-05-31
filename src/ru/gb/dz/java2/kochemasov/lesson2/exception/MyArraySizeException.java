package ru.gb.dz.java2.kochemasov.lesson2.exception;

public class MyArraySizeException extends RuntimeException{
    public MyArraySizeException() {
        super("Массив должен быть строго размером 4х4!");
    }
}
