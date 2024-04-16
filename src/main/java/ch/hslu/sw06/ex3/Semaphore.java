package ch.hslu.sw06.ex3;

public class Semaphore {
    private final int limit;
    private final Object lock;
    private int sema;

    public Semaphore(int permits, int limit) {
        if (permits > limit) {
            throw new IllegalArgumentException("Limit must be greater than permits.");
        } else if (permits < 0) {
            throw new IllegalArgumentException("Permit and limit must be greater than zero.");
        }
        this.sema = permits;
        this.limit = limit;
        this.lock = new Object();
    }

    public void acquire(final int permits) throws InterruptedException {
        if (permits > limit) {
            throw new IllegalArgumentException("Amount of permits cannot be greater than limit " + limit);
        }
        synchronized (lock) {
            while (sema < permits) {
                lock.wait();
            }
            sema -= permits;
        }
    }

    public void acquire() throws InterruptedException {
        acquire(1);
    }

    public void release(final int permits) {
        synchronized (lock) {
            if (sema >= 0 && sema + permits <= limit) {
                lock.notifyAll();
                sema += permits;
            } else {
                throw new IllegalStateException("Limit " + limit + " reached.");
            }
        }
    }

    public void release() {
        release(1);
    }
}
