package com.knowgravity.raster_game.graphics.sprite.Sprites;

import com.knowgravity.raster_game.graphics.sprite.AnimatedSprite;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.sprite.SpriteSet;
import com.knowgravity.raster_game.graphics.sprite.SpriteSheet;
import com.knowgravity.raster_game.util.maths.Coordinate;

public class Sprites {

	public static class Player {
		public static SpriteSet LINK_SPRITESET = new SpriteSet(new AnimatedSprite[] {
				new AnimatedSprite(0, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(0, 0) }),
				new AnimatedSprite(5, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(0, 0), new Coordinate(1, 0),
						new Coordinate(2, 0) }),
				new AnimatedSprite(6, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(0, 2), new Coordinate(1, 2),
						new Coordinate(2, 2) }),
				new AnimatedSprite(0, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(2, 0) }),
				new AnimatedSprite(5, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(0, 1), new Coordinate(1, 1),
						new Coordinate(2, 1) }),
				null,
				new AnimatedSprite(0, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(2, 0) }),
				new AnimatedSprite(10, SpriteSheet.PLAYER, 32,
						new Coordinate[] { new Coordinate(3, 0), new Coordinate(4, 0) }).disableLoop(),
				new AnimatedSprite(20, SpriteSheet.PLAYER, 32,
						new Coordinate[] { new Coordinate(3, 2), new Coordinate(4, 2) }).disableLoop(),
				new AnimatedSprite(0, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(2, 4) }).disableLoop() });

	}

	public static class Menus {

		public static class Loading {
			public static Sprite content = new Sprite(0, 0, 96, 32, SpriteSheet.LOADING_MENU);
		}

		public static class Start {
			public static class Buttons {
				public static class Play {
					public static Sprite still = new Sprite(0, 0, 96, 32, SpriteSheet.START_MENU);
					public static Sprite hover = new Sprite(0, 1, 96, 32, SpriteSheet.START_MENU);
					public static Sprite active = new Sprite(0, 2, 96, 32, SpriteSheet.START_MENU);
				}
			}
		}
	}

}
