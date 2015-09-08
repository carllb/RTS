package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	boolean[] keyBuffer = new boolean[KeyEvent.KEY_LAST + 1];

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() >= 0 && arg0.getKeyCode() < keyBuffer.length - 1) {
			keyBuffer[arg0.getKeyCode()] = true;
		} else {
			System.err.println("unknown key code: " + arg0.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() >= 0 && arg0.getKeyCode() < keyBuffer.length - 1) {
			keyBuffer[arg0.getKeyCode()] = false;
		} else {
			System.err.println("unknown key code: " + arg0.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public boolean isKeyDown(int keyCode) {
		return keyBuffer[keyCode];
	}

}
