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
package ch.hslu.sw08.ex2;

/**
 * Thread-sicherer Zähler.
 */
public final class SynchronizedCounter implements Counter {

    private final Object lock;
    private int count;
    /**
     * Erzeugt einen Zähler mit Zählerstand 0.
     */
    public SynchronizedCounter() {
        this.count = 0;
        this.lock = new Object();
    }

    /**
     * see ch.hslu.ad.exercise.sw07.count.Counter#increment()
     */
    @Override
    public int increment() {
       synchronized (lock) {
           return ++count;
       }
    }

    /**
     * see ch.hslu.ad.exercise.sw07.count.Counter#decrement()
     */
    @Override
    public int decrement() {
        synchronized (lock) {
            return --count;
        }
    }

    /**
     * see ch.hslu.ad.exercise.sw07.count.Counter#get()
     */
    @Override
    public int get() {
       synchronized (lock) {
           return count;
       }
    }
}
