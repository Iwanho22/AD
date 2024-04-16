package ch.hslu.sw06.ex3;


import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SemaphoreTest {

    @Test
    public void testSemaphore_invalidPermit() {
        // act && assert
        assertThatThrownBy(() -> new Semaphore(-1, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Permit and limit must be greater than zero.");
    }

    @Test
    public void testSemaphore_invalidLimit_Limit0() {
        // act && assert
        assertThatThrownBy(() -> new Semaphore(-10, -10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Permit and limit must be greater than zero.");
    }

    @Test
    public void testSemaphore_invalidLimit_smallerThanPermit() {
        // act && assert
        assertThatThrownBy(() -> new Semaphore(5, 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Limit must be greater than permits.");
    }

    @Test
    public void testRelease_AmountHigherThanLimit_shouldThrowException() {
        // arrange
        var sema = new Semaphore(3,3);

        // act && assert
        assertThatThrownBy(sema::release)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Limit 3 reached.");
    }

    @Test
    public void testRelease_multiAmountHigherThanLimit_shouldThrowException() {
        // arrange
        var sema = new Semaphore(2,3);

        // act && assert
        assertThatThrownBy(() -> sema.release(5))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Limit 3 reached.");
    }

    @Test
    public void testAcquire_amountPermitsHigherThanLimit_shouldThrowException() {
        // arrange
        var sema = new Semaphore(3,3);

        // act && assert
        assertThatThrownBy(() -> sema.acquire(5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amount of permits cannot be greater than limit 3");
    }

    @Test
    public void testAcquire_open() throws InterruptedException {
        // arrange
        var sema = new Semaphore(1,1);
        var res = new AtomicBoolean(false);

        // act
        var thread = Thread.startVirtualThread(() -> {
            try {
                sema.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            res.set(true);
        });
        thread.join();

        // act
        assertThat(res).isTrue();
    }

    @Test
    public void testAcquire_multiple_open() throws InterruptedException {
        // arrange
        var sema = new Semaphore(3,3);
        var res = new AtomicBoolean(false);

        // act
        var thread = Thread.startVirtualThread(() -> {
            try {
                sema.acquire(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            res.set(true);
        });
        thread.join();

        // act
        assertThat(res).isTrue();
    }

    @Test
    public void testAcquire_wait() throws InterruptedException {
        // arrange
        var sema = new Semaphore(0,1);
        var res = new AtomicBoolean(false);

        // act
        var thread = Thread.startVirtualThread(() -> {
            try {
                sema.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            res.set(true);
        });


        // act
        assertThat(res).isFalse();
        sema.release();
        thread.join();
        assertThat(res).isTrue();
    }

    @Test
    public void testAcquire_multiple_shouldWait() throws InterruptedException {
        // arrange
        var sema = new Semaphore(0,5);
        var res = new AtomicBoolean(false);

        // act
        var thread = Thread.startVirtualThread(() -> {
            try {
                sema.acquire(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            res.set(true);
        });


        // act
        assertThat(res).isFalse();
        sema.release();
        assertThat(res).isFalse();
        sema.release(4);
        thread.join();
        assertThat(res).isTrue();
    }
}