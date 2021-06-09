package ru.gb.dz.java2.kochemasov.lesson5;

public class MyRunnable implements Runnable {

    private final int delayMs;

    public MyRunnable(int delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread
                    .currentThread().getName() + ": " + i);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread
                .currentThread().getName() + " завершил работу");
    }
}
