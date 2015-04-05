package com.knowgravity.raster_game.util.maths;

public class AspectRatio {
	double factor;

	public AspectRatio(int wr, int hr) {
		factor = (double) wr / (double) hr;
	}

	public AspectRatio(int factor) {
		this.factor = factor;
	}

	public int getHeight(int width) {
		return (int) (1 / factor * width);

	}

	public int getWidth(int height) {
		return (int) (factor * height);
	}

	public double getFactor() {
		return factor;
	}

}
