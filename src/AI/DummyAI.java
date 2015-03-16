package AI;

import Game.Direction;

public class DummyAI implements AI {
	private String name = "Dummy";
	private String[] authors = {"Pete"};
	
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
		
		return null;
	}

}
