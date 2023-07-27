package com.urise.webapp.util;

public class DeadLock {
    public static void main(String[] args) {

        Object lock1 = new Object();
        Object lock2 = new Object();
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }
    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " waiting");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " waiting");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " start");
                }
            }
        }).start();
    }
}
