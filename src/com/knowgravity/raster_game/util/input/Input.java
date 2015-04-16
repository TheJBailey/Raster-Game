package com.knowgravity.raster_game.util.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	private ArrayList<Key> keys = new ArrayList<Key>();
	private boolean[] key_codes = new boolean[65535];

	public Input() {
		for (int i = 0; i < key_codes.length; i++)
			keys.add(new Key(i));
	}

	public void update() {
		for (int i = 0; i < key_codes.length; i++) {
			keys.get(i).pState = keys.get(i).state;
			keys.get(i).setState(key_codes[i]);
		}
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

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
