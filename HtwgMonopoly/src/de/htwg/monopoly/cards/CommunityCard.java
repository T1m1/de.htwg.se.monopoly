package de.htwg.monopoly.cards;

public class CommunityCard implements ICards {

	private String description;
	private String actionType;

	public CommunityCard(String descr, String action) {
		this.description = descr;
		this.actionType = action;
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
		return actionType;
	}

}
