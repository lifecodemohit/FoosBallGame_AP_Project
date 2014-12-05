package gameGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import team.Team;
import coin.Coin;
import coin.enums.CoinFace;
import computerPlayer.ComputerPlayer;

@SuppressWarnings("serial")
public class Configuration extends JFrame {
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private ButtonGroup buttonGroup2 = new ButtonGroup();
	private ButtonGroup buttonGroup3 = new ButtonGroup();
	private ButtonGroup buttonGroup4 = new ButtonGroup();

	private JButton start = new JButton();

	private JPanel topPanel = new JPanel();
	private JPanel playerPanel = new JPanel();

	private JPanel playerNamePanel = new JPanel();
	public JPanel modePanel = new JPanel();

	private JPanel centralPanel = new JPanel();
	public JPanel levelPanel = new JPanel();
	public JPanel playerNoPanel = new JPanel();

	public JPanel selectionPanel = new JPanel();
	private JPanel selectionPanel1 = new JPanel();
	private JPanel selectionPanel2 = new JPanel();
	private JPanel selectionPanel3 = new JPanel();

	private JPanel tossPanel = new JPanel();

	private JPanel buttonPanel = new JPanel();

	private JLabel nameLabel = new JLabel();
	private JTextField nameField = new JTextField();

	private JRadioButton novice = new JRadioButton();
	private JRadioButton intermediate = new JRadioButton();
	private JRadioButton expert = new JRadioButton();
	private JRadioButton player1 = new JRadioButton();
	private JRadioButton player2 = new JRadioButton();
	private JRadioButton human = new JRadioButton();
	private JRadioButton computer = new JRadioButton();
	private JRadioButton head = new JRadioButton();
	private JRadioButton tail = new JRadioButton();

	@SuppressWarnings("rawtypes")
	private JComboBox defender = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox midFielder = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox attacker = new JComboBox();

	Coin c = new Coin();
	boolean p1 = false, p2 = false;
	String name = null, name2 = null;
	Team teamA = null, teamB = null;
	int level = 0, level2 = 0, mode = 0, mode2 = 0, defenders = 0,
			midFielders = 0, attackers = 0, defenders2 = 0, midFielders2 = 0,
			attackers2 = 0;
	CoinFace p1Choice;
	int tossWinner = 0;

	public Configuration() {
		init();
		player1.setSelected(true);
		player2.setEnabled(false);
		start.setEnabled(false);
		human.setSelected(true);
		intermediate.setSelected(true);
		head.setSelected(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		playerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		playerPanel.setLayout(new GridLayout(4, 1));

		topPanel.setLayout(new GridLayout(1, 2));
		playerNamePanel.setLayout(new GridLayout(1, 2));
		modePanel.setLayout(new GridLayout(1, 2));

		centralPanel.setLayout(new GridLayout(1, 2));
		levelPanel.setLayout(new GridLayout(1, 3));
		playerNoPanel.setLayout(new GridLayout(1, 2));

		selectionPanel.setLayout(new GridLayout(1, 3));
		selectionPanel1.setLayout(new GridLayout(1, 2));
		selectionPanel2.setLayout(new GridLayout(1, 2));
		selectionPanel3.setLayout(new GridLayout(1, 2));

		tossPanel.setLayout(new GridLayout(1, 5));

		nameLabel.setText("Player Name");
		nameLabel.setSize(70, 20);
		nameField.setSize(70, 20);

		playerNamePanel.setBorder(javax.swing.BorderFactory
				.createEtchedBorder());

		// Setting the border of modePanel
		modePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
				BorderFactory.createTitledBorder("Mode"), "",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("SansSerif", 0, 48)));

