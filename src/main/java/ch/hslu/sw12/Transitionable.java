package ch.hslu.sw12;

public interface Transitionable {
    Transitionable transition(TransitionType type);

    boolean isEndState();
}
