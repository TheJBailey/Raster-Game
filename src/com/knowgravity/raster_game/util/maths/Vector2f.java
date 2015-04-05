package com.knowgravity.raster_game.util.maths;

public class Vector2f {

	float x, y;

	public Vector2f(float x, float y) {
		setVector(x, y);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Vector2f setX(float x) {
		this.x = x;
		return this;
	}

	public Vector2f setY(float y) {
		this.y = y;
		return this;
	}

	public Vector2f setVector(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
}
