package com.knowgravity.raster_game.entity.mob;

import com.knowgravity.raster_game.entity.Entity;
import com.knowgravity.raster_game.graphics.sprite.Sprite;
import com.knowgravity.raster_game.graphics.sprite.SpriteSet;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.MathUtil;
import com.knowgravity.raster_game.util.maths.Vector2f;

import static com.knowgravity.raster_game.graphics.sprite.Sprite.*;

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
	public static final int SLIDING = 9;

	protected int state = WALKING;

	protected Vector2f vector;
	protected double speed, rSpeed, cSpeed, jumpH;
	protected SpriteSet spriteSet;

	protected boolean jumping, jumping2, landed;
	private double yz, xFric = .3, xAccel = .25;

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

	// LOW fix flipped gravity
	// LOW tidy up move cycling
	public void move() {
		double xa = 0, ya = 0;

		xa += doSpeed();
		applyFriction();

		/* ---------------- x-axis ---------------- */
		while (xa != 0) {
			collision = collision(MathUtil.absInc(xa), 0);
			if (Math.abs(xa) > 1) {
				if (collision == Entity.COLLISION_NONE) bounds.x += MathUtil.absInc(xa);
				xa -= MathUtil.absInc(xa);
			} else {
				if (collision == Entity.COLLISION_NONE) bounds.x += xa;
				xa = 0;
			}
		}

		/* ---------------- y-axis | also contains gravity and jumping(partial) ---------------- */

		yz -= level.getGravity();
		ya -= yz;
		while (ya != 0) {
			collision = collision(0, MathUtil.absInc(ya));
			if (ya < 0) yDir = Entity.DIR_UP;
			if (ya >= 0) yDir = Entity.DIR_DOWN;
			if (Math.abs(ya) > 1) {
				if (collision == Entity.COLLISION_NONE) {
					bounds.y += MathUtil.absInc(ya);
					landed = false;
				} else {
					if ((collision == Entity.COLLISION_TOP_LEFT || collision == Entity.COLLISION_TOP_RIGHT)
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
					if ((collision == Entity.COLLISION_TOP_LEFT || collision == Entity.COLLISION_TOP_RIGHT)
							&& yDir == Entity.DIR_DOWN) landed = true;
					else landed = false;
					yz = 0;
				}
				ya = 0;
			}
		}

		jumpdate();
		flip = getFlip();
	}

	public double doSpeed() {
		double tSpeed = 0;
		if (state == WALKING || state == RUNNING) {
			if (state == WALKING) tSpeed = speed;
			if (state == RUNNING) tSpeed = rSpeed;
			if (cSpeed != tSpeed) {
				if (cSpeed < tSpeed) {
					if (cSpeed + xAccel < tSpeed) cSpeed += xAccel;
					else cSpeed = tSpeed;
				} else {
					if (cSpeed - xAccel > tSpeed) cSpeed -= xAccel;
					else cSpeed = tSpeed;
				}
			}
		}

		if (xDir == DIR_LEFT) return -cSpeed;
		if (xDir == DIR_RIGHT) return cSpeed;
		return 0;
	}

	public void applyFriction() {
		if (state == SLIDING) {
			cSpeed -= xFric;
			if (cSpeed < xFric) cSpeed = 0;
		}
	}

	public int getFlip() {
		if (xDir == DIR_LEFT && level.getGravity() > 0) return FLIP_X;
		if (xDir != DIR_LEFT && level.getGravity() < 0) return FLIP_Y;
		if (xDir == DIR_LEFT && level.getGravity() < 0) return FLIP_BOTH;
		return FLIP_NONE;
	}

	public void jumpdate() {
		if (yDir == Entity.DIR_DOWN && collision == COLLISION_NONE) state = FALLING;
		if (jumping || jumping2) state = JUMPING;
		if (state == JUMPING && cSpeed > speed) state = LONG_JUMPING;
		if (landed) jumping = jumping2 = false;
	}

	public void jump(double jump_accel) {
		if (jumping2) return;
		if (jumping) jumping2 = true;
		else if (landed) jumping = true;
		else return;
		if (flip == FLIP_BOTH || flip == FLIP_Y) yz = -jump_accel;
		else yz = jump_accel;
	}

}
