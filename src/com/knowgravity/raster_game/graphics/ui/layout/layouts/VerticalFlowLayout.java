package com.knowgravity.raster_game.graphics.ui.layout.layouts;

import java.util.List;

import com.knowgravity.raster_game.graphics.ui.element.UIElement;
import com.knowgravity.raster_game.graphics.ui.layout.UILayout;

public class VerticalFlowLayout extends UILayout {

	private int limit;

	public VerticalFlowLayout(int limit, int align) {
		super(align);
		this.limit = limit;
	}

	public VerticalFlowLayout(int x, int y, int width, int height, int limit, int align) {
		super(x, y, width, height, align);
		this.limit = limit;
	}

	@Override
	public void apply(List<UIElement> elements) {
		int xs = 0, ys = 0;
		int verticalLimit = (int) Math.ceil((double) elements.size() / (double) limit);

		if (bounds.x == -1 && bounds.y == -1) center(2, verticalLimit, limit, elements);
		else {
			if (bounds.x != -1) center(1, verticalLimit, limit, elements);
			if (bounds.y != -1) center(0, verticalLimit, limit, elements);
		}

		for (int y = 0; y < verticalLimit; y++) {
			int ypad = top + y * (bottom + top);
			int yp = (int) bounds.y + ypad;

			for (int x = 0; x < limit; x++) {
				int xpad = left + x * (left + right);
				int xp = (int) bounds.x + xpad;

				UIElement elem = elements.get(x + y * limit);
				elem.setLocation(xp + xs, yp + ys);

				xs += elem.getWidth();
				ys += elem.getHeight();
			}
		}
	}
}
