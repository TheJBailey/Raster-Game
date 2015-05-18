package com.knowgravity.raster_game;

import com.knowgravity.raster_game.entity.mob.player.Player;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.Coordinate;

public class Loader implements Runnable {

	public String content = "";
	public Game game;

	public Loader(Game game) {
		this.game = game;
	}

	public void update() {}

	public void run() {
		content = "loading level";
		game.setLevel(Level.TEST_LEVEL);

		content = "loading player";
		Coordinate spawnTile = Game.getLevel().getSpawnTile();
		game.setPlayer(new Player((int) spawnTile.x, (int) spawnTile.y, Game.getLevel()));

		content = "re-initializing graphics";
		Game.initGraphics(Game.getLevel().getScale());

		content = "done!";
		Game.getUIManager().removeUIState();
	}

	public void reset() {
		content = "";
	}

}