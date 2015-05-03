package com.knowgravity.raster_game.level;

import java.awt.image.BufferedImage;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.util.IOUtil;

public class Background {

	// TODO background class
	private int[][] frames;
	private int numOfFrames, frameRate = 4, cFrame;
	private int ticks;

	private int width, height;
	private String dir = "";

	public Background(BufferedImage... images) {

	}

	public Background(String path, String prefix, String ext, int nFiles) {
		dir = path;
		load(prefix, ext, nFiles);
	}

	private void load(String prefix, String suffix, int nFiles) {
		BufferedImage image = IOUtil.loadImageResource(dir + prefix + 0 + suffix);
		width = image.getWidth();
		height = image.getHeight();
		frames = new int[nFiles][width * height];
		for (int i = 0; i < nFiles; i++)
			IOUtil.loadImageResource(dir + prefix + i + suffix).getRGB(0, 0, width, height, frames[i], 0, width);
	}

	public void update() {
		ticks++;
		if (ticks > 3600) ticks = 0;
		if (ticks % frameRate == 0) cFrame++;
		if (cFrame >= frames.length) cFrame = 0;
	}

	public void render(Screen screen) {
//		for (int i = 0; i < frames[cFrame].length; i++) {
//			screen.renderRaster(0, 0, width, height, frames[cFrame]);
//			System.out.println("looping" + cFrame);
//		}
		}

	class Layer {
		// TODO background layer structure
		
	}
}
