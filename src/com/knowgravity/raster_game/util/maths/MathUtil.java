package com.knowgravity.raster_game.util.maths;

import java.util.Random;

public class MathUtil {

	public static Random random = new Random();

	public static int getBitShift(int value) {
		int i = 0;
		while (value != 1) {
			value >>= 1;
			i++;
		}
		return i;
	}
}
