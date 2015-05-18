package com.knowgravity.raster_game.entity.mob.player;

import static com.knowgravity.raster_game.Game.getInput;

import com.knowgravity.raster_game.entity.mob.Mob;
import com.knowgravity.raster_game.graphics.sprite.Sprites.Sprites;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.input.Input;

public class Player extends Mob {

	public Player(double x, double y, Level level) {
		super(x * level.getTileSize(), y * level.getTileSize(), Sprites.Player.LINK_SPRITESET, level);
		speed = 2.2;
		rSpeed = speed * 2;
		jumpH = 5.25;
	}

	@Override
	public void update() {
		/* ------------------------ keys ----------------------------- */
		boolean left = getInput().getKey(Input.KEY_LEFT).isActive();
		boolean right = getInput().getKey(Input.KEY_RIGHT).isActive();
		boolean sprint = getInput().getKey(Input.KEY_SPRINT).isActive();

		boolean jump = getInput().getKey(Input.KEY_JUMP).isPressed();

		/* -------------- input ---------------- */
		if (left || right) {
			if (sprint) state = RUNNING;
			else state = WALKING;
			if (left) xDir = DIR_LEFT;
			if (right) xDir = DIR_RIGHT;
		} else if (cSpeed != 0) state = SLIDING;
		else state = IDLE;

		if (jump) jump(jumpH);

		move();
		sprite = spriteSet.update(state);
	}

	public void kill() {}

}
