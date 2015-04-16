package com.knowgravity.raster_game.graphics.sprite;

import java.util.ArrayList;

public class SpriteSet {

	private int pState = -1;

	private ArrayList<AnimatedSprite> aniSprites = new ArrayList<AnimatedSprite>();

	public SpriteSet(AnimatedSprite... sprites) {
		for (AnimatedSprite sprite : sprites)
			aniSprites.add(sprite);
	}

	public Sprite update(int state) {
		if (pState == -1) pState = state;
		if (pState != state) aniSprites.get(pState).reset();
		pState = state;
		if (state < aniSprites.size() && aniSprites.get(state) != null) return aniSprites.get(state).update();
		return aniSprites.get(0).getFrame(0);
	}

	public AnimatedSprite getAnimatedSprite(int state) {
		return aniSprites.get(state);
	}

	public Sprite get(int state, int frame) {

		return aniSprites.get(state).getFrame(frame);
	}
}
