package team.teamBuilder;

import gameGUI.MyPanel;

import java.util.ArrayList;

import team.abstractClasses.Player;
import team.enums.Category;
import team.players.PlayerFactory;

public class TeamBuilder implements FoosballTeamBuilderInterFace {

	ArrayList<Player> playersInTeam = new ArrayList<Player>();;

	public ArrayList<Player> returnFinalTeam() {
		return playersInTeam;
	}

	public TeamBuilder addDefenders(int noOfDefenders, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName) {
		int divY = height / (noOfDefenders + 1);
		for (int i = 0; i < noOfDefenders; i++) {
			if (noOfTeams == 0)
				playersInTeam.add(PlayerFactory.getNewPlayer(Category.Defender,
						"Defender" + (i + 1), 19, countryName, myPanel, divY,
						(divX - baseX), (i + 1) * divY));
			else
				playersInTeam.add(PlayerFactory
						.getNewPlayer(Category.Defender, "Defender" + (i + 1),
								19, countryName, myPanel, divY,
								myPanel.getWidth() - (divX - baseX) - 5,
								(i + 1) * divY));
		}

		return this;
	}

	public TeamBuilder addMidFielders(int noOfMidFielders, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName) {
		int divY = height / (noOfMidFielders + 1);
		for (int i = 0; i < noOfMidFielders; i++) {
			if (noOfTeams == 0)
				playersInTeam.add(PlayerFactory.getNewPlayer(
						Category.MidFielder, "Midfielder" + (i + 1), 19,
						countryName, myPanel, divY, ((2 * divX) - baseX),
						(i + 1) * divY));
			else
				playersInTeam.add(PlayerFactory.getNewPlayer(
						Category.MidFielder, "Midfielder" + (i + 1), 19,
						countryName, myPanel, divY, myPanel.getWidth()
								- ((2 * divX) - baseX) - 5, (i + 1) * divY));
		}
		return this;
	}

	public TeamBuilder addAttackers(int noOfAttackers, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName) {
		int divY = height / (noOfAttackers + 1);
		for (int i = 0; i < noOfAttackers; i++) {
			if (noOfTeams == 0)
				playersInTeam.add(PlayerFactory.getNewPlayer(Category.Attacker,
						"Attacker" + (i + 1), 19, countryName, myPanel, divY,
						((3 * divX) - baseX), (i + 1) * divY));
			else
				playersInTeam.add(PlayerFactory.getNewPlayer(Category.Attacker,
						"Attacker" + (i + 1), 19, countryName, myPanel, divY,
						myPanel.getWidth() - ((3 * divX) - baseX) - 5, (i + 1)
								* divY));
		}

		return this;
	}

	public TeamBuilder addGoalKeepers(int noOfGoalKeepers, int divX, int baseX,
			int height, MyPanel myPanel, int noOfTeams, String countryName) {
		int divY = height / (noOfGoalKeepers + 1);
		for (int i = 0; i < noOfGoalKeepers; i++) {
			if (noOfTeams == 0)
				playersInTeam.add(PlayerFactory.getNewPlayer(
						Category.GoalKeeper, "Goalkeeper", 19, countryName,
						myPanel, divY / 2, baseX, divY));
			else
				playersInTeam
						.add(PlayerFactory.getNewPlayer(Category.GoalKeeper,
								"Goalkeeper", 19, countryName, myPanel,
								divY / 2, myPanel.getWidth() - baseX - 5, divY));

		}

		return this;
	}

}
