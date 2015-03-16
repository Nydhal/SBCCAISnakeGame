package AI;

import java.util.List;

import Game.Apple;
import Game.Direction;
import Game.GameObject;
import Game.SnakePart;

public class GameMap {
	/**
	 * Integer representing the <b>width</b> of the board. <li>If your snake is
	 * at a position equal to or greater than this, you die.</li>
	 */
	public int width;

	/**
	 * Integer representing the <b>height</b> of the board. <li>If your snake is
	 * at a position equal to or any greater than this, you die.</li>
	 */
	public int height;

	/**
	 * An ArrayList representing <b>your</b> snake. <li>Position 0 is the head
	 * of your snake.</li> <li>Position 1 through size() - 1 is your tail.</li>
	 * <li>Position size() - 1 is the end of your tail.</li>
	 */
	public List<SnakePart> self;

	/**
	 * An ArrayList representing <b>other</b> snake. <li>Position 0 is the head
	 * of other snake.</li> <li>Position 1 through size() - 1 is other tail.</li>
	 * <li>Position size() - 1 is the end of other tail.</li>
	 */
	public List<SnakePart> opponent;

	/**
	 * An Apple object representing the (one) apple on the board.
	 */
	public Apple apple;

	/**
	 * <b>IMPORTANT!</b> You can cause an {@link ArrayIndexOutOfBoundsException} if you check
	 * this map for a position that isn't possible.
	 * <p>
	 * Use {@link GameMap#isSafe(int, int)} to test individual coordinates.
	 * </p>
	 * <p>
	 * An integer representation of the map
	 * </p>
	 * <li>1's for player1.</li> <li>2's for player2.</li> <li>3's for apples.</li>
	 * <li>0's for empty space.</li>
	 */
	public int[][] map;

	/**
	 * Negative number means the object is Direction.UP.
	 * <p>
	 * Positive number means the object is Direction.DOWN.
	 * </p>
	 * 
	 * @param source
	 * @param destination
	 * @return vertical distance from the object
	 */
	public int getVerticalDistance(GameObject source, GameObject destination) {
		return destination.y - source.y;
	}

	/**
	 * Negative number means the object is Direction.LEFT.
	 * <p>
	 * Positive number means the object is Direction.RIGHT.
	 * </p>
	 * 
	 * @param source
	 * @param destination
	 * @return horizontal distance from the object
	 */
	public int getHorizontalDistance(GameObject source, GameObject destination) {
		return destination.x - source.x;
	}

	/** @see GameMap#isSafe(int, int)
	 * Uses the self object to determine if the particular direction will
	 * result in immediate death.
	 * 
	 * @param direction
	 * @return true or false if the direction is safe.
	 */
	public boolean isSafe(Direction direction) {
		SnakePart head = new SnakePart(self.get(0));

		if (direction == Direction.UP)
			head.y--;
		else if (direction == Direction.DOWN)
			head.y++;
		else if (direction == Direction.LEFT)
			head.x--;
		else if (direction == Direction.RIGHT)
			head.x++;

		return isSafe(head);
	}

	/**
	 * Use this to check the map array to determine if a particular x, y position is safe.
	 * <p>
	 * Open positions and apples are safe
	 * </p>
	 * <p>
	 * This method does bound checking on the x, y position given
	 * </p>
	 * 
	 * @param x
	 * @param y
	 * @return true or false if the position is safe to move to.
	 */
	public boolean isSafe(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return false;

		if (map[x][y] == 3 || map[x][y] == 0)
			return true;

		return false;
	}

	/**
	 * @see GameMap#isSafe(int, int)
	 * @param position
	 * @return true or false if the position is safe to move to.
	 */
	public boolean isSafe(GameObject position) {
		return isSafe(position.x, position.y);
	}
}
