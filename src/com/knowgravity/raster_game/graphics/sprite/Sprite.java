package com.knowgravity.raster_game.graphics.sprite;

public class Sprite {

	public static int FLIP_NONE = 0;
	public static int FLIP_X = 1;
	public static int FLIP_Y = 2;
	public static int FLIP_BOTH = 3;

	private int[] pixels;
	private int width, height;
	private SpriteSheet sheet;

	private int color;
	private int size;

	public Sprite(int size, int color) {
		this.size = size;
		width = height = size;
		pixels = new int[width * height];

		setColor(color);
	}

	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		if (width != height) size = -1;
		else size = width;

		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int x, int y, int size, SpriteSheet sheet) {
		this.sheet = sheet;
		this.size = size;
		width = height = size;
		pixels = new int[width * height];

		load(x, y);
	}

	public Sprite(int x, int y, int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		if (width != height) size = -1;
		else size = width;
		pixels = new int[width * height];
		load(x, y);
	}

	public void load(int xp, int yp) {
		for (int y = 0; y < height; y++) {
			int ya = y + yp * height;
			for (int x = 0; x < width; x++) {
				int xa = x + xp * width;
				pixels[x + y * width] = sheet.getPixels()[xa + ya * sheet.getWidth()];
			}
		}

	}

	public void setColor(int color) {
		this.color = color;
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	public SpriteSheet getSheet() {
		return sheet;
	}

	public int getColor() {
		return color;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSize() {
		return size;
	}

	public int[] getPixels() {
		return pixels;
	}
}
