package de.htwg.monopoly.entities;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommunityCardsStack extends CardsStack {

	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	public CommunityCardsStack() {
		super();
		pushOnTop(new CommunityCard(bundle.getString("comm_1"), "move",
				bundle.getString("comm_1.1"), 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_2"),
				bundle.getString("comm_2.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_3"),
				bundle.getString("comm_3.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_4"),
				bundle.getString("comm_4.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_5"),
				bundle.getString("comm_5.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_6"),
				bundle.getString("comm_6.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_7"),
				bundle.getString("comm_7.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_8"),
				bundle.getString("comm_8.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_9"),
				bundle.getString("comm_9.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_10"),
				bundle.getString("comm_10.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_11"),
				bundle.getString("comm_11.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_12"),
				bundle.getString("comm_12.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_13"),
				bundle.getString("comm_13.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_14"),
				bundle.getString("comm_14.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_15"),
				bundle.getString("comm_15.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_16"),
				bundle.getString("comm_16.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_17"),
				bundle.getString("comm_17.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_18"),
				bundle.getString("comm_18.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_19"),
				bundle.getString("comm_19.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_20"),
				bundle.getString("comm_20.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_21"),
				bundle.getString("comm_21.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_22"),
				bundle.getString("comm_22.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_23"),
				bundle.getString("comm_23.1"), "move", 0, 0, false));
		pushOnTop(new CommunityCard(bundle.getString("comm_24"),
				bundle.getString("comm_24.1"), "move", 0, 0, false));

		this.shuffle();
	}

	// @Override
	public char getType() {
		// g wie Gemeinschaftsfeld.
		return 'g';
	}

	@Override
	public String toString() {
		return "Gemeinschaftsfeld";
	}

}
