package team;

import gameGUI.MyPanel;

import java.util.ArrayList;

import team.abstractClasses.Player;
import team.teamBuilder.TeamBuilder;

public class Team {

	public ArrayList<Player> playersInTeam;
	MyPanel myPanel;

	int numberOfPlayers = 11;
	int numberOfAttackers;
	int numberOfMidFielder;
	int numberOfDefender;
	int numberOfGoalKeeper;
	String countryName;
	public int score;

	// Constructor
	public Team(String countryName, int numberOfAttackers,
			int numberOfMidFielder, int numberOfDefender,
			int numberOfGoalKeeper, MyPanel myPanel) {
		this.numberOfAttackers = numberOfAttackers;
		this.numberOfDefender = numberOfDefender;
		this.numberOfGoalKeeper = numberOfGoalKeeper;
		this.numberOfMidFielder = numberOfMidFielder;
		this.countryName = countryName;
		this.myPanel = myPanel;
		this.BuildTeam(this.myPanel);
		this.score = 0;

		myPanel.setPlayersInTeam(playersInTeam);
	}

	// Constructor
	public Team(String countryName, int numberOfAttackers,
			int numberOfMidFielder, int numberOfDefender, int numberOfGoalKeeper) {
		this.numberOfAttackers = numberOfAttackers;
		this.numberOfDefender = numberOfDefender;
		this.numberOfGoalKeeper = numberOfGoalKeeper;
		this.numberOfMidFielder = numberOfMidFielder;
		this.countryName = countryName;
		this.BuildTeam(this.myPanel);
		this.score = 0;

		myPanel.setPlayersInTeam(playersInTeam);
	}

	public Team(String cName, int p1, int p2, int p3, int p4, int s) {
		this.numberOfAttackers = p1;
		this.numberOfDefender = p3;
		this.numberOfGoalKeeper = p4;
		this.numberOfMidFielder = p2;
		this.countryName = cName;
		this.score = s;
		this.playersInTeam = new ArrayList<Player>();
	}

	public void setMyPanel(MyPanel myPanel) {
		this.myPanel = myPanel;
	}

	private static int noOfTeams = 0;

	// Build Team Function : BuilderPattern
	private void BuildTeam(MyPanel myPanel) {

		int divX = myPanel.getWidth() / 4;
		int baseX = myPanel.getWidth() / 15;
		int height = myPanel.getHeight();

		// Builder Pattern
		TeamBuilder teamBuilder = new TeamBuilder();

		teamBuilder.addGoalKeepers(this.getNumberOfGoalKeeper(), divX, baseX,
				height, myPanel, noOfTeams, countryName);

		teamBuilder.addDefenders(this.getNumberOfDefender(), divX, baseX,
				height, myPanel, noOfTeams, countryName);

		teamBuilder.addMidFielders(this.getNumberOfMidFielder(), divX, baseX,
				height, myPanel, noOfTeams, countryName);

		teamBuilder.addAttackers(this.getNumberOfAttackers(), divX, baseX,
				height, myPanel, noOfTeams, countryName);

		this.playersInTeam = teamBuilder.returnFinalTeam();

		noOfTeams = 1;

	}

	// Sets error margins based on difficulty levels
	public void setTeamErrorMargins(int xErrorMargin, int yErrorMargin) {
		for (Player player : playersInTeam) {
			player.xErrorMargin = xErrorMargin;
			player.yErrorMargin = yErrorMargin;
		}
	}

	// Getter and Setters
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getNumberOfAttackers() {
		return numberOfAttackers;
	}

	public void setNumberOfAttackers(int numberOfAttackers) {
		this.numberOfAttackers = numberOfAttackers;
	}

	public int getNumberOfMidFielder() {
		return numberOfMidFielder;
	}

	public void setNumberOfMidFielder(int numberOfMidFielder) {
		this.numberOfMidFielder = numberOfMidFielder;
	}

	public int getNumberOfDefender() {
		return numberOfDefender;
	}

	public void setNumberOfDefender(int numberOfDefender) {
		this.numberOfDefender = numberOfDefender;
	}

	public int getNumberOfGoalKeeper() {
		return numberOfGoalKeeper;
	}

	public void setNumberOfGoalKeeper(int numberOfGoalKeeper) {
		this.numberOfGoalKeeper = numberOfGoalKeeper;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void printTeam() {
		for (Player player : playersInTeam) {
			System.out.println(player.getPName() + "  " + player.getCategory()
					+ "  " + player.getxAxisPosition() + "  "
					+ player.getyAxisPosition() + "  ");
		}
		System.out.println("Panel : " + myPanel.getWidth() + "  "
				+ myPanel.getHeight());
	}

	public void startTeam() {
		for (Player player : playersInTeam) {
			player.start();
		}
	}

	public ArrayList<Player> getPlayersInTeam() {
		return this.playersInTeam;
	}

}
