package com.knowgravity.raster_game.entity;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.Coordinate.Bounds;

public abstract class Entity {

	private Level level;
	private Bounds bounds;
	private Sprite sprite;

	private boolean removed = false;

	public Entity(double x, double y, double width, double height, int color, Level level) {
		bounds = new Bounds(x, y, width, height);
		sprite = new Sprite((int) width, (int) height, color);
		this.level = level;
	}

	public Entity(double x, double y, double width, double height, Sprite sprite, Level level) {
		bounds = new Bounds(x, y, width, height);
		this.sprite = sprite;
		this.level = level;
	}

	public Entity(double x, double y, Sprite sprite, Level level) {
		bounds = new Bounds(x, y, sprite.getWidth(), sprite.getHeight());
		this.sprite = sprite;
		this.level = level;
	}

	public abstract void update();

	public abstract void kill();

	public void render(Screen screen) {
		screen.renderSprite((int) bounds.x, (int) bounds.y, sprite, true);
	}

	public Level getLevel() {
		return level;
	}

	public Bounds getBounds() {
		return bounds;
	}

	public double getX() {
		return bounds.x;
	}

	public double getY() {
		return bounds.y;
	}

	public double getWidth() {
		return bounds.width;
	}

	public double getHeight() {
		return bounds.height;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void remove() {
		removed = true;
	}

}
