package de.htwg.monopoly.entities;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActionType() {
		// TODO Auto-generated method stub
		return null;
	}

}
