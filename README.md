# Snake it off!

## Introduction

The goal of this project is to fill out the logic for our very own snake game, compelete with graphics! 
If you haven't played Snake before, search google for [snake game](https://google.com/search?q=snake+game) to play it live in your browser before you start coding.

### Game Rules

- The snake always starts by going to the right
- The snake grows by 1 segment for every food item it eats
- The player loses if the snake collides with itself
- (optional CHALLENGE) The player loses if the snake hits the edge of the board (Alternatively, it can wrap around the board)
- The snake is moved once per frame (handled by the driver code!)
- The player's score is the length of the snake at the end

## Part 1 - Linked Snake

- Implement the TODO's in `Snake.java` and `SnakeGame.java`
- Your snake should use the provided `SnakeSegment` class as the nodes in the snake

### Position 
- The data in the nodes is of type `Position` which is a simple class that holds 2 integers, x and y:

```java
Position position = new Position(x: 1, y: 2);
position.x; // = 1
position.y; // = 2
```

Additionally, `Position` has an `equals()` method that allows to compare two positions:

```java
Position position = new Position(1, 2);
Position position2 = new Position(2, 2);

position.equals(position2); // = false
```

`Position.randomPosition()` generates a random position. This is useful for the optional challenges.

### SnakeGame
In `SnakeGame.java`, you neeed to implement the method that draws the snake's body:

```java
 public void drawBody() {
    
}
```

*Note*: You do not need to work with the graphics here. There is already a method `drawSegment()` that takes a `SnakeSegment` as input. When you pass a segment, it will draw a body piece at the location specified by `SnakeSegment.getPosition()`.

### The Core Methods

#### Move

The `move()` method moves the snake by one square on the game board in the direction of travel (kept track of elsewhere in your code). 

```java
public void move() {

}
```
There are two ways to implement this method: (1) Go through every segment and change its position (hard) or (2) keep all of the segments stationary except the head and tail. That would look like this:

- Create new segment that occupies the next space that snake is moving to
- Make this segment the head
- Remove the tail

*Remember, work smarter not harder! :-)*

For your convenience, there are some helper methods built into the `Position` class and `Direction` enum (contains some TODOs). 

`Direction.deltaPosition(direction)` is a **static** method that takes in a direction and outputs the change in position the direction represents. 

For example:
```java
Position up = Direction.deltaPosition(Direction.UP);
up.x; // = 0
up.y; // = -1 (because y starts at 0 and increases as you go down!)
```

`Position.add(Position position)` adds the two positions and returns a **new** one.  

For example:
```java
Position position1 = new Position(10, 10)
Position position2 = new Position(1, 1);

Position sum = position1.add(position2);
sum.x; // = 11
sum.y; // = 11
```

Use these in your method!

#### Grow

The `shouldGrow()` method indicates whether to increase the length of the snake by one segment. 
There are multiple ways to solve this. 

```java
public void shouldGrow() {

}
```
*Hint:* What if we just changed the behavior of our move method when we are supposed to grow?

### The Direction Enum

We haven't talked a lot about enums thus far, but fret not! You will learn a bit more about enums as you adapt the `keyPressed()` method.
`Direction.java` is not a class, but an `enum`. This is a special type of class that is practically a list of possible values, in our case possible directions. 
You can access these fields using the `.` operator, as we do for standard classes. We can store and compare these values with each other, which is great when we only have a few of them.

For example:
```java
Direction direction = Direction.UP;
direction == Direction.DOWN; // = false
```

The `keyPressed()` method in `SnakeGame` contains commented code. Uncomment it and fix the switch statement.
This should be a fairly easy task -- decide on the case and set the direction for each case. The rest is already implemented and should not need changing.


## Part 2 - More Food!!

Our snake is *HUNGRY!* Cherries just aren't cutting it. Add in a few more food items by extending the `Food` class.
All food items must have two methods, `getPosition()` and `getIcon()`.

`getPosition()` returns the position of the Food item.

`getIcon()` returns the location of an 8x8 pixel icon for the food item. There are already extra icons in the `assets/` directory, which you may use to do this. Feel free to import your own!

Once you're done creating a new type of food, add it to the method `randomFood()` in `Food.java`:

```java
public static Food randomFood(Position position) {
    int maxActiveFoodItems = 2; // // Represents the number of food items
    
    int randomNumber = new Random().nextInt(maxActiveFoodItems);

    switch(randomNumber) {
        case 0:
            return new Cherry(position);
        case 1:
            return new MyNewFruit(position); // Replace this line here with your new food!
        default:
            return null;
    }
}
```

This allows our game to use our new food items!

## Challenges

### Pause

Implement the `pause()` method in `SnakeGame` to freeze/unfreeze the snake's movement when called. 
`SnakeGame` calls this method when the player hits the space key.

```java
public void pause() {

}
```

### AI, or *Almost* Intelligence

Implement the method

```java
public Direction makeMove(ArrayList<Food> food) {

}
```

This method takes in the array of food as an argument and returns the direction it thinks the snake should move. You do not need to do anything fancy here, maybe just move towards the food and try not to make a move that lands you in your own body!

## Game Parameters

There are a few parameters of the game that you can adjust to your liking, which are all found in `SnakeGame.java`:

```java
public static final int SQUARE_SIZE = 20; // Size of each cell in our game
public static final int MAX_ACTIVE_FOOD = 1; // The number of food items to spawn
public static final int WIDTH = 800; // The width of the window (must be a multiple of SQUARE_SIZE)
public static final int HEIGHT = 600; // The height of the window (must be a multiple of SQUARE_SIZE)
public static final int SPEED = 10; // Frames per second the game runs at
```
