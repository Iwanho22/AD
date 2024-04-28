/*
 * Copyright 2024 Hochschule Luzern - Informatik.
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
package ch.hslu.sw08.ex3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Demonstration der Bankgeschäfte - Aufgabe 4 - N3_EX_WeiterführendeKonzepte.
 */
public final class DemoBankAccount {

    private static final Logger LOG = LoggerFactory.getLogger(DemoBankAccount.class);

    /**
     * Privater Konstruktor.
     */
    private DemoBankAccount() {
    }

    /**
     * Wartet bis alle Threads abgearbeitet sind.
     *
     * @param threads Array mit Threads.
     * @throws InterruptedException Interupted.
     */
    private static void waitForCompletion(final List<Future<?>> threads) throws InterruptedException, ExecutionException {
        for (final Future<?> future : threads) {
            future.get();
        }
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ArrayList<BankAccount> source = new ArrayList<>();
        final ArrayList<BankAccount> target = new ArrayList<>();
        final int amount = 100_000;
        final int number = 5;
        for (int i = 0; i < number; i++) {
            source.add(new BankAccount(amount));
            target.add(new BankAccount());
        }
        // Account Tasks starten...
        var task = new ArrayList<Future<?>>();
        try (var pool = Executors.newFixedThreadPool(number * 3)) {
            for (int i = 0; i < number; i++) {
                var account = i;
                task.add(pool.submit(() -> new AccountTask(source.get(account), target.get(account), amount)));
                task.add(pool.submit(() -> new AccountTask(target.get(account), source.get(account), amount)));
            }
        }
        waitForCompletion(task);
        LOG.info("Bank accounts after transfers");
        for (int i = 0; i < number; i++) {
            LOG.info("source({}) = {}; target({}) = {};", i, source.get(i).getBalance(), i, target.get(i).getBalance());
        }
    }
}
