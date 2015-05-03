package com.knowgravity.raster_game.graphics.ui;

import java.util.ArrayList;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.ui.element.UIElement;

public abstract class UIState {

	protected ArrayList<UIElement> elements = new ArrayList<UIElement>();

	protected boolean fixed = false;

	protected void updateElements() {
		for (int i = 0; i < elements.size(); i++)
			elements.get(i).update();
	}

	protected void renderElements(Screen screen) {
		for (UIElement e : elements)
			e.render(screen);
	}

	public abstract void update();

	public abstract void render(Screen screen);

	public boolean isFixed() {
		return fixed;
	}

}
