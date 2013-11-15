package de.htwg.monopoly.Cards;

public class CommunityCard implements ICards {

	private String description;

	public CommunityCard(String descr) {
		this.description = descr;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getActionType() {
		// TODO Auto-generated method stub
		return null;
	}

}
