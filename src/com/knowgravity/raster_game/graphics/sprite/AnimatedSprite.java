package com.knowgravity.raster_game.graphics.sprite;

import java.util.ArrayList;

import com.knowgravity.raster_game.util.maths.Coordinate;
import com.knowgravity.raster_game.util.maths.Coordinate.Bounds;

public class AnimatedSprite {

	private static int tick;

	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	private int fRate, cFrame;
	private boolean loop = true;

	public AnimatedSprite(int aniRate, Sprite... s) {
		for (Sprite sprite : s)
			sprites.add(sprite);
	}

	public AnimatedSprite(int rate, SpriteSheet sheet, Bounds... bounds) {
		for (Bounds b : bounds) {
			sprites.add(new Sprite((int) b.x, (int) b.y, (int) b.width, (int) b.height, sheet));
		}
		fRate = rate;
	}

	public AnimatedSprite(int rate, SpriteSheet sheet, int size, Coordinate... coords) {
		for (Coordinate coord : coords) {
			sprites.add(new Sprite((int) coord.x, (int) coord.y, size, size, sheet));
		}
		fRate = rate;
	}

	public AnimatedSprite(int rate, SpriteSheet sheet, int width, int height, Coordinate... coords) {
		for (Coordinate coord : coords) {
			sprites.add(new Sprite((int) coord.x, (int) coord.y, width, height, sheet));
		}
		fRate = rate;
	}

	// TODO static ticks doesnt work
	public Sprite update() {
		tick++;
		if (tick > 3600) tick = 0;

		if (fRate > 0 && tick % fRate == 0) cFrame++;

		if (loop && cFrame >= sprites.size()) cFrame = 0;
		if (!loop && cFrame >= sprites.size()) return sprites.get(getNumberOfFrames() - 1);
		return sprites.get(cFrame);
	}

	public void setFrameRate(int rate) {
		fRate = rate;
	}

	public boolean isDone() {
		return (cFrame >= sprites.size());
	}

	public Sprite getCurrentFrame() {
		return sprites.get(cFrame);
	}

	public void reset() {
		cFrame = 0;
	}

	public Sprite getFrame(int frame) {
		return sprites.get(frame);
	}

	public int getNumberOfFrames() {
		return sprites.size();
	}

	public int getFrameRate() {
		return fRate;
	}

	public AnimatedSprite disableLoop() {
		loop = false;
		return this;
	}

}
