package com.knowgravity.raster_game.graphics.sprite;

import java.awt.image.BufferedImage;

import com.knowgravity.raster_game.util.IOUtil;

public class SpriteSheet {

	public static final SpriteSheet BASIC = new SpriteSheet("/res/spritesheets/texture_sheets/");
	public static final SpriteSheet PLAYER = new SpriteSheet("/res/spritesheets/player_sheets/");
	public static final SpriteSheet START_MENU = new SpriteSheet("/res/spritesheets/menu_sheets/start/");
	public static final SpriteSheet LOADING_MENU = new SpriteSheet("/res/spritesheets/menu_sheets/loading/");

	// public static final SpriteSheet MOB_SHEET_1 = new SpriteSheet("/res/spritesheets/mob_sheets/",
	// "mob_sheet_1.png");

	private String path;
	private BufferedImage image;
	private int[] pixels;
	private int width, height;

	public SpriteSheet(String path) {
		this.path = path;
		load(null);
	}

	public SpriteSheet(String path, String name) {
		this.path = path;
		load(name);
	}

	private void load(String name) {
		if (name == null) name = "sheet.png";
		image = IOUtil.loadImageResource(path + name);
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}
}
