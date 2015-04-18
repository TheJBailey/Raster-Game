package com.knowgravity.raster_game.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.knowgravity.raster_game.graphics.sprite.Sprite;

public class IOUtil {

	public static String loadDatResource(String path) {
		InputStream is = IOUtil.class.getResourceAsStream(path);
		String s = null;
		try {
			s = new String(IOUtils.toByteArray(is));
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	public static BufferedImage loadImageResource(String path) {
		try {
			return ImageIO.read(IOUtil.class.getResource(path));
		} catch (IOException e) {
			System.err.println("failed to load image::" + path);
			return null;
		}
	}

	// LOW fix sprite loading from dat file
	@SuppressWarnings("unused")
	public Sprite loadSprite(int x, int y, String path) {
		Scanner file;
		int sw = 32, sh = 32;
		String data = IOUtil.loadDatResource(path + "/load.dat");
		return null;
	}

}
