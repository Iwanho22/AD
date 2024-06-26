package ch.hslu.sw13.ex2.states;

import ch.hslu.sw13.ex2.Transitionalbe;

public enum InitialState implements Transitionalbe {
    INITIAL_STATE;

    @Override
    public Transitionalbe sendInput(char input) {
        if (input == 'A') {
            return State1.STATE_1;
        }

        return INITIAL_STATE;
    }
}
