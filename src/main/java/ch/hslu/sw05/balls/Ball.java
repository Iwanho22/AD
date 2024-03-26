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

import java.util.List;
import java.util.Random;

/**
 * Description of class Ball
 */
public class Ball implements Runnable {
    public static final Random RANDOM = new Random();

    private final Circle circle;
    private final int size;
    private final int offset;

    public Ball(final int size, final int xPos, final int yPos, String color) {
        this.size = size * 2;
        this.circle = new Circle(this.size, xPos, yPos, color);
        this.offset = RANDOM.nextInt(1, 10);
    }

    @Override
    public void run() {
        circle.makeVisible();
        do {
            circle.moveVertical(offset);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (circle.getY() + size < Canvas.getCanvas().getHeight());
        circle.makeInvisible();
    }
}
