package ch.hslu.sw12.States;

import ch.hslu.sw12.Transitionable;
import ch.hslu.sw12.TransitionType;

public enum State1 implements Transitionable {
    STATE_1;

    @Override
    public Transitionable transition(TransitionType type) {
        return switch (type) {
            case ONE -> State2.STATE_2;
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
    }

    @Override
    public boolean isEndState() {
        return true;
    }
}
