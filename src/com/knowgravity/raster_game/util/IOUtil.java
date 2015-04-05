package com.knowgravity.raster_game.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.knowgravity.raster_game.graphics.sprite.Sprite;

public class IOUtil {

	public static File loadResource(String path) {
		try {
			return new File(IOUtil.class.getResource(path).toURI());
		} catch (URISyntaxException e) {
			System.err.println("failed to load::" + path);
			return null;
		}
	}

	public static BufferedImage loadImageResource(String path) {
		try {
			return ImageIO.read(loadResource(path));
		} catch (IOException e) {
			System.err.println("failed to load image::" + path);
			return null;
		}
	}

	// LOW fix sprite loading from dat file
	@SuppressWarnings({ "resource", "unused" })
	public Sprite loadSprite(int x, int y, String path) {
		Scanner file;
		int sw = 32, sh = 32;
		try {
			file = new Scanner(IOUtil.loadResource(path + "/load.dat"));
			while (file.hasNextLine()) {
				String line = file.nextLine();
				if (line.startsWith("$")) {
					line = line.replace("$", "");
					String[] wh = line.split(",");
					sw = Integer.parseInt(wh[0]);
					sh = Integer.parseInt(wh[1]);
				}
				if (line.startsWith("::")) {
					line = line.replace("::", "");

				}
			}
		} catch (FileNotFoundException e) {
		}
		return null;
	}

}
