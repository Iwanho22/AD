package ch.hslu.sw12.States;

import ch.hslu.sw12.Transitionable;
import ch.hslu.sw12.TransitionType;

public enum InitialState implements Transitionable {
    INITIAL_STATE;

    @Override
    public Transitionable transition(TransitionType type) {
        return switch (type) {
            case ZERO -> State1.STATE_1;
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
