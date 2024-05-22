package ch.hslu.sw12;

public interface Transisitionable {
    Transisitionable transition(TransitionType type);

    boolean isEndState();
}
