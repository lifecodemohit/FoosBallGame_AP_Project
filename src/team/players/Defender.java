package team.players;

import gameGUI.MyPanel;
import team.abstractClasses.Player;
import team.enums.Category;

public class Defender extends Player {

	public Defender(String name, int age, String country, MyPanel myPanel,
			int movementLimit, int baseX, int baseY) {
		super(name, age, country, myPanel, movementLimit, baseX, baseY);
		this.setCategory(Category.Defender);
	}

	public Defender(String name, String country, int x, int y) {
		super(name, country, x, y);
		this.setCategory(Category.Defender);
	}
}