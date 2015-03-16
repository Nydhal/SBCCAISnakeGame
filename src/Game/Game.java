package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AI.AI;
import AI.DummyAI;
import AI.GameMap;

//TODO comment more code

public class Game {
	static Random random = new Random();

	int maxNumberOfSteps = 500;
	int numberOfSteps = 0;
	boolean gameOver = false;

	int worldWidth;
	int worldHeight;

	Apple apple;

	Snake player1;
	AI player1AI;
	Snake player2;
	AI player2AI;

	public Game(int worldWidth, int worldHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;

		player1 = new Snake(0, 0);
		player2 = new Snake(worldWidth - 1, worldHeight - 1);

		apple = new Apple(0, 5);
		placeAppleRandomly();
	}

	public void setPlayer1AI(AI ai) {
		player1AI = ai;
	}

	public void setPlayer2AI(AI ai) {
		player2AI = ai;
	}

	public void step() {
		if (!gameOver) {
			numberOfSteps++;

			Direction newDirection;

			newDirection = player1AI.getDirection(generateGameMap(player1));
			if (newDirection != null)
				player1.direction = newDirection;

			newDirection = player2AI.getDirection(generateGameMap(player2));
			if (newDirection != null)
				player2.direction = newDirection;

			player1.move();
			player2.move();

			collisionDetection();
			winDetection();
		}
	}

	private GameMap generateGameMap(Snake player) {
		GameMap map = new GameMap();

		map.width = worldWidth;
		map.height = worldHeight;

		// don't want the AI's modifying the actual apples or snakes, so the
		// following methods perform a deep copy so the AI's have their own and
		// any modifications they make will not be reflected.
		map.apple = cloneApple();

		if (player == player1) {
			map.self = cloneSnake(player1);
			map.opponent = cloneSnake(player2);
		} else {
			map.self = cloneSnake(player2);
			map.opponent = cloneSnake(player1);
		}

		map.map = generateMap();

		return map;
	}

	private int[][] generateMap() {
		int[][] map = new int[worldWidth][worldHeight];

		// array is already initialized to 0's
		// for (int x = 0; x < worldWidth - 1; x++) {
		// for (int y = 0; y < worldHeight - 1; y++) {
		// map[x][y] = 0;
		// }
		// }

		map[apple.x][apple.y] = 3;

		map[player1.head.x][player1.head.y] = 1;

		for (SnakePart part : player1.parts)
			map[part.x][part.y] = 1;

		map[player2.head.x][player2.head.y] = 2;

		for (SnakePart part : player2.parts)
			map[part.x][part.y] = 2;

		return map;
	}

	private Apple cloneApple() {
		return new Apple(apple);
	}

	private List<SnakePart> cloneSnake(Snake snake) {
		List<SnakePart> clonedSnake = new ArrayList<>();

		clonedSnake.add(new SnakePart(snake.head));

		for (SnakePart part : snake.parts)
			clonedSnake.add(new SnakePart(part));

		return clonedSnake;
	}

	private void collisionDetection() {
		// players out of bounds
		// player1
		if ((player1.head.x < 0 || player1.head.y < 0)
				|| (player1.head.x >= worldWidth || player1.head.y >= worldHeight))
			player1.hasCollided = true;
		// player2
		if ((player2.head.x < 0 || player2.head.y < 0)
				|| (player2.head.x >= worldWidth || player2.head.y >= worldHeight))
			player2.hasCollided = true;

		// test snakes touching each other
		// heads touching (ewww)
		if (player1.head.x == player2.head.x
				&& player1.head.y == player2.head.y) {
			player1.hasCollided = true;
			player2.hasCollided = true;
		}
		// player1 touching player2
		for (SnakePart part : player2.parts)
			if (player1.head.x == part.x && player1.head.y == part.y)
				player1.hasCollided = true;
		// player1 touching self
		if (player1AI.getClass() != DummyAI.class) // only do the check if it's
													// not the dummyai
			for (SnakePart part : player1.parts)
				if (player1.head.x == part.x && player1.head.y == part.y)
					player1.hasCollided = true;

		// player2 touching player1
		for (SnakePart part : player1.parts)
			if (player2.head.x == part.x && player2.head.y == part.y)
				player2.hasCollided = true;
		// player2 touching self
		if (player2AI.getClass() != DummyAI.class) // only do the check if it's
													// not the dummyai
			for (SnakePart part : player2.parts)
				if (player2.head.x == part.x && player2.head.y == part.y)
					player2.hasCollided = true;

		if (player1.hasCollided && player2.hasCollided)
			return;

		// test snakes touching an apple
		// player 1
		if (player1.head.x == apple.x && player1.head.y == apple.y) {
			player1.eat();
			placeAppleRandomly();
		}
		// player2
		if (player2.head.x == apple.x && player2.head.y == apple.y) {
			player2.eat();
			placeAppleRandomly();
		}
	}

