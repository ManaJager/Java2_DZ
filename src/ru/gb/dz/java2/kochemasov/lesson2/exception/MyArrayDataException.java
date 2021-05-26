package ru.gb.dz.java2.kochemasov.lesson2.exception;

public class MyArrayDataException extends NumberFormatException{
    public MyArrayDataException() {
        super("Массив должен содержать только цифры!");
    }
}
