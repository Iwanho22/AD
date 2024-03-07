package ch.hslu.sw02.ex4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RingBufferTest {

    @Test
    public void testOffer_sizeShouldIncrease() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(32);

        // act
        queue.offer(1);

        // assert
        assertThat(queue.size()).isEqualTo(1);
    }

    @Test
    public void testOffer_multipleTimes_sizeShouldIncrease() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(32);

        // act
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        // assert
        assertThat(queue.size()).isEqualTo(4);
    }

    @Test
    public void testPoll_sizeShouldDecrease() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(32);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        // act
        queue.poll();

        // assert
        assertThat(queue.size()).isEqualTo(3);
    }

    @Test
    public void testPoll_multipleTimes_sizeShouldDecrease() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(32);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        // act
        queue.poll();
        queue.poll();
        queue.poll();

        // assert
        assertThat(queue.size()).isEqualTo(1);
    }

    @Test
    public void testPoll_shouldReturnFirstElement() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(32);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        // act
        var res = queue.poll();

        // assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void testPoll_threeTimes_shouldReturnFirstThreeElements() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(32);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        // assert & assert
        assertThat(queue.poll()).isEqualTo(1);
        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.poll()).isEqualTo(3);
    }

    @Test
    public void testPeek_multipleTimes_sizeShouldStaySame() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(32);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        // act
        queue.peek();
        queue.peek();
        queue.peek();

        // assert
        assertThat(queue.size()).isEqualTo(4);
    }

    @Test
    public void testOffer_ringFunctionality_shouldStartABeginning() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.poll();

        // act
        queue.offer(5);


        // assert
        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.poll()).isEqualTo(3);
        assertThat(queue.poll()).isEqualTo(4);
        assertThat(queue.poll()).isEqualTo(5);
    }

    @Test
    public void testOffer_onFullQueue_shouldThrowException() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        // act && assert
        assertThatThrownBy(() -> queue.offer(5)).isInstanceOf(IllegalStateException.class).hasMessage("Queue is " +
                "full, max size of 4 exceeded.");

    }

    @Test
    public void testPoll_onEmpty_shouldThrowException() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(4);

        // act && assert
        assertThatThrownBy(queue::poll).isInstanceOf(IllegalStateException.class).hasMessage("Queue is empty.");

    }

    @Test
    public void testToString() {
        // arrange
        Queue<Integer> queue = new RingBuffer<>(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.poll();
        queue.poll();
        queue.poll();
        queue.poll();
        queue.offer(6);
        queue.offer(7);
        queue.poll();

        // act
        var res = queue.toString();

        // assert
        assertThat(res).isEqualTo("RingBuffer{head=2, tail=1, size=1, items=[null, 7, null, null]}");
    }
}