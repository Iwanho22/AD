package ch.hslu.sw12;

import ch.hslu.sw12.States.InitialState;

import java.util.Arrays;

public class WordChecker {

    public static boolean checkWord(final String word) {
        Transitionable state = InitialState.INITIAL_STATE;
        var symbols = Arrays.stream(word.split("")).map(Integer::valueOf).toList();

        var valid = true;
        for(int symbol: symbols) {
            try {
                state = state.transition(TransitionType.getByCode(symbol));
            } catch (IllegalArgumentException e) {
                valid = false;
                break;
            }
        }

        if (valid) {
            return state.isEndState();
        }

        return false;
    }
}
