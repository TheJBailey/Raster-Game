package com.knowgravity.raster_game.graphics.ui;

import java.util.ArrayList;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.ui.state.UIState;

public class UIManager {

	ArrayList<UIState> uiStack = new ArrayList<UIState>();
	UIState cState, pState;

	public UIManager(UIState state) {
		pState = null;
		cState = state;
		uiStack.add(state);
	}

	public void update() {
		if (cState != null) cState.update();
	}

	public void render(Screen screen) {
		if (cState != null) cState.render(screen);
	}

	public void addUIState(UIState state) {
		uiStack.add(state);
		pState = cState;
		cState = state;
	}

	public void removeUIState() {
		uiStack.remove(uiStack.size() - 1);
		if (uiStack.size() > 0) cState = uiStack.get(uiStack.size() - 1);
		else cState = null;
		if (uiStack.size() > 1) pState = uiStack.get(uiStack.size() - 2);
		else pState = null;
	}

	public void previousState() {
		uiStack.remove(uiStack.indexOf(cState));
		cState = pState;
		pState = uiStack.get(uiStack.size() - 2);
	}

}
