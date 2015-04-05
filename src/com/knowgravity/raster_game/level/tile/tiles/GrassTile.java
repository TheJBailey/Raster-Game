package com.knowgravity.raster_game.level.tile.tiles;

import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.level.tile.Tile;
import com.knowgravity.raster_game.util.maths.MathUtil;

public class GrassTile extends Tile {

	public static int QUANTITY;

	public GrassTile(Sprite sprite) {
		super(sprite, "GRASS");
		QUANTITY++;
	}

	public static Tile fetch() {
		switch (MathUtil.random.nextInt(QUANTITY)) {
		default:
			return Tile.grass;
		}
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	// FIXME Question for Mr PIGG
	/*
	 * how efficent would it be to get the surrounding tiles of a tile (8 tiles) and check them here then return a
	 * certain tile
	 */
}
