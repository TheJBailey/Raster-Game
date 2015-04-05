package com.knowgravity.raster_game.util.maths;

public class Coordinate {

	public double x, y;

	public Coordinate() {
		x = y = 0;
	}

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "x::" + x + " | y::" + y;
	}

	public static class Bounds extends Coordinate {

		public double width, height;

		public Bounds() {
			super();
			width = height = 0;
		}

		public Bounds(double x, double y, double width, double height) {
			super(x, y);
			this.width = width;
			this.height = height;
		}
	}
}
