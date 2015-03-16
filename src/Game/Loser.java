package Game;

import AI.AI;

public class Loser {
	public String name;
	public String[] authors;
	public int score;
	public Direction direction;
	
	public Loser(String name, String[] authors, int score, Direction direction) {
		this.name = name;
		this.authors = authors;
		this.score = score;
		this.direction = direction;
	}

	public Loser(AI playerAI, Snake playerSnake) {
		this.name = playerAI.getName();
		this.authors = playerAI.getAuthors();
		this.score = playerSnake.score;
		this.direction = playerSnake.direction;
	}
}
