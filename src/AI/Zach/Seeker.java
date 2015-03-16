package AI.Zach;

import AI.AI;
import AI.GameMap;
import Game.Direction;

public class Seeker implements AI {

	private String name = "Seeker";
	private String[] authors = { "Me, Myself, and Irene" };
	private Direction direction;

	public Seeker(String string) {
		name += string;
	}
	
	public Seeker() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String[] getAuthors() {
		return authors;
	}

	@Override
	public Direction getDirection(GameMap map) {

		if (map.apple.x < map.self.get(0).x) {
			if (map.isSafe(direction.LEFT))
				direction = direction.LEFT;
			else if (map.apple.y < map.self.get(0).y)
				direction = direction.DOWN;
			else if (map.apple.y < map.self.get(0).y)
				direction = direction.UP;
		} else if (map.apple.x > map.self.get(0).x) {
			if (map.isSafe(direction.RIGHT))
				direction = direction.RIGHT;
			else if (map.apple.y < map.self.get(0).y)
				direction = direction.DOWN;
			else if (map.apple.y < map.self.get(0).y)
				direction = direction.UP;
		} else if (map.apple.x == map.self.get(0).x) {
			if (map.apple.y < map.self.get(0).y) {
				if (map.isSafe(direction.UP))
					direction = direction.UP;
			} else
				direction = direction.DOWN;
		}
		return direction;

	}
}
