package ch.hslu.sw06.ex1;

public class DemoWaitPool {
    public static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        synchronized (LOCK) {
            var task = new MyTask(LOCK);
            new Thread(task).start();
            Thread.sleep(1000);

            LOCK.notifyAll();
        }
    }
}
