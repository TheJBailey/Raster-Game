package com.knowgravity.raster_game.level.tile.tiles;

import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.level.tile.Tile;
import com.knowgravity.raster_game.util.maths.MathUtil;

public class MiscTile extends Tile {

	public static int QUANTITY;

	public MiscTile(Sprite sprite, String name) {
		super(sprite, name);
		QUANTITY++;
	}

	public static Tile fetch() {
		switch (MathUtil.random.nextInt(QUANTITY)) {
		case 1:
			return Tile.background;
		default:
			return Tile.unknown;
		}
	}

	@Override
	public boolean isSolid() {
		return false;
	}

}
