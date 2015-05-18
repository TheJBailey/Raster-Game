package com.knowgravity.raster_game.graphics.ui.state;

import java.util.ArrayList;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.ui.element.UIElement;
import com.knowgravity.raster_game.graphics.ui.layout.UILayout;

public abstract class UIState {

	protected ArrayList<UIElement> elements = new ArrayList<UIElement>();

	protected UILayout layout;
	protected boolean fixed = false;

	public UIState(UILayout layout) {
		this.layout = layout;
	}
	
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
