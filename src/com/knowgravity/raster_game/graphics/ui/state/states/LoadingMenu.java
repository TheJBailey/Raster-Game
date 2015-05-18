package com.knowgravity.raster_game.graphics.ui.state.states;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.sprite.Sprites.Sprites;
import com.knowgravity.raster_game.graphics.ui.layout.layouts.VerticalFlowLayout;
import com.knowgravity.raster_game.graphics.ui.state.UIState;

public class LoadingMenu extends UIState {

	public LoadingMenu() {
		super(new VerticalFlowLayout(-1, -1));
	}

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
