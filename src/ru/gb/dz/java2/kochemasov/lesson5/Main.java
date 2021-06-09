package ru.gb.dz.java2.kochemasov.lesson5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) throws InterruptedException {
        method1();
        method2();
    }

    private static void method1() throws InterruptedException {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(1);
        }
        long a = System.currentTimeMillis();
        Thread thread = Calculate(arr, size);
        thread.start();
        thread.join();
        System.out.println("При однопоточной обработке потрачено " +
                (System.currentTimeMillis() - a) + " мс");
        System.out.println(a);
    }

    private static void method2() throws InterruptedException {
        float[] arr = new float[size];
        float[] a1 = new float[h], a2 = new float[h];
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(1);
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread thread1 = Calculate(a1, h);
        Thread thread2 = Calculate(a2, h);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("При двухпоточной обработке потрачено "
                + (System.currentTimeMillis() - a) + " мс");
        System.out.println(a);
    }

    private static Thread Calculate(float[] array, int massiveSize) {
        return new Thread(() -> {
            for (int i = 0; i < massiveSize; i++) {
                array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) *
                        Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
    }
}
