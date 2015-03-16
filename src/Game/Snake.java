package Game;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Snake {
	List<SnakePart> parts = new LinkedList<SnakePart>();
	SnakePart head;
	Direction direction;
	boolean hasCollided = false;
	int score = 0;

	public Snake(int x, int y) {
		head = new SnakePart(x, y);
		parts.add(new SnakePart(head));
		parts.add(new SnakePart(head));
		parts.add(new SnakePart(head));
	}

	void move() {
		// move last tail part to the head location, and place it to the beginning of the list
		SnakePart end = parts.remove(parts.size() - 1);
		end.x = head.x;
		end.y = head.y;
		parts.add(0, end);
		

		// move head
		if (direction == Direction.UP)
			head.y--;
		else if (direction == Direction.DOWN)
			head.y++;
		else if (direction == Direction.LEFT)
			head.x--;
		else if (direction == Direction.RIGHT)
			head.x++;
	}

	void eat() {
		score++;
		SnakePart end = parts.get(parts.size() - 1);
		parts.add(new SnakePart(end.x, end.y));
	}
}
