package com.knowgravity.raster_game.graphics.sprite;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.knowgravity.raster_game.util.IOUtil;

public class SpriteSheet {

	public static SpriteSheet basic = new SpriteSheet("/res/spritesheets/basic_sheet");
	public static SpriteSheet player = new SpriteSheet("/res/spritesheets/player_sheet");

	private String path;
	private BufferedImage image;
	private int[] pixels;
	private int width, height;

	public SpriteSheet(String path) {
		this.path = path;
		load();
	}

	private void load() {
		image = IOUtil.loadImageResource(path + "/sheet.png");
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
