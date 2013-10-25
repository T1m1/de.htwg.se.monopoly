package de.htwg.monopoly.entities;

public class Player {
		String name;
		char figure;
		double budget;
		String ownership[];
		int position;
		
		public Player(String name, char figure, double budget,
				String[] ownership, int position) {
			super();
			this.name = name;
			this.figure = figure;
			this.budget = budget;
			this.ownership = ownership;
			this.position = position;
		}
		
		public Player(String name, char figure, double budget) {
			this(name, figure, budget, null, 1);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public char getFigure() {
			return figure;
		}

		public void setFigure(char figure) {
			this.figure = figure;
		}

		public double getBudget() {
			return budget;
		}

		public void setBudget(double budget) {
			this.budget = budget;
		}

		public String[] getOwnership() {
			return ownership;
		}

		public void setOwnership(String[] ownership) {
			this.ownership = ownership;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		

}
