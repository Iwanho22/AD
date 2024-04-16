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
package ch.hslu.sw08.ex3;

import ch.hslu.sw02.ex2.collections.LinkedList;
import ch.hslu.sw05.bank.AccountTask;
import ch.hslu.sw05.bank.BankAccount;
import ch.hslu.sw08.ex2.AtomicCounter;
import ch.hslu.sw08.ex2.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public final class Measurement {

    private static final Logger LOG = LoggerFactory.getLogger(Measurement.class);

    private static void waitForCompletion(final Thread[] threads) throws InterruptedException {
        for (final Thread thread : threads) {
            thread.join();
        }
    }

    private static void waitForCompletion(final List<Future<?>> threads) throws InterruptedException, ExecutionException {
        for (final Future<?> future : threads) {
            future.get();
        }
    }

    public static long speedTestOldBank(int amount, int accounts) throws InterruptedException {
        final List<ch.hslu.sw05.bank.BankAccount> source = new LinkedList<>();
        final List<ch.hslu.sw05.bank.BankAccount> target = new LinkedList<>();
        for (int i = 0; i < accounts; i++) {
            source.add(new ch.hslu.sw05.bank.BankAccount(amount));
            target.add(new BankAccount());
        }
        var start = System.nanoTime();
        final Thread[] threads = new Thread[accounts * 2];
        for (int i = 0; i < accounts; i++) {
            threads[i] = new Thread(new ch.hslu.sw05.bank.AccountTask(source.get(i), target.get(i), amount));
            threads[i + accounts] = new Thread(new AccountTask(target.get(i), source.get(i), amount));
        }
        for (final Thread thread : threads) {
            thread.start();
        }
        waitForCompletion(threads);

        return (System.nanoTime() - start) / 1_000_000;
    }

    public static long speedTestNewBank(int amount, int accounts) throws InterruptedException, ExecutionException {
        final ArrayList<ch.hslu.sw08.ex3.BankAccount> source = new ArrayList<>();
        final ArrayList<ch.hslu.sw08.ex3.BankAccount> target = new ArrayList<>();

        for (int i = 0; i < accounts; i++) {
            source.add(new ch.hslu.sw08.ex3.BankAccount(amount));
            target.add(new ch.hslu.sw08.ex3.BankAccount());
        }
        var start = System.nanoTime();
        // Account Tasks starten...
        var task = new ArrayList<Future<?>>();
        try (var pool = Executors.newFixedThreadPool(accounts * 3)) {
            for (int i = 0; i < accounts; i++) {
                var account = i;
                task.add(pool.submit(() -> new ch.hslu.sw08.ex3.AccountTask(source.get(account), target.get(account), amount)));
                task.add(pool.submit(() -> new ch.hslu.sw08.ex3.AccountTask(target.get(account), source.get(account), amount)));
            }
        }
        waitForCompletion(task);
        return (System.nanoTime() - start) / 1_000_000;
    }


    public static void main(final String[] args) throws ExecutionException, InterruptedException {
        final int passes = 10;
        final int accounts = 10;
        final int amount = 1_000_000;
        long sumOld = 0;
        //dry run
        speedTestOldBank(amount, accounts);
        for (int i = 0; i < passes; i++) {
            sumOld += speedTestOldBank(amount, accounts);
        }

        //dry run
        speedTestNewBank(amount, accounts);
        long sumNew = 0;
        for (int i = 0; i < passes; i++) {
            sumNew += speedTestNewBank(amount, accounts);
        }

        LOG.info("Sync counter ok");
        LOG.info("Sync counter average test duration = {} ms", sumOld / (float) passes);


        LOG.info("Atom counter ok");
        LOG.info("Atom counter average test duration = {} ms", sumNew / (float) passes);

    }
}
