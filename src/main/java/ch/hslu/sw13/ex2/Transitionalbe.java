package ch.hslu.sw13.ex2;

public interface Transitionalbe {

    Transitionalbe sendInput(char input);

    default boolean isEndState() {
        return false;
    }
}
