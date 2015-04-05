package com.knowgravity.raster_game.entity.mob;

import com.knowgravity.raster_game.entity.Entity;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.Vector2f;

public abstract class Mob extends Entity {

	public Vector2f vector;

	public Mob(double x, double y, double width, double height, Sprite sprite, Level level) {
		super(x, y, width, height, sprite, level);
	}

	public Mob(double x, double y, Sprite sprite, Level level) {
		super(x, y, sprite, level);
	}

	public abstract void move(int xa, int ya);

}
