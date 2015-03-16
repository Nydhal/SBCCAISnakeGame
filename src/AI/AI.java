package AI;

import Game.Direction;

public interface AI {
	public String getName();
	public String[] getAuthors();
	public Direction getDirection(GameMap map);
}
