package com.knowgravity.raster_game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.knowgravity.raster_game.entity.mob.player.Player;
import com.knowgravity.raster_game.graphics.Screen;
import com.knowgravity.raster_game.graphics.ui.UIManager;
import com.knowgravity.raster_game.graphics.ui.states.LoadingMenu;
import com.knowgravity.raster_game.graphics.ui.states.StartMenu;
import com.knowgravity.raster_game.level.Level;
import com.knowgravity.raster_game.util.input.Input;
import com.knowgravity.raster_game.util.maths.AspectRatio;
import com.knowgravity.raster_game.util.maths.Coordinate;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame window;
	static String title = "Raster Game";
	static AspectRatio aspect = new AspectRatio(16, 9);
	static int total_width = 1000, width, height, scale = -1;
	static Dimension size;

	static Font DEFAULT_FONT = new Font("HELVETICA", Font.PLAIN, 32);

	private static BufferedImage image;
	private static int[] pixels;
	private static Screen screen;

	private Thread thread;
	private boolean running;
	private static int frameLimit = -1, updateRate = 60;

	static boolean DEV_HUD = true;

	private static Level level;
	private static Player player;

	private static Input input;

	private static Loader loader;
	private static UIManager uiManager;

	public Game() {
		window = new JFrame(title);
		initGraphics(1);
		initWindow();

		screen = new Screen(width, height);

		input = new Input();
		addMouseListener(input);
		addMouseMotionListener(input);
		addKeyListener(input);

		uiManager = new UIManager(new StartMenu());
		loader = new Loader(this);
		load();
	}

	public static void load() {
		Game.getUIManager().removeUIState();
		uiManager.addUIState(new LoadingMenu());
		Thread thread = new Thread(loader, "loading");
		thread.start();
	}

	public void update() {
		input.update();
		uiManager.update();
		if (loader != null) loader.update();
		if (level != null) level.update();
		if (player != null) player.update();
	}

	void setRenderingHints(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		setRenderingHints(g);

		int xScroll = 0, yScroll = 0;
		if (player != null) {
			xScroll = (int) player.getBounds().getCenter().x - Screen.getWidth() / 2;
			yScroll = (int) player.getBounds().getCenter().y - Screen.getHeight() / 2;
		}

		/* <-------raster-render here-------> */
		uiManager.render(screen);

		if (level != null) level.render(xScroll, yScroll, screen);
		if (player != null) player.render(screen);

		for (int i = 0; i < Screen.getPixels().length; i++)
			pixels[i] = Screen.getPixels()[i];

		g.drawImage(image, 0, 0, size.width, size.height, null);

		/* <-------graphics-render here-------> */
		g.setColor(Color.white);
		g.setFont(DEFAULT_FONT.deriveFont(24f));
		Coordinate m = new Coordinate(input.getMousePosition().x, input.getMousePosition().y);
		g.drawString("x::" + m.x + " | y::" + m.y, size.width - 200, 24);
		g.drawOval((int) m.x - 5, (int) m.y - 5, 10, 10);

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
		double deltaU = 0;

		int frames = 0, updates = 0;
		long timer = System.currentTimeMillis();

		requestFocus();
		while (running) {
			long now = System.nanoTime();
			deltaU += (now - lastTimeU) / (1000000000.0 / updateRate);
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

	public static void initGraphics(int scale) {
		Game.scale = scale;
		height = aspect.getHeight((width = total_width / scale));

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		screen = new Screen(width, height);

		size = new Dimension(width * scale, height * scale);
	}

	public void initWindow() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(size);
		window.add(this);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		new Game().start();
	}

	public static void enableDevHUD(boolean dev) {
		DEV_HUD = dev;
	}

	public static boolean isDevHudEnabled() {
		return DEV_HUD;
	}

	public static void setFrameLimt(int fl) {
		frameLimit = fl;
	}

	public static Input getInput() {
		return input;
	}

	public static Level getLevel() {
		return level;
	}

	public static int getScale() {
		if (level == null) return scale;
		return level.getScale();
	}

	public void setLevel(Level level) {
		Game.level = level;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		Game.player = player;
	}

	public static UIManager getUIManager() {
		return uiManager;
	}
}
