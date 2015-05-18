package com.knowgravity.raster_game.graphics.ui.layout.layouts;

import java.util.ArrayList;
import java.util.List;

import com.knowgravity.raster_game.graphics.ui.element.UIElement;
import com.knowgravity.raster_game.graphics.ui.layout.UILayout;

public class GroupLayout extends UILayout {

	private List<UILayout> layoutGroup = new ArrayList<UILayout>();
	private int[] filter;

	public GroupLayout(int[] filter, UILayout... layouts) {
		super();
		this.filter = filter;
		for (UILayout layout : layouts)
			layoutGroup.add(layout);
	}

	public GroupLayout(int x, int y, int width, int height, int[] filter, UILayout... layouts) {
		super(x, y, width, height);
		this.filter = filter;
		for (UILayout layout : layouts)
			layoutGroup.add(layout);
	}

	@Override
	public void apply(List<UIElement> elements) {
		List<UIElement> cElements;
		for (int i = 0; i < filter.length; i++) {
			cElements = new ArrayList<UIElement>();

			for (int j = 0; j < filter[i]; j++)
				cElements.add(elements.get(j));

			layoutGroup.get(i).apply(cElements);
		}
	}

	public void setFilter(int[] filter) {
		this.filter = filter;
	}

}
