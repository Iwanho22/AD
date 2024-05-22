package ch.hslu.sw13.ex2.states;

import ch.hslu.sw13.ex2.Transitionalbe;

public enum State6 implements Transitionalbe {
    STATE_6;

    @Override
    public Transitionalbe sendInput(char input) {
        return InitialState.INITIAL_STATE;
    }

    @Override
    public boolean isEndState() {
        return true;
    }


}
