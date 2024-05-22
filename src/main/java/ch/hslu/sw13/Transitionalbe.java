package ch.hslu.sw13;

public interface Transitionalbe {

    Transitionalbe sendInput(char input);

    default boolean isEndState() {
        return false;
    }
}
