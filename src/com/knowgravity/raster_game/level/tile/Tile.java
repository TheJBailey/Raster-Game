package com.knowgravity.raster_game.level.tile;

import java.util.ArrayList;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.sprite.SpriteSheet;
import com.knowgravity.raster_game.level.tile.tiles.DirtTile;
import com.knowgravity.raster_game.level.tile.tiles.GrassTile;
import com.knowgravity.raster_game.level.tile.tiles.MiscTile;
import com.knowgravity.raster_game.level.tile.tiles.VoidTile;
import com.knowgravity.raster_game.util.maths.MathUtil;

public abstract class Tile {

	protected static ArrayList<Tile> tiles = new ArrayList<Tile>();

	public static Tile outOfBounds = new VoidTile(new Sprite(1, 0, 32, SpriteSheet.basic));
	
	public static Tile background = new MiscTile(new Sprite(32, 0x00000000), "BACKGROUND");
	public static Tile unknown = new MiscTile(new Sprite(32, 0xFFD940), "VOID");

	public static Tile grass = new GrassTile(new Sprite(0, 0, 32, SpriteSheet.basic));
	public static Tile dirt1 = new DirtTile(new Sprite(1, 0, 32, SpriteSheet.basic));
	public static Tile dirt2 = new DirtTile(new Sprite(2, 0, 32, SpriteSheet.basic));


	protected Sprite sprite;
	protected String name = "TILE";

	public Tile(Sprite sprite, String name) {
		this.sprite = sprite;
		this.name = name;
		tiles.add(this);
	}

	public void render(int x, int y, int shift, Screen screen) {
		screen.renderTile(x << shift, y << shift, sprite);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getWidth() {
		return sprite.getWidth();
	}

	public int getHeight() {
		return sprite.getHeight();
	}

	public String toString() {
		return "TILE::" + name;
	}

	public abstract boolean isSolid();

	public static Tile fetch() {
		return tiles.get(MathUtil.random.nextInt(tiles.size()));
	}
}
