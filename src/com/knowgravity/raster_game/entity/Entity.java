package com.knowgravity.raster_game.entity;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.level.tile.Tile;
import com.knowgravity.raster_game.util.maths.Coordinate.Bounds;

public abstract class Entity {

	public static int FLIP_NONE = 0;
	public static int FLIP_X = 1;
	public static int FLIP_Y = 2;
	public static int FLIP_BOTH = 3;

	public static int DIR_UP = 1;
	public static int DIR_LEFT = 2;
	public static int DIR_DOWN = 3;
	public static int DIR_RIGHT = 0;

	public static int COLLISION_BOTTOM_RIGHT = 0;
	public static int COLLISION_BOTTOM_LEFT = 1;
	public static int COLLISION_TOP_RIGHT = 2;
	public static int COLLISION_TOP_LEFT = 3;
	public static int COLLISION_NONE = -1;

	protected Level level;

	protected Sprite sprite;

	protected Bounds bounds;
	protected int flip;

	protected int xDir, yDir;
	protected int collisionDir;

	protected boolean removed = false;

	public Entity(double x, double y, Sprite sprite, Level level) {
		bounds = new Bounds(x, y, sprite.getWidth(), sprite.getHeight());
		this.sprite = sprite;
		this.level = level;
	}

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

	public int collision(int xa, int ya) {
		// System.out.println(xa + " :: " + ya);
		for (int c = 0; c < 4; c++) {
			double xc, yc;
			if (flip == 2 || flip == 3) {
				xc = ((bounds.x + xa) + c % 2 * 17 + 6) / level.getTileSize();
				yc = ((bounds.y + ya) + c / 2 * 25 + 0) / level.getTileSize();
			} else {
				xc = ((bounds.x + xa) + c % 2 * 17 + 6) / level.getTileSize();
				yc = ((bounds.y + ya) + c / 2 * 25 + 6) / level.getTileSize();
			}
			Tile tile = level.getTile((int) xc, (int) yc);
			if (tile.isSolid()) return c;
		}
		return -1;
	}

	public abstract void update();

	public abstract void kill();

	public void render(Screen screen) {
		screen.renderSprite((int) bounds.x, (int) bounds.y, sprite, true, flip);
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
