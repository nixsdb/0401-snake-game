/*
 * Created on 2024-11-21
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

import java.util.ArrayList;

public class Snake {
    private SnakeSegment head;
    private Direction direction = RIGHT; // Default direction

    public Snake() {
        // You may change this code for extra credit (implement some fancy stuff!)
        // Feel free to make the starting position random
        Position startingPosition = new Position(10, 10);
        head = new SnakeSegment(startingPosition);
    }

    // TODO: The snake should grow whenever it "eats" a food item
    public void shouldGrow() {

    }

    // TODO: Remove the last node (tail) of the snake, leave head untouched
    private void removeTail() {

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
        return false;
    }

    // Sets the direction the snake will move in
    public void setDirection(Direction direction) {
        this.direction = direction;

    }

    // TODO: Get the length of the snake
    public int getLength() {
        int count = 0;

        return count;
    }

    // Moves the snake by one in the next direction
    // TODO: Implement move()
    public void move() {
        Position newPosition = head.getPosition().add(direction.deltaPosition());

        // ...

        // * OPTIONAL: also handle wrapping left and right here
        // * Or check for a collision with the top and bottom of the frame

        // HINT: You may add and remove nodes here

        // TODO: Uncomment and use the following code snippet
        // if (!shouldGrow) {
        // removeTail();
        // } else {
        // shouldGrow = false;
        // }
    }

    // Return the head of the snake
    public SnakeSegment getHead() {
        return this.head;
    }

    // TODO: Return the start of the body (NOT the head!)
    public SnakeSegment getBody() {
        return null;
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
