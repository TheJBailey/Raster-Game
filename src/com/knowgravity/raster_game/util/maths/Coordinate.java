package com.knowgravity.raster_game.util.maths;

import com.knowgravity.raster_game.Game;

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

		public Coordinate getCenter() {
			return new Coordinate(x + width / 2, y + height / 2);
		}

		public boolean contains(Coordinate coor) {
			if (coor.x < x || coor.x > x + width || coor.y < y || coor.y > y + height) return false;
			return true;
		}

		public boolean containsMouse() {
			int scale = Game.getScale();
			Coordinate coor = Game.getInput().getMousePosition();
			Bounds b = new Bounds(x * scale, y * scale, width * scale, height * scale);
			if (coor.x < b.x || coor.x > b.x + b.width || coor.y < b.y || coor.y > b.y + b.height) return false;
			return true;
		}

		public String toString() {
			return super.toString() + " | width::" + width + " | h::" + height;
		}
	}
}
