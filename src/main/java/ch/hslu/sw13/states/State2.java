package ch.hslu.sw13.states;

import ch.hslu.sw13.Transitionalbe;

public enum State2 implements Transitionalbe {
    STATE_2;

    @Override
    public Transitionalbe sendInput(char input) {
        if (input == 'A') {
            return State3.STATE_3;
        }

        return InitialState.INITIAL_STATE;
    }
}
