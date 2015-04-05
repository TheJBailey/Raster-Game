package com.knowgravity.raster_game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.knowgravity.raster_game.entity.mob.player.Player;
import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.maths.AspectRatio;

public class Main extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame window;
	static AspectRatio aspect = new AspectRatio(16, 9);
	static int width = 500, height = aspect.getHeight(width), scale = 2;
	static Dimension size = new Dimension(width * scale, height * scale);
	static String title = "Raster Game";

	static boolean DEV_HUD = true;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Thread thread;
	private boolean running;
	private static int frameLimit = -1, updateRate = 60;

	private Screen screen;

	private Level level = Level.TEST_LEVEL;
	private Player player;

	public Main() {
		window = new JFrame(title);
		this.setPreferredSize(size);
		initWindow();

		screen = new Screen(width, height);
		player = new Player((int) level.getSpawnTile().x, (int) level.getSpawnTile().y, Player.SPRITE, level);
	}

	public void update() {

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		int xScroll = (int) player.getBounds().x - Screen.getWidth() / 2;
		int yScroll = (int) player.getBounds().y - Screen.getHeight() / 2;
		/* <-------render here-------> */
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		/* <-------render here-------> */

		for (int i = 0; i < Screen.getPixels().length; i++)
			pixels[i] = Screen.getPixels()[i];

		g.drawImage(image, 0, 0, size.width, size.height, null);

		screen.clear();
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		/* frame limit */
		long lastTimeF = System.nanoTime();
		double deltaF = 0;

		/* updateRate */
		long lastTimeU = System.nanoTime();
		final double nsU = 1000000000.0 / updateRate;
		double deltaU = 0;

		int frames = 0, updates = 0;
		long timer = System.currentTimeMillis();

		requestFocus();
		while (running) {
			long now = System.nanoTime();
			deltaU += (now - lastTimeU) / nsU;
			lastTimeU = now;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			long nowF = System.nanoTime();
			deltaF += (nowF - lastTimeF) / (1000000000.0 / frameLimit);
			lastTimeF = nowF;

			if (frameLimit > 0) {
				if (deltaF >= 1) {
					render();
					frames++;
					deltaF--;
				}
			} else {
				render();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (DEV_HUD) window.setTitle(title + " | fps: " + frames + ", ups: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Main");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.err.println(thread.toString() + " failed to close");
		}
	}

	public void initWindow() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		new Main().start();
	}

	public static void setDevHUD(boolean dev) {
		DEV_HUD = dev;
	}

	public static boolean isDevHudEnabled() {
		return DEV_HUD;
	}

	public static void setFrameLimt(int fl) {
		frameLimit = fl;
	}

}