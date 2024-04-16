package ch.hslu.sw06.ex1.alternative;

import java.util.concurrent.CountDownLatch;

public class DemoWaitPool {

    public static void main(String[] args) throws InterruptedException {
        var latch = new CountDownLatch(1);
        var task = new MyTask(latch);
        new Thread(task).start();
        Thread.sleep(1000);

        latch.countDown();
    }
}
