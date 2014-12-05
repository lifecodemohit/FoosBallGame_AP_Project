package team.players;

import gameGUI.MyPanel;
import team.abstractClasses.Player;
import team.enums.Category;

public class MidFielder extends Player {

	public MidFielder(String name, int age, String country, MyPanel myPanel,
			int movementLimit, int baseX, int baseY) {
		super(name, age, country, myPanel, movementLimit, baseX, baseY);
		this.setCategory(Category.MidFielder);
	}

}