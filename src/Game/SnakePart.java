package Game;

public class SnakePart extends GameObject {

	public SnakePart(int x, int y) {
		super(x, y);
	}

	public SnakePart(SnakePart head) {
		super(head.x, head.y);
	}
}
