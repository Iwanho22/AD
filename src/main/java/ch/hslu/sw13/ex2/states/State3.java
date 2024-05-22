package ch.hslu.sw13.ex2.states;

import ch.hslu.sw13.ex2.Transitionalbe;

public enum State3 implements Transitionalbe {
    STATE_3;

    @Override
    public Transitionalbe sendInput(char input) {
        return switch (input) {
            case 'N' -> State4.STATE_4;
            case 'A' -> State1.STATE_1;
            default -> InitialState.INITIAL_STATE;
        };
    }
}
