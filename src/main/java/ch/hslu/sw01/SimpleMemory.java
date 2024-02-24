package ch.hslu.sw01;

import java.util.LinkedHashSet;
import java.util.Set;

public class SimpleMemory implements Memory {
    public static final long ONE_GB = 8589934592L;
    private final long size;
    private final LinkedHashSet<Allocation> allocatedSpace = new LinkedHashSet<>();

    public SimpleMemory(long size) {
        if (size > ONE_GB) {
            throw new IllegalArgumentException("Memory size to large. Max Size is one GB");
        }

        this.size = size;
    }

    public long getFreeSpace() {
        return size - allocatedSpace.stream().mapToLong(Allocation::getSize).sum();
    }

    public long getUsedSpace() {
        return allocatedSpace.stream().mapToLong(Allocation::getSize).sum();
    }

    @Override
    public Allocation alloc(long size) {
        long start = 0;

        if (!allocatedSpace.isEmpty()) {
            var lastAllocatedSpace =  allocatedSpace.getLast();

            start = lastAllocatedSpace.getStart() + lastAllocatedSpace.getSize();
            if (start + size > this.size) {
                var previous = allocatedSpace.getFirst();
                for (Allocation current : allocatedSpace) {
                    long potentialStart = previous.getStart() + previous.getSize();
                    if (current.getStart() - potentialStart >= size) {
                        start = potentialStart;
                        break;
                    }
                    previous = current;
                }

                if (start + size > this.size) {
                    throw new OutOfMemoryError("Not enough space in memory");
                }
            }
        }

        var allocation = new Allocation(start, size);
        allocatedSpace.addLast(allocation);
        return allocation;
    }

    @Override
    public void free(Allocation allocation) {
        allocatedSpace.remove(allocation);
    }

    @Override
    public String toString() {
        var used = getUsedSpace();
        return "SimpleMemory[Used: %d; Free: %d]".formatted(used, size - used);
    }
}
