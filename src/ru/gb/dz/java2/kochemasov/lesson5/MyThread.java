package ru.gb.dz.java2.kochemasov.lesson5;

public class MyThread extends Thread{
    private final int delayMs;

    public MyThread(int delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
        }

    }


}
