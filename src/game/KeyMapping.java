package game;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class KeyMapping implements Serializable, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HashMap<String, Integer> keyMap = new HashMap<String, Integer>();
	transient File keys = new File("keys");
	transient JFrame frame;
	transient JScrollPane scroll;
	transient JPanel panel;
	transient KeyHandler input;

	public static void main(String[] args) {
		new KeyMapping(new KeyHandler());
	}

	@SuppressWarnings("unchecked")
	public KeyMapping(KeyHandler input) {
		Scanner s = null;

		try {
			s = new Scanner(keys);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (s.hasNext()) {
			String key = s.nextLine();
			keyMap.put(key, 0);
		}
		this.input = input;

		s.close();
		frame = new JFrame();
		panel = new JPanel();

		scroll = new JScrollPane(panel);
		frame.setLayout(null);
		frame.add(scroll);
		frame.setResizable(false);

		for (int i = 0; i < keyMap.entrySet().toArray().length; i++) {
			String keyS = ((Entry<String, Integer>) keyMap.entrySet().toArray()[i])
					.getKey();
			JButton b = new JButton(keyS);
			JLabel l = new JLabel("Key: "
					+ KeyEvent.getKeyText(keyMap.get(keyS)));
			panel.add(b);
			panel.add(l);
			l.setName(keyS);
			b.addActionListener(this);
			b.addKeyListener(input);
			b.setSelected(false);
		}
		JButton save = new JButton("Save");
		Save sl = new Save(this);
		save.addActionListener(sl);
		panel.add(save);
		save.addKeyListener(input);
		frame.setSize(640, 480);
		scroll.setSize(600, 400);
		panel.setSize(600, keyMap.entrySet().toArray().length * 100);
		frame.setVisible(true);
		frame.repaint();
		frame.addKeyListener(input);

	}

	@Override
	public void actionPerformed(final ActionEvent arg0) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				JButton b = (JButton) arg0.getSource();
				System.out.println(b.getText());
				b.setEnabled(false);
				long time = System.currentTimeMillis();
				while (true) {
					for (int i = 0; i < input.keyBuffer.length; i++) {
						if (input.keyBuffer[i]) {						
							
							keyMap.put(b.getText(), i);
							Component[] comps = panel.getComponents();
							for (Component c : comps) {
								if (c instanceof JLabel) {
									JLabel l = (JLabel) c;
									if (l.getName().equals(b.getText())) {
										l.setText(KeyEvent.getKeyText(i));
									}
								}
							}
							b.setEnabled(true);
							return;
						}
					}
					if(System.currentTimeMillis() - time > (500 * 10^3)){
						b.setEnabled(true);
						return;
					}
				}

			}
		});

		t.start();
	}
	public int getKey(String name){
		return keyMap.get(name);
	}

}
class Save implements ActionListener{

	KeyMapping km;
	public Save(KeyMapping km){
		this.km = km;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("SAVING...");
		JFileChooser jfc = new JFileChooser(".");
		jfc.showSaveDialog(jfc);
		File f = jfc.getSelectedFile();
		try {
			if(f == null){
				return;
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(km);
			oos.close();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}	
}
