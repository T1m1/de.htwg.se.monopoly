package main;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.view.TextUI;


public class Monopoly {

	public static void main(String[] args) {
		IController controller = new Controller();
		TextUI tui = new TextUI(controller);
		
		tui.startGame();

	}

}
