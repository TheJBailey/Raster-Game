package com.knowgravity.raster_game.graphics.ui.states;

import com.knowgravity.raster_game.Action.Actions;
import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.sprite.Sprites.Sprites;
import com.knowgravity.raster_game.graphics.ui.UIState;
import com.knowgravity.raster_game.graphics.ui.element.elements.UIButton;

public class StartMenu extends UIState {

	public UIButton play;

	public StartMenu() {
		play = new UIButton(100, 100, Sprites.Menus.Start.Buttons.Play.still, Actions::StartGame, this);
		elements.add(play);
	}

	@Override
	public void update() {
		updateElements();
		if (play.getState() == UIButton.State.still) play.setSprite(Sprites.Menus.Start.Buttons.Play.still);
		if (play.getState() == UIButton.State.hover) play.setSprite(Sprites.Menus.Start.Buttons.Play.hover);
		if (play.getState() == UIButton.State.active) play.setSprite(Sprites.Menus.Start.Buttons.Play.active);
		if (play.isReleased()) play.doAction();
	}

	@Override
	public void render(Screen screen) {
		renderElements(screen);
	}

}
