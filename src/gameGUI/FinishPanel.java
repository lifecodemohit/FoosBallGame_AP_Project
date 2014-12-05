package gameGUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FinishPanel extends JPanel {
	Image WinningImage;
	Image LosingImage;
	Image referenceImage;
	int win = 0;

	public FinishPanel(int win) {
		this.win = win;
		this.WinningImage = new ImageIcon("Victory.jpg").getImage();
		this.LosingImage = new ImageIcon("Loser.jpg").getImage();
		this.referenceImage = new ImageIcon("field.jpg").getImage();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (win == 1) {
			g.drawImage(WinningImage, 0, 0, this.getWidth(), this.getHeight(),
					null);
		} else if (win == 2) {
			g.drawImage(LosingImage, 0, 0, this.getWidth(), this.getHeight(),
					null);
		}

	}

}
