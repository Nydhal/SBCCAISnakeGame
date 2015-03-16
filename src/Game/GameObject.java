package Game;

public class GameObject {
	public int x;
	public int y;
	
	public GameObject() {
	}
	
	public GameObject(GameObject gameObject) {
		x = gameObject.x;
		y = gameObject.y;
	}

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
