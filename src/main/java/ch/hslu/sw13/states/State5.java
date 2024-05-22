package ch.hslu.sw13.states;

import ch.hslu.sw13.Transitionalbe;

public enum State5 implements Transitionalbe {
    STATE_5;

    @Override
    public Transitionalbe sendInput(char input) {
        return switch (input) {
            case 'A' -> State1.STATE_1;
            case 'N' -> State4.STATE_4;
            case 'S' -> State6.STATE_6;
            default -> InitialState.INITIAL_STATE;
        };
    }
}
