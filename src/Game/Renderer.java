package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Renderer {
	private Game game;
	private Image image;
	private Graphics imageGraphics;
	
	private int imageMaxWidth;
	private int imageMaxHeight;
	
	private int imageWidth;
	private int imageHeight;
	
	public int getImageWidth() {
		return imageWidth;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}
	
	private Color player1Color = Color.ORANGE;
	private Color player2Color = Color.BLUE;
	private Color appleColor = Color.RED;
	
	private int pixelPerBlockX;
	private int pixelPerBlockY;
	
	public Renderer(Game game, Dimension startDimension, Dimension maxDimensions) {
		this.game = game;
		
		imageMaxWidth = (int) maxDimensions.getWidth();
		imageMaxHeight = (int) maxDimensions.getHeight();
		
		int width = (int) startDimension.getWidth();
		int height = (int) startDimension.getHeight();
		
		resize(width, height);
		
		image = new BufferedImage(imageMaxWidth, imageMaxHeight, BufferedImage.TYPE_INT_ARGB);
		imageGraphics = image.getGraphics();
	}

	public Image render() {
		//clear image
		clearImage();
		
		//draw arena
		drawArena();
		//draw player 1
		drawPlayer(game.player1, player1Color);
		//draw player 2
		drawPlayer(game.player2, player2Color);
		//draw apple
		drawBlock(game.apple, appleColor);
		
		return image;
	}

	private void clearImage() {
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.fillRect(0, 0, imageMaxWidth, imageMaxHeight);
	}

	private void drawArena() {
		imageGraphics.setColor(Color.BLACK);
		imageGraphics.drawRect(0, 0, imageWidth - 1, imageHeight - 1);
	}

	private void drawPlayer(Snake player, Color playerColor) {
		//draw head
		drawBlock(player.head, playerColor);
		
		//draw body
		for(GameObject part : player.parts)
			drawBlock(part, playerColor);
	}

	private void drawBlock(GameObject object, Color objectColor) {
		int x = object.x * pixelPerBlockX;
		int y = object.y * pixelPerBlockY;
		int width = pixelPerBlockX;
		int height = pixelPerBlockY;
		
		imageGraphics.setColor(objectColor);
		imageGraphics.fillRect(x, y, width, height);
	}

	public static String renderAsText(Game game) {
		String[][] map = new String[game.worldWidth][game.worldHeight];

		for (int y = 0; y < game.worldHeight; y++) {
			for (int x = 0; x < game.worldWidth; x++) {
				map[x][y] = "_";
			}
		}

		map[game.apple.x][game.apple.y] = "A";

		for (SnakePart part : game.player1.parts)
			map[part.x][part.y] = "+";

		for (SnakePart part : game.player2.parts)
			map[part.x][part.y] = "-";

		try {
			map[game.player1.head.x][game.player1.head.y] = "1";
		} catch (Exception e) {
			System.out.println("this is expected, dont worry, but there is still a game over");
			e.printStackTrace();
		}

		try {
			map[game.player2.head.x][game.player2.head.y] = "2";
		} catch (Exception e) {
			System.out.println("this is expected, dont worry, but there is still a game over");
			e.printStackTrace();
		}

		StringBuilder out = new StringBuilder();

		for (int y = 0; y < game.worldHeight; y++) {
			for (int x = 0; x < game.worldWidth; x++) {
				out.append(map[x][y]);
			}
			out.append("\n");
		}

		return out.toString();
	}

	public void resize(int width, int height) {
		// force a square image
		if (width < height)
			height = width;
		else
			width = height;
		
		imageWidth = width;
		imageHeight = height;
		
		pixelPerBlockX = imageWidth / game.worldWidth;
		pixelPerBlockY = imageHeight / game.worldHeight;
	}
}
