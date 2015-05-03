package com.knowgravity.raster_game.graphics.ui.states;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.sprite.Sprites.Sprites;
import com.knowgravity.raster_game.graphics.ui.UIState;

public class LoadingMenu extends UIState {

	@Override
	public void update() {}

	@Override
	public void render(Screen screen) {
		Sprite sprite = Sprites.Menus.Loading.content;
		int xp = (Screen.getWidth() - sprite.getWidth()) / 2;
		int yp = (Screen.getHeight() - sprite.getHeight()) / 2;
		screen.renderSprite(xp, yp, sprite, fixed, Sprite.FLIP_NONE);
	}

}
