package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	boolean[] keyBuffer = new boolean[KeyEvent.KEY_LAST + 1];

	@Override
	public void keyPressed(KeyEvent arg0) {
		keyBuffer[arg0.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keyBuffer[arg0.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public boolean isKeyDown(int keyCode) {
		return keyBuffer[keyCode];
	}

}
