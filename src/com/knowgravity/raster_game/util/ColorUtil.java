package com.knowgravity.raster_game.util;

public class ColorUtil {

	public static int TYPE_OVER = 1;

	public static int getAlphaComposite(int tc, int bc, int compositeType) {
		float alpha = (float) getAlpha(tc) / (float) 255;
		if (alpha == 1f) return tc;
		if (alpha == 0) return bc;
		int red = 0, green = 0, blue = 0;
		switch (compositeType) {
		case 1:
			red = (int) (getRed(tc) * alpha + getRed(bc) * (1 - alpha));
			green = (int) (getGreen(tc) * alpha + getGreen(bc) * (1 - alpha));
			blue = (int) (getBlue(tc) * alpha + getBlue(bc) * (1 - alpha));
			break;
		default:
			break;
		}

		return (int) alpha * 256 * 256 * 256 + red * 256 * 256 + green * 256 + blue;
	}

	public static int getAlpha(int color) {
		return (color >> 24) & 0xFF;
	}

	public static int getRed(int color) {
		return (color >> 16) & 0xFF;
	}

	public static float getGreen(int color) {
		return (color >> 8) & 0xFF;
	}

	public static int getBlue(int color) {
		return color & 0xFF;
	}
}
