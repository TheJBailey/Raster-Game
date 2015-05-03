package com.knowgravity.raster_game;

import com.knowgravity.raster_game.graphics.ui.states.StartMenu;

public interface Action {

	public void method();

	public static class Actions {
		public static void addStartMenu() {
			Game.getUIManager().addUIState(new StartMenu());
		}

		public static void StartGame() {
			Game.load();
		}
	}

}
