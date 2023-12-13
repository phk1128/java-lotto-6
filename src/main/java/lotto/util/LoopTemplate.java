package lotto.util;

import java.util.function.Supplier;

public class LoopTemplate {

    private LoopTemplate() {
    }
    public static <T> T tryCatchTemplate(Supplier<T> callback) {
        while (true) {
            try {
                return callback.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void tryCatchTemplate(Runnable callback) {
        while (true) {
            try {
                callback.run();
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
