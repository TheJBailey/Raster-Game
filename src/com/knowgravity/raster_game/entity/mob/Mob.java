package com.knowgravity.raster_game.entity.mob;

import com.knowgravity.raster_game.entity.Entity;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.sprite.SpriteSet;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.MathUtil;
import com.knowgravity.raster_game.util.maths.Vector2f;

public abstract class Mob extends Entity {

	public static final int IDLE = 0;
	public static final int WALKING = 1;
	public static final int RUNNING = 2;
	public static final int JUMPING = 3;
	public static final int LONG_JUMPING = 4;
	public static final int CLIMBING = 5;
	public static final int FALLING = 6;
	public static final int ATTACK = 7;
	public static final int DASH_ATTACK = 8;

	protected int state = WALKING;

	protected Vector2f vector;
	protected double speed, cSpeed;
	protected SpriteSet spriteSet;

	protected boolean jumping, jumping2, landed;
	private double xz, yz, xFric = .3;

	public Mob(double x, double y, SpriteSet set, Level level) {
		super(x, y, set.get(IDLE, 0), level);
		spriteSet = set;
	}

	public Mob(double x, double y, double width, double height, Sprite sprite, Level level) {
		super(x, y, width, height, sprite, level);
	}

	public Mob(double x, double y, Sprite sprite, Level level) {
		super(x, y, sprite, level);
	}

	// TODO fix flipped gravity
	// LOW mob move method is a bit messy
	public void move(double xa, double ya) {
		/* ---------------- x-axis ---------------- */
		while (xa != 0) {
			int collision = collisionDir = collision(MathUtil.absInc(xa), 0);
			if (xa < 0) xDir = Entity.DIR_LEFT;
			if (xa >= 0) xDir = Entity.DIR_RIGHT;
			if (Math.abs(xa) > 1) {
				if (collision == Entity.COLLISION_NONE) {
					bounds.x += MathUtil.absInc(xa);
				} else xz = 0;
				xa -= MathUtil.absInc(xa);
			} else {
				if (collision == Entity.COLLISION_NONE) {
					bounds.x += xa;
				} else xz = 0;
				xa = 0;
			}
		}

		/* ---------------- y-axis | also contains gravity and jumping(partial) ---------------- */
		yz -= level.getGravity();
		ya -= yz;
		// if (Main.getInput().getKey(KeyEvent.VK_O).isActive()) ya -= 1;
		// if (Main.getInput().getKey(KeyEvent.VK_L).isActive()) ya += 1;
		while (ya != 0) {
			int collision = collisionDir = collision(0, MathUtil.absInc(ya));
			if (ya < 0) yDir = Entity.DIR_UP;
			if (ya >= 0) yDir = Entity.DIR_DOWN;
			if (Math.abs(ya) > 1) {
				if (collision == Entity.COLLISION_NONE) {
					bounds.y += MathUtil.absInc(ya);
					landed = false;
				} else {
					if ((collisionDir == Entity.COLLISION_TOP_LEFT || collisionDir == Entity.COLLISION_TOP_RIGHT)
							&& yDir == Entity.DIR_DOWN) landed = true;
					else landed = false;
					yz = 0;
				}
				ya -= MathUtil.absInc(ya);
			} else {

				if (collision == Entity.COLLISION_NONE) {
					bounds.y += ya;
					landed = false;
				} else {
					if ((collisionDir == Entity.COLLISION_TOP_LEFT || collisionDir == Entity.COLLISION_TOP_RIGHT)
							&& yDir == Entity.DIR_DOWN) landed = true;
					else landed = false;
					yz = 0;
				}
				ya = 0;
			}
		}
		// TODO figure out correct logic behind jumping
		if (jumping || jumping2) {
			state = JUMPING;
			if (cSpeed > speed) state = LONG_JUMPING;
			if (landed) jumping = jumping2 = false;
		}
		if (yDir == Entity.DIR_DOWN && collisionDir == COLLISION_NONE) state = FALLING;
	}

	protected void jump(double jump_accel) {
		if (jumping2) return;
		if (jumping) jumping2 = true;
		else if (landed) jumping = true;
		else return;
		if (flip == 2 || flip == 3) yz = -jump_accel;
		else yz = jump_accel;
		// if ((collisionDir == Entity.COLLISION_BOTTOM_LEFT || collisionDir == Entity.COLLISION_TOP_LEFT)
		// && xDir == Entity.DIR_LEFT) xz = jump_accel / 2;
		// if ((collisionDir == Entity.COLLISION_BOTTOM_RIGHT || collisionDir == Entity.COLLISION_TOP_RIGHT)
		// && xDir == Entity.DIR_RIGHT) xz = -jump_accel / 2;
	}

}
