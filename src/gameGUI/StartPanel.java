package gameGUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartPanel extends JPanel {
	Image howToPlay;
	Image instruction;
	Image referenceImage;

	public StartPanel() {
		this.howToPlay = new ImageIcon("HowToPlay.png").getImage();
		this.referenceImage = new ImageIcon("field.jpg").getImage();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(howToPlay, 0, 0, this.getWidth(), this.getHeight(), null);

	}

}
