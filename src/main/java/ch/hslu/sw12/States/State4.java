package ch.hslu.sw12.States;

import ch.hslu.sw12.Transitionable;
import ch.hslu.sw12.TransitionType;

public enum State4 implements Transitionable {
    STATE_4;

    @Override
    public Transitionable transition(TransitionType type) {
        return switch (type) {
            case ONE -> State2.STATE_2;
            case ZERO -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
    }

    @Override
    public boolean isEndState() {
        return true;
    }
}
