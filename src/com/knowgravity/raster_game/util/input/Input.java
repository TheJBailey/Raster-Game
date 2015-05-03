package com.knowgravity.raster_game.util.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.knowgravity.raster_game.util.maths.Coordinate;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	public static final int NMB = -1;
	public static final int LMB = 1;
	public static final int RMB = 3;
	public static final int MMB = 2;

	public static final int KEY_JUMP = KeyEvent.VK_SPACE;
	public static final int KEY_SPRINT = KeyEvent.VK_SHIFT;
	public static final int KEY_RIGHT = KeyEvent.VK_D;
	public static final int KEY_LEFT = KeyEvent.VK_A;
	public static final int KEY_SPECIAL_1 = KeyEvent.VK_X;

	private ArrayList<Key> keys = new ArrayList<Key>();
	private boolean[] key_codes = new boolean[65535];

	private Coordinate mouse = new Coordinate(0, 0);
	private int button = NMB, pButton = NMB, cButton = NMB;

	public Input() {
		for (int i = 0; i < key_codes.length; i++)
			keys.add(new Key(i));
	}

	public void update() {
		for (int i = 0; i < key_codes.length; i++) {
			keys.get(i).pState = keys.get(i).state;
			keys.get(i).setState(key_codes[i]);
		}
		pButton = cButton;
		cButton = button;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		key_codes[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key_codes[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public Key getKey(int keyEvent) {
		return keys.get(keyEvent);
	}

	public class Key {
		private int keyCode;
		private boolean state, pState;

		public Key(int key) {
			keyCode = key;
		}

		public int getKeyCode() {
			return keyCode;
		}

		public boolean isActive() {
			return state;
		}

		public boolean isPressed() {
			return (!pState && state);
		}

		public boolean isReleased() {
			return (pState && !state);
		}

		private void setState(boolean bool) {
			state = bool;
		}
	}

	public int getMouseButton() {
		return cButton;
	}

	public int getPreviousButton() {
		return pButton;
	}

	public Coordinate getMousePosition() {
		return new Coordinate(mouse.x, mouse.y);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button = NMB;
	}

}
