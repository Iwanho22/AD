package ch.hslu.sw12.States;

import ch.hslu.sw12.Transisitionable;
import ch.hslu.sw12.TransitionType;

public enum State2 implements Transisitionable {
    STATE_2;

    @Override
    public Transisitionable transition(TransitionType type) {
        return switch (type) {
            case ONE -> State3.STATE_3;
            case ZERO -> State4.STATE_4;
        };
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
