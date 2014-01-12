package main;

import java.util.Scanner;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.view.GraphicUserInterface;
import de.htwg.monopoly.view.TextUI;

public class Monopoly {

	private static Scanner scanner;

	public static void main(String[] args) {
		IController controller = new Controller();
		TextUI tui = new TextUI(controller);
;
		scanner = new Scanner(System.in);
		
		tui.startGame();
		
		GraphicUserInterface gui = new GraphicUserInterface(controller);
		boolean run = true;
		while (run) {
			run = tui.processInputLine(scanner.next());
		}
		System.out.println("Spiel beendet!");
	}
}
