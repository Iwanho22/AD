package ch.hslu.sw01;

import java.util.Objects;

public final class Allocation implements Comparable<Allocation> {
    private final long start;
    private final long size;

    public Allocation(long start, long size) {
        if (start < 0) {
            throw new IllegalArgumentException("Start must be a positive number.");
        } else if (size <= 1) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }

        this.start = start;
        this.size = size;
    }

    public long getStart() {
        return start;
    }

    public long getSize() {
        return size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, size);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return (obj instanceof Allocation other)
                && other.start == this.start
                && other.size == this.size;
    }

    @Override
    public int compareTo(Allocation o) {
        if (o == this) {
            return 0;
        }

        return Long.compare(this.start, o.start);
    }

    @Override
    public String toString() {
        return "Allocation[Address: %d; Size: %d]".formatted(start, size);
    }
}
