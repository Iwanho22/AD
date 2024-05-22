package ch.hslu.sw13;

import ch.hslu.sw13.states.InitialState;

public class AnanasChecker {

    public static int find(String input) {
        var inputs = input.toCharArray();
        Transitionalbe state = InitialState.INITIAL_STATE;

        for (int i = 0; i < input.length(); i++) {
            state = state.sendInput(inputs[i]);
            if (state.isEndState()) {
                return i - 5;
            }
        }

        return -1;
    }
}
