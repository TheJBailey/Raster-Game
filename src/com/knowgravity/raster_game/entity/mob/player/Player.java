package com.knowgravity.raster_game.entity.mob.player;

import static com.knowgravity.raster_game.Main.getInput;
import static java.awt.event.KeyEvent.*;

import com.knowgravity.raster_game.entity.mob.Mob;
import com.knowgravity.raster_game.graphics.sprite.AnimatedSprite;
import com.knowgravity.raster_game.graphics.sprite.SpriteSet;
import com.knowgravity.raster_game.graphics.sprite.SpriteSheet;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.Coordinate;

public class Player extends Mob {

	// public static final Sprite SPRITE_1 = new Sprite(0, 0, 32, SpriteSheet.PLAYER);
	// public static final Sprite SPRITE_2 = new Sprite(1, 0, 32, SpriteSheet.PLAYER);
	// public static final Sprite SPRITE_3 = new Sprite(2, 0, 32, SpriteSheet.PLAYER);

	private static AnimatedSprite[] aniSprites = {
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
			new AnimatedSprite(10, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(3, 0), new Coordinate(4, 0) })
					.disableLoop(),
			new AnimatedSprite(20, SpriteSheet.PLAYER, 32, new Coordinate[] { new Coordinate(3, 2), new Coordinate(4, 2) })
					.disableLoop() };

	private boolean xFlip, yFlip;

	public Player(double x, double y, Level level) {
		super(x * level.getTileSize(), y * level.getTileSize(), new SpriteSet(aniSprites), level);
		speed = 2.2;
	}

	// @Override
	// public void move(int xa, int ya) {}

	@Override
	public void update() {
		double xa = 0, ya = 0;
		if (landed) cSpeed = speed;

		/* -------------- input ---------------- */
		if (state != ATTACK && state != DASH_ATTACK) {
			state = WALKING;
			if (getInput().getKey(VK_SHIFT).isActive() && landed) {
				state = RUNNING;
				cSpeed = speed * 2;
			}

			if (getInput().getKey(VK_A).isActive()) xa -= cSpeed;
			if (getInput().getKey(VK_D).isActive()) xa += cSpeed;
			if (xa == 0) state = IDLE;
			if (getInput().getKey(VK_SPACE).isPressed()) {
				if (state == RUNNING) jump(2.75);
				else jump(2.55);
			}
		} else {
			if (spriteSet.getAnimatedSprite(state).isDone() && !getInput().getKey(VK_COMMA).isActive()) state = IDLE;
			else {
				if (state == DASH_ATTACK) {
					if (xDir == DIR_LEFT) xa -= (speed * 1.75);
					if (xDir == DIR_RIGHT) xa += (speed * 1.75);
				}
			}
		}
		if (getInput().getKey(VK_COMMA).isPressed()) {
			if (state == RUNNING) state = DASH_ATTACK;
			else state = ATTACK;
		}
		/* -------------- input ---------------- */

		if (xa < 0) xFlip = true;
		if (xa > 0) xFlip = false;
		if (level.getGravity() > 0) yFlip = false;
		if (level.getGravity() < 0) yFlip = true;

		if (xFlip && yFlip) flip = FLIP_BOTH;
		else if (xFlip) flip = FLIP_X;
		else if (yFlip) flip = FLIP_Y;
		else flip = FLIP_NONE;

		// System.out.println(xa + " :: " + level.getGravity());

		move(xa, 0);
		move(0, ya);
		sprite = spriteSet.update(state);
	}

	public void kill() {}

}
