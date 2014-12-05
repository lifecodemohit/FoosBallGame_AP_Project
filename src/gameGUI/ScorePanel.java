package gameGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel {

	static final int DEFAULT_WIDTH = 300;
	static final int DEFAULT_HEIGHT = 200;
	JPanel newPanel = new JPanel();
	JTextField ScoreTeam1 = new JTextField();
	JTextField ScoreTeam2 = new JTextField();;
	JLabel TeamName1 = new JLabel("   TEAM1 NAME      /       ");
	JLabel TeamName2 = new JLabel("   TEAM2 NAME    ");
	JLabel TeamName3 = new JLabel("   TEAM3 NAME    "); // TEAM NAME 3 is for
														// extracting team 1
														// name

	String teamAName, teamBName;

	public ScorePanel(String teamAName, String teamBName) {
		// setResizable(true);// cannot be resized. Change to true for resizing.

		// first text box
		this.teamAName = teamAName;
		this.teamBName = teamBName;

		this.setTeamName1(teamAName + "  :  " + teamBName + "    ");
		this.setTeamName2("           " + teamBName + " : ");
		this.setTeamName3(teamAName + " : ");

		JPanel textbox1Panel = new JPanel();
		textbox1Panel.setLayout(new BoxLayout(textbox1Panel, 0));
		textbox1Panel.setOpaque(true);
		textbox1Panel.setBackground(new Color(164, 164, 164));
		textbox1Panel.setPreferredSize(new Dimension(TeamName3.toString()
				.length(), 25));
		TeamName3.setForeground(Color.BLACK);
		TeamName3.setFont(new Font("Sanserif", Font.BOLD, 16));
		textbox1Panel.add(TeamName3);
		textbox1Panel.add(ScoreTeam1);
		ScoreTeam1.setText("  0  ");

		JPanel textbox2Panel = new JPanel();
		textbox2Panel.setLayout(new BoxLayout(textbox2Panel, 0));
		textbox2Panel.setOpaque(true);
		textbox2Panel.setBackground(new Color(164, 164, 164));
		textbox2Panel.setPreferredSize(new Dimension(TeamName2.toString()
				.length(), 25));
		TeamName2.setForeground(Color.BLACK);
		TeamName2.setFont(new Font("Sanserif", Font.BOLD, 16));
		textbox2Panel.add(TeamName2);
		textbox2Panel.add(ScoreTeam2);
		// ScoreTeam1.setEnabled(false);//CHANGE THIS
		ScoreTeam2.setText("  0  ");
		// seond text box
		/*
		 * JPanel textbox2Panel = new JPanel(); textbox2Panel.setLayout(new
		 * BoxLayout(textbox2Panel, 0)); textbox2Panel.setOpaque(true);
		 * textbox2Panel.setBackground(new Color(164, 164, 164));
		 * textbox2Panel.setPreferredSize(new Dimension(200, 30));
		 * TeamName2.setForeground(Color.BLACK); TeamName2.setFont(new
		 * Font("Sanserif",Font.BOLD,16)); textbox2Panel.add(TeamName2);
		 * textbox2Panel.add(ScoreTeam2);
		 */
		// ScoreTeam2.setEnabled(false);//CHANGE THIS

		// Final Panel in insert
		newPanel.setOpaque(true);
		newPanel.setBackground(new Color(164, 164, 164));
		newPanel.setPreferredSize(new Dimension(TeamName3.toString().length()
				+ TeamName2.toString().length() + 5, 40));// Strip size
		newPanel.add(textbox1Panel, BorderLayout.WEST);
		// JTextField text=new JTextField("         \t\t\t\t\t\t\t\t\t   ");
		// newPanel.add(text,BorderLayout.CENTER);
		add(newPanel, BorderLayout.NORTH);

		newPanel.setOpaque(true);
		newPanel.setBackground(new Color(164, 164, 164));
		newPanel.setPreferredSize(new Dimension(TeamName3.toString().length()
				+ TeamName2.toString().length() + 5, 40));// Strip size
		newPanel.add(textbox2Panel, BorderLayout.EAST);
		// JTextField text1=new JTextField("\t\t\t\t\t\t\t\t\t");
		add(newPanel, BorderLayout.NORTH);

		// text.setOpaque(true);
		// text.setBackground(new Color(164, 164, 164));
		// Border border = BorderFactory.createLineBorder(new Color(164, 164,
		// 164), 5);
		// text.setBorder(border);
		// newPanel.add(text,BorderLayout.CENTER);
		// newPanel.add(textbox2Panel,BorderLayout.EAST);
		// add(newPanel,BorderLayout.NORTH);//Position

		// pack();

	}

	public void setScoreTeam1(int i) {
		ScoreTeam1.setText("" + i);
	}

	public void setScoreTeam2(int Score) {
		ScoreTeam2.setText("" + Score);
	}

	public void setTeamName1(String Name) {
		TeamName1.setText(Name);
	}

	public void setTeamName2(String Name) {
		TeamName2.setText(Name);
	}

	public void setTeamName3(String Name) {
		TeamName3.setText(Name);
	}

	public JPanel getScorePanel() {
		return this.newPanel;
	}

}