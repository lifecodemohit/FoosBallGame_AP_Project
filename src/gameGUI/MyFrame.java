package gameGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	public MyPanel myPanel;
	public ScorePanel scorePanel;
	public String teamAName, teamBName;

	public MyFrame(String teamAName, String teamBName)

	{
		this.myPanel = new MyPanel(teamAName, teamBName);
		this.teamAName = teamAName;
		this.teamBName = teamBName;
		add(myPanel);
		setTitle("FoosBall Game");
		this.setSize(new Dimension(myPanel.getWidth(), myPanel.getHeight()));

		this.scorePanel = new ScorePanel(teamAName, teamBName);
		add(scorePanel.getScorePanel(), BorderLayout.SOUTH);

		myPanel.setScorePanel(scorePanel);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Resize Dimensions

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent evt) {
				Component c = (Component) evt.getSource();
				System.out.println("Component Resize : " + c.getWidth()
						+ c.getHeight());
				Dimension newDimension = new Dimension(c.getWidth(), c
						.getHeight());
				myPanel.resizePanel(newDimension);
			}
		});

	}

	public void showFinishScreen() {
		this.setVisible(false);
		int win = 0;
		if (myPanel.scoreKeeper.getTeamAScore() > myPanel.scoreKeeper
				.getTeamBScore()) {
			win = 1;
		} else {
			win = 2;
		}
		FinishScreen finishScreen = new FinishScreen(win);
		finishScreen.setVisible(true);
		this.dispose();

	}

}
