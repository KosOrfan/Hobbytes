package com.example.ntinos.hobbytes;

/**
 * Created by ntinos on 7/5/2017.
 */

public class CheckedUtils {

    public static <T> T get(final CheckedSupplier<T> checkedSupplier) {
        try {
            return checkedSupplier.get();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public static void run(final CheckedRunnable runnable) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public interface CheckedSupplier<T> {

        T get() throws Throwable;
    }

    public interface CheckedRunnable {

        void run() throws Throwable;
    }
}