		// Setting the border of levelPanel
		levelPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
				BorderFactory.createTitledBorder("Player"), "",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("SansSerif", 0, 48)));

		// Setting the border of playerNoPanel
		playerNoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
				BorderFactory.createTitledBorder("Player"), "",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("SansSerif", 0, 48)));

		// Setting the border of selectionPanel
		selectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
				BorderFactory.createTitledBorder("Selection"), "",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("SansSerif", 0, 48)));

		buttonGroup1.add(computer);
		buttonGroup1.add(human);

		buttonGroup2.add(novice);
		buttonGroup2.add(intermediate);
		buttonGroup2.add(expert);

		buttonGroup3.add(player1);
		buttonGroup3.add(player2);

		buttonGroup4.add(head);
		buttonGroup4.add(tail);

		defender.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"3", "4", "5", "6" }));
		midFielder.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"2", "3", "4", "5", "6" }));
		attacker.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"1", "2", "3", "4" }));

		computer.setText(" Computer");
		human.setText(" Human");

		playerNamePanel.setPreferredSize(new Dimension(500, 20));
		playerNamePanel.add(nameLabel);
		playerNamePanel.add(nameField);

		modePanel.add(computer);
		modePanel.add(human);

		topPanel.setPreferredSize(new Dimension(60, 10));
		topPanel.add(playerNamePanel);
		topPanel.add(modePanel);

		novice.setText(" Novice");
		intermediate.setText(" Intermediate");
		expert.setText(" Expert");

		player1.setText(" Player 1");
		player2.setText(" Player 2");

		levelPanel.add(novice);
		levelPanel.add(intermediate);
		levelPanel.add(expert);

		playerNoPanel.add(player1);
		playerNoPanel.add(player2);

		centralPanel.setPreferredSize(new Dimension(20, 10));
		centralPanel.add(levelPanel);
		centralPanel.add(playerNoPanel);

		selectionPanel1.add(new JLabel("Defender"));
		selectionPanel1.add(defender);
		selectionPanel2.add(new JLabel("   Midfielder"));
		selectionPanel2.add(midFielder);
		selectionPanel3.add(new JLabel("   Attacker"));
		selectionPanel3.add(attacker);

		selectionPanel.add(selectionPanel1);
		selectionPanel.add(selectionPanel2);
		selectionPanel.add(selectionPanel3);

		head.setText(" Head");
		tail.setText(" Tail");

		tossPanel.add(new JLabel(""));
		tossPanel.add(head);
		tossPanel.add(new JLabel(""));
		tossPanel.add(tail);
		tossPanel.add(new JLabel(""));

		playerPanel.setSize(new Dimension(900, 300));
		playerPanel.add(topPanel);
		playerPanel.add(centralPanel);
		playerPanel.add(selectionPanel);
		playerPanel.add(tossPanel);

		start.setText(" Begin ");

		buttonPanel.add(start);
		add(buttonPanel, BorderLayout.SOUTH);
		this.setSize(new Dimension(playerPanel.getWidth(), playerPanel
				.getHeight()));
		this.add(playerPanel);

		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		computer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				computerActionPerformed(evt);
			}
		});

		human.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				humanActionPerformed(evt);
			}
		});

		start.setText("Next");
		start.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StartActionPerformed(evt);
			}
		});

		defender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((Integer.parseInt((String) midFielder.getSelectedItem()) == 2)
							&& (Integer.parseInt((String) defender
									.getSelectedItem()) == 3)) {
						JOptionPane
								.showMessageDialog(rootPane,
										"MidFielder = 2 and Defender = 3 not valid combination!");
						start.setEnabled(false);
						return;
					}

					int indexSelected = 8 - (Integer.parseInt((String) attacker
							.getSelectedItem()) + Integer
							.parseInt((String) defender.getSelectedItem()));

					if (indexSelected < 0) {
						// defender.removeActionListener(this);
						defender.setSelectedIndex(0);
						start.setEnabled(false);
						if (human.isSelected()) {

							JOptionPane
									.showMessageDialog(
											rootPane,
											"This combination is not possible the sum must be 11.\n Every category must have atleast 2 players.");
						}
						defender.addActionListener(this);
						if ((Integer.parseInt((String) defender
								.getSelectedItem())
								+ Integer.parseInt((String) midFielder
										.getSelectedItem()) + Integer
								.parseInt((String) attacker.getSelectedItem())) == 10) {
							start.setEnabled(true);
						}

					} else {
						midFielder.setSelectedIndex(indexSelected);
						start.setEnabled(true);
					}
				} catch (Exception e2) {

				}
			}

		});

		midFielder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if ((Integer.parseInt((String) midFielder.getSelectedItem()) == 2)
							&& (Integer.parseInt((String) defender
									.getSelectedItem()) == 3)) {
						JOptionPane
								.showMessageDialog(rootPane,
										"MidFielder = 2 and Defender = 3 not valid combination!");
						start.setEnabled(false);
						return;
					}

					int indexSelected = 9 - (Integer.parseInt((String) defender
							.getSelectedItem()) + Integer
							.parseInt((String) midFielder.getSelectedItem()));
					if (indexSelected < 0) {
						// midFielder.removeActionListener(this);
						midFielder.setSelectedIndex(0);
						start.setEnabled(false);
						if (human.isSelected()) {
							JOptionPane
									.showMessageDialog(
											rootPane,
											"This combination is not possible the sum must be 11.\n Every category must have atleast 2 players.");
						}
						midFielder.addActionListener(this);
						if (((Integer.parseInt((String) defender
								.getSelectedItem())
								+ Integer.parseInt((String) midFielder
										.getSelectedItem()) + Integer
								.parseInt((String) attacker.getSelectedItem())) == 10)) {
							start.setEnabled(true);
						}

					} else {
						attacker.setSelectedIndex(indexSelected);
						start.setEnabled(true);
					}

				} catch (Exception e2) {
				}

			}
		});
		attacker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int indexSelected = 7 - (Integer
							.parseInt((String) midFielder.getSelectedItem()) + Integer
							.parseInt((String) attacker.getSelectedItem()));
					if (indexSelected < 0) {
						// attacker.removeActionListener(this);
						attacker.setSelectedIndex(0);
						start.setEnabled(false);
						if (human.isSelected()) {

							JOptionPane
									.showMessageDialog(
											rootPane,
											"This combination is not possible the sum must be 11.\n Every category must have atleast 2 players.");
						}
						attacker.addActionListener(this);
						if ((Integer.parseInt((String) defender
								.getSelectedItem())
								+ Integer.parseInt((String) midFielder
										.getSelectedItem()) + Integer
								.parseInt((String) attacker.getSelectedItem())) == 10) {
							start.setEnabled(true);
						}
					} else {
						defender.setSelectedIndex(indexSelected);
						start.setEnabled(true);
					}
				} catch (Exception e2) {
				}

			}
		});

	}

	private void computerActionPerformed(java.awt.event.ActionEvent evt) {
		midFielder.setEnabled(false);
		defender.setEnabled(false);
		attacker.setEnabled(false);
		ArrayList<Integer> numberOfDefender = new ArrayList<Integer>();
		ArrayList<Integer> numberOfMidfielder = new ArrayList<Integer>();
		for (int numDefenderPlayer = 4; numDefenderPlayer <= 6; numDefenderPlayer++) {
			for (int numMidfielderPlayer = 3; numMidfielderPlayer <= (9 - numDefenderPlayer); numMidfielderPlayer++) {
				numberOfDefender.add(numDefenderPlayer);
				numberOfMidfielder.add(numMidfielderPlayer);
			}

		}
		int selectedPermutation = ((int) (Math.random() * 100))
				% numberOfDefender.size();

		defender.setSelectedIndex(numberOfDefender.get(selectedPermutation) - 3);
		midFielder
				.setSelectedIndex(numberOfMidfielder.get(selectedPermutation) - 3);
		attacker.setSelectedIndex(10
				- (numberOfDefender.get(selectedPermutation))
				- (numberOfMidfielder.get(selectedPermutation)));

	}

	private void humanActionPerformed(java.awt.event.ActionEvent evt) {
		if (human.isSelected()) {
			midFielder.setEnabled(true);
			defender.setEnabled(true);
			attacker.setEnabled(true);
		}
	}

	private void StartActionPerformed(java.awt.event.ActionEvent evt) {

		if ((nameField.getText().trim().equals(""))) {
			JOptionPane.showMessageDialog(this,
					"Please enter the name of the Player!");
			return;
		}

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(rootPane,
				"Confirm Selections for this Player?", "Proceed", dialogButton);

		if (dialogResult == 0) {
			if (player2.isSelected()) {
				name2 = nameField.getText();
				if (novice.isSelected()) {
					level2 = 0;
				} else if (intermediate.isSelected()) {
					level2 = 1;
				} else if (expert.isSelected()) {
					level2 = 2;
				}

				/*
				 * if (human.isSelected()) {
				 */
				if (human.isSelected()) {
					mode2 = 0;
				} else {
					mode2 = 1;
				}
				defenders2 = Integer.parseInt((String) defender
						.getSelectedItem());
				midFielders2 = Integer.parseInt((String) midFielder
						.getSelectedItem());
				attackers2 = Integer.parseInt((String) attacker
						.getSelectedItem());

				p2 = true;
			}

			if (player1.isSelected()) {
				name = nameField.getText();
				if (head.isSelected()) {
					p1Choice = CoinFace.HEADS;
				} else if (tail.isSelected()) {
					p1Choice = CoinFace.TAILS;
				}
				if (novice.isSelected()) {
					level = 0;
				} else if (intermediate.isSelected()) {
					level = 1;
				} else if (expert.isSelected()) {
					level = 2;
				}

				if (human.isSelected()) {
					mode = 0;
				} else {
					mode = 1;
				}

				defenders = Integer.parseInt((String) defender
						.getSelectedItem());
				midFielders = Integer.parseInt((String) midFielder
						.getSelectedItem());
				attackers = Integer.parseInt((String) attacker
						.getSelectedItem());
				JOptionPane.showMessageDialog(this,
						"Now enter the Details of Player 2");
				p1 = true;
				nameField.setText("");
				buttonGroup1.clearSelection();
				buttonGroup2.clearSelection();
				buttonGroup3.clearSelection();
				start.setText("Begin");
				player2.setSelected(true);
				player2.setEnabled(true);
				player1.setEnabled(false);
				human.setSelected(true);
				intermediate.setSelected(true);
				head.setVisible(false);
				tail.setVisible(false);
				if (c.toss().equals(p1Choice)) {
					tossWinner = 1;
					JOptionPane
							.showMessageDialog(this, name + " Won the Toss!");

				} else {
					tossWinner = 2;
					JOptionPane.showMessageDialog(this, name
							+ " Lost the Toss!");
				}

			}

			if (p2) {

				start.setEnabled(false);
				this.setEnabled(false);

				MyFrame frame = new MyFrame(name, name2);
				frame.myPanel.getaBall().setTossWinner(tossWinner);
				teamA = new Team(name, attackers, midFielders, defenders, 1,
						frame.myPanel);
				teamA.startTeam();

				teamB = new Team(name2, attackers2, midFielders2, defenders2,
						1, frame.myPanel);
				teamB.startTeam();

				// Set Error Margins based on difficulty
				int xError = 20, yError = 40;
				teamA.setTeamErrorMargins(xError - 5 * level, yError - 10
						* level);
				teamB.setTeamErrorMargins(xError - 5 * level2, yError - 10
						* level2);

				// Set movement speed based on difficulty
				int levelSleepTime = 500;
				if (mode == 1) {
					ComputerPlayer computerPlayer1 = new ComputerPlayer(
							teamA.getPlayersInTeam(), frame.myPanel.getaBall(),
							frame.myPanel, (50 + (2 - level) * levelSleepTime));
					computerPlayer1.start();
				}
				if (mode2 == 1) {
					ComputerPlayer computerPlayer2 = new ComputerPlayer(
							teamB.getPlayersInTeam(), frame.myPanel.getaBall(),
							frame.myPanel, (50 + (2 - level2) * levelSleepTime));
					computerPlayer2.start();
				}
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});

				this.dispose();
			}
		}
	}

	public static void main(String[] args) {
		Configuration t = new Configuration();
		t.setTitle("Configuration Panel");
	}

}
