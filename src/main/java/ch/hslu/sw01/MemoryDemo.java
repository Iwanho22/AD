package ch.hslu.sw01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MemoryDemo {
    public static final Logger LOG = LoggerFactory.getLogger(MemoryDemo.class);

    public static void main(String[] args) {
        Memory memory = new SimpleMemory(20);
        LOG.info("{}",memory);
        Allocation block1 = memory.alloc(4);
        LOG.info("{}",memory);
        LOG.info("{}",block1);
        Allocation block2 = memory.alloc(4);
        LOG.info("{}",block2);
        LOG.info("{}",memory);
        memory.free(block1);
    }
}
