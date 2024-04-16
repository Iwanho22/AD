package ch.hslu.sw06.ex2;

public class Latch implements Sync{
    private boolean released;
    private final Object lock;

    public Latch() {
        this.lock = new Object();
    }

    @Override
    public void acquire() throws InterruptedException {
        while (!released) {
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    @Override
    public void release() {
        released = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
