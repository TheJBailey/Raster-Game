package com.knowgravity.raster_game.level.tile.tiles;

import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.level.tile.Tile;
import com.knowgravity.raster_game.util.maths.MathUtil;

public class DirtTile extends Tile {

	public static int QUANTITY;

	public DirtTile(Sprite sprite) {
		super(sprite, "DIRT");
		QUANTITY++;
	}

	public static Tile fetch() {
		switch (MathUtil.random.nextInt(QUANTITY)) {
		case 1:
			return Tile.dirt2;
		default:
			return Tile.dirt1;
		}
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
