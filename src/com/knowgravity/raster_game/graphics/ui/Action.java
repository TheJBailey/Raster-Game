package com.knowgravity.raster_game.graphics.ui;

import com.knowgravity.raster_game.Game;
import com.knowgravity.raster_game.graphics.ui.state.states.StartMenu;

public interface Action {

	public void method();

	public static class MenuActions {
		public static void addStartMenu() {
			Game.getUIManager().addUIState(new StartMenu());
		}
	}

	public static class GameActions {
		public static void StartGame() {
			Game.load();
		}
	}

}
