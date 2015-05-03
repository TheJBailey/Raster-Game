package com.knowgravity.raster_game.graphics.ui.element;

import com.knowgravity.raster_game.Action;
import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.ui.UIState;
import com.knowgravity.raster_game.util.maths.Coordinate.Bounds;

public abstract class UIElement {

	protected Bounds bounds;
	Action action;
	Sprite sprite;
	UIState state;
	protected boolean fixed = false;

	public UIElement(Bounds b, Sprite s, Action a, UIState state) {
		bounds = b;
		sprite = s;
		action = a;
		this.state = state;
	}

	public void doAction() {
		action.method();
	}

	protected abstract void pollState();

	public abstract void update();

	public void render(Screen screen) {
		screen.renderSprite((int) bounds.x, (int) bounds.y, sprite, state.isFixed(), 0);
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
