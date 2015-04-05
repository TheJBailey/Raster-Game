package com.knowgravity.raster_game.level.tile.tiles;

import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.level.tile.Tile;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite, "VOID");
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
