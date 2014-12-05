package gameGUI;

import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FinishScreen extends JFrame {
	Image referenceImage;

	public FinishScreen(int win) {

		FinishPanel finishPanel = new FinishPanel(win);
		setTitle("Finish");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.referenceImage = new ImageIcon("field.jpg").getImage();
		this.add(finishPanel);

		// Set size of finish screen
		this.setSize(new Dimension(referenceImage.getWidth(null),
				referenceImage.getHeight(null)));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);

		if (win == 1) {
			JOptionPane.showMessageDialog(this, "Congratulations! You Win");
		} else if (win == 2) {
			JOptionPane.showMessageDialog(this,
					"Better Luck next Time, You Lose");
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		FinishScreen f = new FinishScreen(1);
	}

}
