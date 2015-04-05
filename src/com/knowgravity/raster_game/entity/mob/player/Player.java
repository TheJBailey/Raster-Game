package com.knowgravity.raster_game.entity.mob.player;

import com.knowgravity.raster_game.entity.mob.Mob;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.sprite.SpriteSheet;
import com.knowgravity.raster_game.level.Level;

public class Player extends Mob {
	// TODO Player class

	public static final Sprite SPRITE = new Sprite(0, 0, 32, SpriteSheet.player);

	public Player(double x, double y, Sprite sprite, Level level) {
		super(x * level.getTileSize(), y * level.getTileSize(), sprite, level);
	}

	@Override
	public void move(int xa, int ya) {
	}

	@Override
	public void update() {
	}

	public void kill() {
	}

}
