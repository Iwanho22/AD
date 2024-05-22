package ch.hslu.sw12;

public enum TransitionType {
    ZERO(0),
    ONE(1);

    private final int code;

    TransitionType(final int code) {
        this.code = code;
    }

    public static TransitionType getByCode(int code) {
        for (var value: values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("No transition type with code " + code);
    }
}
