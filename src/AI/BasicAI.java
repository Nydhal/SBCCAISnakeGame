package AI;

import Game.Direction;

public class BasicAI implements AI {
	private String name = "BasicAI";
	private String[] authors = {"Pete", "Zach", "Nidal"};
	private Direction direction;
	
	public BasicAI(Direction initialDirection) {
		direction = initialDirection;
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
		
		System.out.println(map.getVerticalDistance(map.self.get(0), map.apple));
		System.out.println(map.getHorizontalDistance(map.self.get(0), map.apple));
		
		return direction;
	}

}
