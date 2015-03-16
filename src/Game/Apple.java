package Game;

public class Apple extends GameObject {

	public Apple(int x, int y) {
		super(x, y);
	}
	
	public Apple(Apple apple) {
		super(apple.x, apple.y);
	}
	
}