	private void winDetection() {
		if (player1.hasCollided || player2.hasCollided
				|| numberOfSteps >= maxNumberOfSteps)
			gameOver = true;
	}

//	/**
//	 * @return a list of winners, or null if no winner.
//	 */
//	public List<Winner> getWinner() {
//		List<Winner> winners = new ArrayList<Winner>();
//
//		if ((player1.hasCollided && player2.hasCollided)
//				|| numberOfSteps >= maxNumberOfSteps) {
//			if (player1.score == player2.score) {
//				winners.add(new Winner(player2AI, player2));
//				winners.add(new Winner(player1AI, player1));
//
//				return winners;
//			} else if (player1.score > player2.score) {
//				winners.add(new Winner(player1AI, player1));
//
//				return winners;
//			} else if (player2.score > player1.score) {
//				winners.add(new Winner(player2AI, player2));
//
//				return winners;
//			} else {
//				return null;
//			}
//		}
//
//		if (player1.hasCollided)
//			winners.add(new Winner(player2AI, player2));
//		if (player2.hasCollided)
//			winners.add(new Winner(player1AI, player1));
//
//		return winners;
//	}

	public int getPlayer1Score() {
		return player1.score;
	}

	public boolean isPlayer1Winner() {
		if (!gameOver)
			return false;

		// if they've both collided OR the max number of steps have occurred
		if (player1.hasCollided && player2.hasCollided
				|| numberOfSteps >= maxNumberOfSteps)
			// if the score is greater than or the same
			if (player1.score >= player2.score)
				return true;
			else 
				return false;

		if (player2.hasCollided)
			return true;
		
		//if player1.hasCollided
		return false;
	}

	public int getPlayer2Score() {
		return player2.score;
	}
	
	public boolean isPlayer2Winner() {
		if (!gameOver)
			return false;

		// if they've both collided OR the max number of steps have occurred
		if (player1.hasCollided && player2.hasCollided
				|| numberOfSteps >= maxNumberOfSteps)
			// if the score is greater than or the same
			if (player2.score >= player1.score)
				return true;
			else 
				return false;

		if (player1.hasCollided)
			return true;
		
		//if player2.hasCollided
		return false;
	}

	public boolean gameOver() {
		return gameOver;
	}

	public int getMaxNumberOfSteps() {
		return maxNumberOfSteps;
	}

	public void setMaxNumberOfSteps(int maxNumberOfSteps) {
		this.maxNumberOfSteps = maxNumberOfSteps;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

//	public ArrayList<Loser> getLoser() {
//		ArrayList<Loser> losers = new ArrayList<>();
//
//		if (player1.hasCollided)
//			losers.add(new Loser(player1AI, player1));
//		if (player2.hasCollided)
//			losers.add(new Loser(player2AI, player2));
//
//		return losers;
//
//	}

	private void placeAppleRandomly() {
		apple.y = random.nextInt(worldHeight);
		apple.x = random.nextInt(worldWidth);

		// TODO safely place apple
		// place all spots into bag
		// remove taken spots
		// random from that bag
	}
}
