package org.example;

import java.util.concurrent.*;

public class ex14 {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("tick " + System.currentTimeMillis());
        ScheduledFuture<?> h = ses.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
        ses.schedule(() -> { h.cancel(false); ses.shutdown(); }, 10, TimeUnit.SECONDS);
        ses.awaitTermination(15, TimeUnit.SECONDS);
        System.out.println("stopped");
    }
}
