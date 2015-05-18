package com.knowgravity.raster_game.graphics.ui.layout;

import java.util.List;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.ui.element.UIElement;
import com.knowgravity.raster_game.util.maths.Coordinate.Bounds;

public abstract class UILayout {

	public static int LEFT_ALIGN = -1;
	public static int CENTER_ALIGN = 0;
	public static int RIGHT_ALIGN = 1;

	protected Bounds bounds;
	protected int align = CENTER_ALIGN;
	protected int topMargin, LeftMargin;
	protected int left, right, bottom, top;

	public UILayout() {
		bounds = new Bounds(-1, -1, Screen.getWidth(), Screen.getHeight());
	}

	public UILayout(int align) {
		bounds = new Bounds(-1, -1, Screen.getWidth(), Screen.getHeight());
		this.align = align;
	}

	public UILayout(int x, int y, int width, int height) {
		bounds = new Bounds(x, y, width, height);
	}

	public UILayout(int x, int y, int width, int height, int align) {
		bounds = new Bounds(x, y, width, height);
		this.align = align;
	}

	protected void center(int axis, int vertical, int horizontal, List<UIElement> elements) {
		if (axis == 0 || axis == 2) {
			int maxWidth = 0;
			for (int y = 0; y < vertical; y++) {
				int rowWidth = 0;
				for (int x = 0; x < horizontal; x++) {
					rowWidth += elements.get(x + y * horizontal).getWidth();
				}
				if (maxWidth < rowWidth) maxWidth = rowWidth;
			}
			maxWidth += left + horizontal * (left + right) + right;
			bounds.x = (bounds.width - maxWidth) / 2;

			if (axis == 1 || axis == 2) {
				int maxHeight = 0;
				for (int y = 0; y < vertical; y++) {
					int rowHeight = 0;
					for (int x = 0; x < horizontal; x++) {
						int elemHeight = elements.get(x + y * horizontal).getHeight();
						if (rowHeight < elemHeight) rowHeight = elemHeight;
					}
					maxHeight += rowHeight;
				}
				maxHeight += top + horizontal * (top + bottom) + bottom;
				bounds.y = (bounds.height - maxHeight) / 2;
			}
		}
	}

	public void setPadding(int all) {
		left = right = top = bottom = all;
	}

	public void setPadding(int top_bottom, int right_left) {
		top = bottom = top_bottom;
		right = left = right_left;
	}

	public void setPadding(int top, int right, int bottom, int left) {
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	public abstract void apply(List<UIElement> elements);

}
