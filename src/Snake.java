/*
 * Created on 2024-11-21
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

import java.util.ArrayList;

public class Snake {
    private SnakeSegment head;
    private Direction direction = Direction.RIGHT; // Default direction

    public Snake() {
        // You may change this code for extra credit (implement some fancy stuff!)
        // Feel free to make the starting position random
        Position startingPosition = new Position(10, 10);
        head = new SnakeSegment(startingPosition);
    }

    //implemented getDirection
    public Direction getDirection() {
        return this.direction;
    }

    // TODO: The snake should grow whenever it "eats" a food item
    private boolean shouldGrow = false;

    public void shouldGrow() {
        this.shouldGrow = true;
    }

    // TODO: Remove the last node (tail) of the snake, leave head untouched
    private void removeTail() {
        SnakeSegment current = head;
        while (current.getNext() != null && current.getNext().getNext() != null) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(null);
        }
    }

    // Returns true if the snake is colliding with itself
    public boolean isColliding() {
        if (isBodyPartAt(head.getPosition())) {
            return true;
        }
        return false;
    }

    // TODO: Implement isInSnake()
    // Returns false if the specified position is inside the body of the snake
    public boolean isBodyPartAt(Position position) {
        SnakeSegment current = head.getNext();
        while (current != null) {
            if (current.getPosition().equals(position)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // Sets the direction the snake will move in
    public void setDirection(Direction direction) {
        this.direction = direction;

    }

    // TODO: Get the length of the snake
    public int getLength() {
        int count = 0;
        SnakeSegment current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    // Moves the snake by one in the next direction
    // TODO: Implement move()
    public void move() {
        Position newPosition = head.getPosition().add(direction.deltaPosition());

        // Create a new head segment
        SnakeSegment newHead = new SnakeSegment(newPosition);
        newHead.setNext(head);
        head = newHead;

        if (!shouldGrow) {
            removeTail();
        } else {
            shouldGrow = false;
        }

        // Optional: Handle wrapping
        if (newPosition.x < 0) head.getPosition().x = SnakeGame.WIDTH / SnakeGame.SQUARE_SIZE - 1;
        if (newPosition.x >= SnakeGame.WIDTH / SnakeGame.SQUARE_SIZE) head.getPosition().x = 0;
        if (newPosition.y < 0) head.getPosition().y = SnakeGame.HEIGHT / SnakeGame.SQUARE_SIZE - 1;
        if (newPosition.y >= SnakeGame.HEIGHT / SnakeGame.SQUARE_SIZE) head.getPosition().y = 0;
    }


    // Return the head of the snake
    public SnakeSegment getHead() {
        return this.head;
    }

    // TODO: Return the start of the body (NOT the head!)
    public SnakeSegment getBody() {
        return head.getNext(); // Return the segment after the head
    }

    // OPTIONAL: Implement an algorithm that moves the food for us
    public Direction findNextMove(ArrayList<Food> food) {
        return null;
    }
}

class SnakeSegment {
    private Position position;
    private SnakeSegment next;

    public SnakeSegment(Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return this.position;
    }

    public SnakeSegment getNext() {
        return this.next;
    }

    public void setNext(SnakeSegment next) {
        this.next = next;
    }
}
