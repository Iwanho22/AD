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
package ch.hslu.sw05.balls;

import java.util.Random;

/**
 * Demonstration von Bällen.
 */
public class DemoBalls {
    public static final Random RANDOM = new Random();

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        Canvas.getCanvas();

        for (int i = 0; i < 50; i++) {
            var ball = new Ball(
                    RANDOM.nextInt(20, 50),
                    RANDOM.nextInt(0, 550),
                    RANDOM.nextInt(50),
                    Circle.COLORS.get(RANDOM.nextInt(0, Circle.COLORS.size())));
            Thread.startVirtualThread(ball);
            /*var thread = new Thread(ball, "Ball-Task");
            thread.start();*/
        }
    }

}
