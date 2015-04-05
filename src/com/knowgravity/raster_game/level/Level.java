package com.knowgravity.raster_game.level;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.level.tile.Tile;
import com.knowgravity.raster_game.level.tile.tiles.DirtTile;
import com.knowgravity.raster_game.util.IOUtil;
import com.knowgravity.raster_game.util.maths.Coordinate;
import com.knowgravity.raster_game.util.maths.MathUtil;

public class Level {

	public static Level TEST_LEVEL = new Level("/res/levels/test_level/");

	private int width, height;

	private int tileSize;
	private int bitShift;

	private String path;
	private Background background;
	private int bgWidth, bgHeight;

	private Coordinate spawnTile;
	private int[] tileMap, entityMap;

	private Tile[] tiles;

	public Level(String path) {
		this.path = path;
		load();
		generate();
	}

	public Level(int width, int height) {

	}

	public void load() {
		// TODO loading structure
		Scanner file = null;
		try {
			file = new Scanner(IOUtil.loadResource(path + "/.gen.dat"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		while (file.hasNextLine()) {
			String command = file.nextLine();
			String beg = command.substring(0, command.indexOf(":"));
			String content = command.replaceFirst(beg + ":", "");
			switch (beg) {
			case "background-file":
				// TODO background loading
				// BufferedImage img = IOUtil.loadImageResource(path + content);
				// bgWidth = img.getWidth();
				// bgHeight = img.getHeight();
				// background = img.getRGB(0, 0, bgWidth, bgHeight, null, 0, bgWidth);
				System.err.println("background loaded...");
				break;
			case "width-height":
				String[] wh = content.split(",");
				width = Integer.parseInt(wh[0]);
				height = Integer.parseInt(wh[1]);
				System.err.println("width and height loaded...");
				break;
			case "tile-size":
				tileSize = Integer.parseInt(content);
				bitShift = MathUtil.getBitShift(tileSize);
				System.err.println("tile size loaded...");
				break;
			case "tile-map":
				tileMap = IOUtil.loadImageResource(path + content).getRGB(0, 0, width, height, null, 0, width);
				System.err.println("tile map loaded...");
				break;
			case "entity-map":
				entityMap = IOUtil.loadImageResource(path + content).getRGB(0, 0, width, height, null, 0, width);
				System.err.println("entity map loaded...");
				break;
			case "spawn-tile":
				String[] xy = content.split(",");
				spawnTile = new Coordinate(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
				System.err.println("spawn tile loaded...");
			}
		}
		file.close();
	}

	public void generate() {
		tiles = new Tile[width * height];
		for (int i = 0; i < tileMap.length; i++) {
			// System.out.println(String.format("0x%06X", (0xFFFFFF & tileMap[i])));
			tiles[i] = Tile.unknown;
			if (tileMap[i] == 0xFFFFFFFF) tiles[i] = Tile.background;
			if (tileMap[i] == 0xFF000000) tiles[i] = DirtTile.fetch();
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		int pw = getPixelWidth();
		int ph = getPixelHeight();
		int var = tileSize * 1;
		if (pw >= Screen.getWidth()) {
			if (xScroll + Screen.getWidth() > pw) xScroll = pw - Screen.getWidth();
			if (xScroll < 0) xScroll = 0;
		}
		if (yScroll + Screen.getHeight() > ph + var) yScroll = (ph + var) - Screen.getHeight();
		if (yScroll < -var) yScroll = -var;

		screen.setOffset(xScroll, yScroll);

		int x0 = xScroll >> bitShift;
		int x1 = (xScroll + Screen.getWidth() + tileSize) >> bitShift;
		int y0 = yScroll >> bitShift;
		int y1 = (yScroll + Screen.getHeight() + tileSize) >> bitShift;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, bitShift, screen);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.outOfBounds;
		// System.out.println(tiles[x + y * width]);
		return tiles[x + y * width];
	}

	public int getPixelWidth() {
		return width * tileSize;
	}

	public int getPixelHeight() {
		return height * tileSize;
	}

	public int getTileWidth() {
		return width;
	}

	public int getTileHeight() {
		return height;
	}

	public Coordinate getSpawnTile() {
		return spawnTile;
	}

	public int getTileSize() {
		return this.tileSize;
	}
}
