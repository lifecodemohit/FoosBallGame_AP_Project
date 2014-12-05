package gameGUI;

import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class StartScreen extends JFrame {
	Image referenceImage;

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StartScreen s = new StartScreen();
	}

	public StartScreen() {

		StartPanel startPanel = new StartPanel();
		setTitle("Start");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.referenceImage = new ImageIcon("field.jpg").getImage();
		this.add(startPanel);

		// Set size of start screen
		this.setSize(new Dimension(referenceImage.getWidth(null),
				referenceImage.getHeight(null)));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
	}

}
