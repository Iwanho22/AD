package ch.hslu.sw02.ex3;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleStackTest {

    @Test
    public void testNew_stackShouldBeEmpty() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();

        // act
        var res = stack.isEmpty();

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testPush_stackShouldNotBeEmpty() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();

        // act
        stack.push(1);

        // assert
        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    public void testNew_stackShouldNotBeFull() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();

        // act
        var res = stack.isFull();

        // assert
        assertThat(res).isFalse();
    }

    @Test
    public void testPush_stackShouldBeFull() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>(1);

        // act
        stack.push(1);

        // assert
        assertThat(stack.isFull()).isTrue();
    }

    @Test
    public void testPush_sizeShouldIncrease() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();

        // act
        stack.push(1);
        stack.push(1);

        // assert
        assertThat(stack.size()).isEqualTo(2);
    }

    @Test
    public void testPop_sizeShouldNotDecrease() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();

        // act
        stack.push(1);
        stack.push(2);
        stack.pop();

        // assert
        assertThat(stack.size()).isEqualTo(1);
    }

    @Test
    public void testPop_addMultiple_shouldReturnLatestElement() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);

        // act
        var res = stack.pop();

        // assert
        assertThat(res).isEqualTo(2);
    }

    @Test
    public void testPeek_addMultiple_SizeShouldStaySame() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);

        // act
        stack.peek();

        // assert
        assertThat(stack.size()).isEqualTo(2);
    }

    @Test
    public void testPeek_addMultiple_shouldReturnLatestElement() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(7);

        // act
        var res = stack.peek();

        // assert
        assertThat(res).isEqualTo(7);
    }

    @Test
    public void testClear_sizeShouldBeEmpty() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(7);

        // act
        stack.clear();

        // assert
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    public void testPop_onEmptyStack_shouldThrowException() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>();

        // act & assert
        assertThatThrownBy(stack::pop)
                .isInstanceOf(EmptyStackException.class);
    }

    @Test
    public void testPush_onFullStack_shouldThrowException() {
        // arrange
        Stack<Integer> stack = new SimpleStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // act & assert
        assertThatThrownBy(() -> stack.push(4))
                .isInstanceOf(StackOverflowError.class)
                .hasMessage("Stack is full. Cannot push an element");
    }
}