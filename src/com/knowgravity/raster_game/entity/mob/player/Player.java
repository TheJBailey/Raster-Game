package com.knowgravity.raster_game.entity.mob.player;

import static com.knowgravity.raster_game.Main.getInput;
import static java.awt.event.KeyEvent.*;

import com.knowgravity.raster_game.entity.mob.Mob;
import com.knowgravity.raster_game.graphics.sprite.AnimatedSprite;
import com.knowgravity.raster_game.graphics.sprite.SpriteSet;
import com.knowgravity.raster_game.graphics.sprite.SpriteSheet;
import com.knowgravity.raster_game.graphics.sprite.Sprites.Sprites;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.Coordinate;

public class Player extends Mob {

	// public static final Sprite SPRITE_1 = new Sprite(0, 0, 32, SpriteSheet.PLAYER);
	// public static final Sprite SPRITE_2 = new Sprite(1, 0, 32, SpriteSheet.PLAYER);
	// public static final Sprite SPRITE_3 = new Sprite(2, 0, 32, SpriteSheet.PLAYER);

	public Player(double x, double y, Level level) {
		super(x * level.getTileSize(), y * level.getTileSize(), Sprites.Player.LINK_SPRITESET, level);
		speed = 2.2;
		rSpeed = speed * 2;
		jumpH = 5.25;
	}

	// @Override
	// public void move(int xa, int ya) {}

	@Override
	public void update() {
		/* -------------- input ---------------- */
		boolean key_a = getInput().getKey(VK_A).isActive(), key_d = getInput().getKey(VK_D).isActive();
		if (key_a || key_d) {
			if (getInput().getKey(VK_SHIFT).isActive()) state = RUNNING;
			else state = WALKING;
			if (key_a) xDir = DIR_LEFT;
			if (key_d) xDir = DIR_RIGHT;
		} else state = IDLE;

		if (getInput().getKey(VK_SPACE).isPressed()) jump(jumpH);
		/* -------------- input ---------------- */

		// System.out.println(xa + " :: " + level.getGravity());

		move();
		sprite = spriteSet.update(state);
	}

	public void kill() {}

}
