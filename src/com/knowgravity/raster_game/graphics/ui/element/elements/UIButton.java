package com.knowgravity.raster_game.graphics.ui.element.elements;

import static com.knowgravity.raster_game.Game.getInput;

import com.knowgravity.raster_game.Action;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.ui.UIState;
import com.knowgravity.raster_game.graphics.ui.element.UIElement;
import com.knowgravity.raster_game.util.input.Input;
import com.knowgravity.raster_game.util.maths.Coordinate.Bounds;

public class UIButton extends UIElement {

	public enum State {
		hover, still, active;
	}

	State state, pState;

	private boolean released, pressed;

	public UIButton(int x, int y, Sprite s, Action a, UIState state) {
		super(new Bounds(x, y, s.getWidth(), s.getHeight()), s, a, state);
	}

	@Override
	protected void pollState() {
		pState = state;
		if (bounds.containsMouse()) {
			state = State.hover;
			if (getInput().getMouseButton() == Input.LMB) state = State.active;
		} else state = State.still;
	}

	@Override
	public void update() {
		pollState();
		pressed = released = false;
		if (state == State.active && pState != State.active) pressed = true;
		if (state != State.active && pState == State.active && bounds.containsMouse()) released = true;
	}

	public boolean ispressed() {
		return pressed;
	}

	public boolean isReleased() {
		return released;
	}

	public State getState() {
		return state;
	}

}
