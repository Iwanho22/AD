package ch.hslu.sw13.ex2.states;

import ch.hslu.sw13.ex2.Transitionalbe;

public enum State4 implements Transitionalbe {
    STATE_4;

    @Override
    public Transitionalbe sendInput(char input) {
        if (input == 'A') {
            return State5.STATE_5;
        }
        return InitialState.INITIAL_STATE;
    }
}
