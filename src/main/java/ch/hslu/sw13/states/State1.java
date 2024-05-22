package ch.hslu.sw13.states;

import ch.hslu.sw13.Transitionalbe;

public enum State1 implements Transitionalbe {
    STATE_1;

    @Override
    public Transitionalbe sendInput(char input) {
        return switch (input) {
            case 'A' -> STATE_1;
            case 'N' -> State2.STATE_2;
            default -> InitialState.INITIAL_STATE;

        };
    }
}
