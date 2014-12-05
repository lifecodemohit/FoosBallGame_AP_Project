package team.players;

import gameGUI.MyPanel;
import team.abstractClasses.Player;
import team.enums.Category;

public class PlayerFactory {

	// Factory Method on team players
	public static Player getNewPlayer(Category category, String name, int age,
			String country, MyPanel myPanel, int movementLimit, int baseX,
			int baseY) {
		Player player = null;

		if (category.equals(Category.Attacker))
			player = new Attacker(name, age, country, myPanel, movementLimit,
					baseX, baseY);

		if (category.equals(Category.MidFielder))
			player = new MidFielder(name, age, country, myPanel, movementLimit,
					baseX, baseY);

		if (category.equals(Category.Defender))
			player = new Defender(name, age, country, myPanel, movementLimit,
					baseX, baseY);

		if (category.equals(Category.GoalKeeper))
			player = new GoalKeeper(name, age, country, myPanel, movementLimit,
					baseX, baseY);

		return player;

	}

}
