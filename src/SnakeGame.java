/*
 * Created on 2024-11-21
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

import java.util.HashMap;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class SnakeGame extends PApplet {
    public static final int SQUARE_SIZE = 20;
    public static final int MAX_ACTIVE_FOOD = 1;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SPEED = 10;

    public Snake snake = new Snake();

    public HashMap<String, PImage> assets = new HashMap<>();

    public ArrayList<Food> foodList = new ArrayList<>();

    private PImage headImage;

    public boolean gameOver = false;

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "SnakeGame" };
        PApplet.main(appletArgs);
    }

    @Override
    public void settings() {
        smooth(0);
        size(800, 600);
    }

    @Override
    public void setup() {
        // Change the framerate for a harder/easier game
        frameRate(SPEED);

        // Load all the assets that are used often (just the snake parts, not the food!)
        assets.put("head_up", loadAsset("./assets/head_up.png"));
        assets.put("head_down", loadAsset("./assets/head_down.png"));
        assets.put("head_left", loadAsset("./assets/head_left.png"));
        assets.put("head_right", loadAsset("./assets/head_right.png"));
        assets.put("body", loadAsset("./assets/body.png"));

        // The snake always moves to the right by default
        // Hence, the right head is set as starting head
        headImage = assets.get("head_right");

        // Grow the snake so we do not start at 0
        snake.shouldGrow();

        // Create food at random positions
        for (int i = 0; i < MAX_ACTIVE_FOOD; i++) {
            foodList.add(Food.randomFood(Position.randomPosition()));
        }
    }

    @Override
    public void draw() {
        background(0);
        if (gameOver) {
            showGameOverScreen();
            return;
        }

        // Move once a frame
        Direction randomMove = snake.findNextMove(foodList);
        if (randomMove != null) {
            snake.setDirection(randomMove);
        }
        snake.move();

        // Draw food first so the snake covers it up
        drawFood();

        // Draw the snake
        drawBody();
        image(headImage, snake.getHead().getPosition().x * SQUARE_SIZE, snake.getHead().getPosition().y * SQUARE_SIZE,
                SQUARE_SIZE, SQUARE_SIZE);

        checkFoodCollision();
        checkGameOver();
    }

    // Check if the snake has eaten some food
    public void checkFoodCollision() {
        Food eatenFood = null;
        for (Food food : foodList) {
            if (snake.getHead().getPosition().equals(food.getPosition())) {
                snake.shouldGrow();
                eatenFood = food;
                break;
            }
        }
        if (eatenFood != null) {
            foodList.remove(eatenFood);
            Position randomPosition = Position.randomPosition();
            while (snake.isBodyPartAt(randomPosition)) {
                randomPosition = Position.randomPosition();
            }
            foodList.add(Food.randomFood(randomPosition));
        }
    }

    public void checkGameOver() {
        if (snake.isColliding()) {
            gameOver = true;
        }
    }

    public void showGameOverScreen() {
        // Center text alignment
        textAlign(CENTER, CENTER);

        textSize(64);
        // Centered, slightly above vertical center
        text("Your Score: " + snake.getLength(), WIDTH / 2.0f, HEIGHT / 2.5f);

        textSize(32);
        // Center horizontally, slightly below vertical center
        text("Press SPACE to restart....", WIDTH / 2.0f, HEIGHT / 1.6f);
    }

    public void restart() {
        gameOver = false;
        snake = new Snake();

        // Reset direction to default
        snake.setDirection(Direction.RIGHT);
        snake.shouldGrow();

        // Reset head image to match the starting direction
        headImage = assets.get("head_right");

        // Reset food list
        foodList.clear();
        for (int i = 0; i < MAX_ACTIVE_FOOD; i++) {
            foodList.add(Food.randomFood(Position.randomPosition()));
        }
    }

    // OPTIONAL: Implement a pause() functionality
    public void pause() {
        // ...
    }

    public void drawFood() {
        // Save the food icons in the assets so we do not have to keep importing it
        for (Food food : foodList) {
            if (!assets.containsKey(food.getIcon())) {
                assets.put(food.getIcon(), loadAsset(food.getIcon()));
            }

            image(assets.get(food.getIcon()), food.getPosition().x * SQUARE_SIZE,
                    food.getPosition().y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    public void drawSegment(SnakeSegment segment) {
        image(assets.get("body"), segment.getPosition().x * SQUARE_SIZE,
                segment.getPosition().y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    // TODO: Draw the body
    public void drawBody() {
        SnakeSegment current = snake.getBody();
        while (current != null) {
            drawSegment(current);
            current = current.getNext();
        }
    }

    public PImage loadAsset(String path) {
        PImage image = loadImage(path);
        return image;
    }

    // TODO: Set the direction depending on pressing a key
    @Override
    public void keyPressed() {
        /**
         * keycode: There is an enum for all special keyboard keys
         * see
         * https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html#keyCode
         * This enum is accessible in our current scope, so we do not
         * need have to use the dot ('.') operator to access them
         * (the 'keyCode' is not the same as 'Direction'!)
         */
        switch (keyCode) {
            case UP:
                if (snake.getDirection() != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                    headImage = assets.get("head_up");
                }
                break;
            case DOWN:
                if (snake.getDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                    headImage = assets.get("head_down");
                }
                break;
            case LEFT:
                if (snake.getDirection() != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                    headImage = assets.get("head_left");
                }
                break;
            case RIGHT:
                if (snake.getDirection() != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                    headImage = assets.get("head_right");
                }
                break;
            default:
                break;
        }

        if (key == ' ') {
            if (gameOver) {
                restart();
            } else {
                pause();
            }
        }
    }
}
