/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.sw05.joins;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Demonstration von Join und Sleep - Aufgabe 3 - N1_EX_ThreadsSynch.
 */
public class JoinAndSleep {
    
    private static final Logger LOG = LoggerFactory.getLogger(JoinAndSleep.class);
    
    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException {
        var thread3 = new Thread(new JoinAndSleepTask("Task 3", 4000), "Thread 3");
        var thread2 = new Thread(new JoinAndSleepTask("Task 2", 3000, thread3), "Thread 2");
        var thread1 = new Thread(new JoinAndSleepTask("Task 1", 2000, thread2), "Thread 1");

        LOG.info("Started Thread 1");
        thread1.start();
        LOG.info("Started Thread 2");
        thread2.start();
        LOG.info("Started Thread 3");
        thread3.start();
    }
}
