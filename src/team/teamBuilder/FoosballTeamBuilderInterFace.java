package team.teamBuilder;

import gameGUI.MyPanel;

import java.util.ArrayList;

import team.abstractClasses.Player;

public interface FoosballTeamBuilderInterFace {

	public ArrayList<Player> returnFinalTeam();

	public TeamBuilder addDefenders(int noOfDefenders, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName);

	public TeamBuilder addMidFielders(int noOfMidFielders, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName);

	public TeamBuilder addAttackers(int noOfAttackers, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName);

	public TeamBuilder addGoalKeepers(int noOfGoalKeepers, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName);

}
