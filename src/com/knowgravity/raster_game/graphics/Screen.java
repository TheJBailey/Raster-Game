package com.knowgravity.raster_game.graphics;

import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.util.ColorUtil;
import com.knowgravity.raster_game.util.maths.Coordinate;

public class Screen {

	private static int[] pixels;
	private static int width, height;
	private Coordinate offset;

	public Screen(int w, int h) {
		width = w;
		height = h;
		pixels = new int[w * h];
		offset = new Coordinate();
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {
			xp -= offset.x;
			yp -= offset.y;
		}
		for (int y = 0; y < height; y++) {
			int ya = y + yp;
			for (int x = 0; x < width; x++) {
				int xa = x + xp;
				int tCol = color;
				int bCol = pixels[xa + ya * Screen.width];
				pixels[xa + ya * Screen.width] = ColorUtil.getAlphaComposite(tCol, bCol, ColorUtil.TYPE_OVER);
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= offset.x;
			yp -= offset.y;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int tCol = sprite.getPixels()[x + y * sprite.getWidth()];
				int bCol = pixels[xa + ya * Screen.width];
				pixels[xa + ya * Screen.width] = ColorUtil.getAlphaComposite(tCol, bCol, ColorUtil.TYPE_OVER);
			}
		}
	}

	public void renderTile(int xp, int yp, Sprite sprite) {
		xp -= offset.x;
		yp -= offset.y;
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * Screen.width] = sprite.getPixels()[x + y * sprite.getWidth()];
			}
		}
	}

	public void renderRaster(int xp, int yp, int rasterWidth, int rasterHeight, int[] raster) {
		for (int y = 0; y < rasterHeight; y++) {
			int ya = y + yp;
			for (int x = 0; x < rasterWidth; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = raster[x + y * rasterWidth];
			}
		}
	}

	public void setOffset(double x, double y) {
		offset = new Coordinate(x, y);
	}

	public Coordinate getOffset() {
		return offset;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static int[] getPixels() {
		return pixels;
	}
}
