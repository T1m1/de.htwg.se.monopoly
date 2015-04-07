package de.htwg.monopoly.game;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.view.StartGameUI;
import de.htwg.monopoly.view.TextUI;

import java.util.Scanner;

public class Monopoly {

    private static Monopoly instance = null;
	private IController controller;
	private static TextUI tui;
	private static StartGameUI start;


	private static Monopoly getInstance() {
		if (instance == null) {
			instance = new Monopoly();
		}
		return instance;
	}

	private Monopoly() {

		Injector injector = Guice.createInjector(new MonopolyModule());

		this.controller = injector.getInstance(IController.class);
		//this.controller = new Controller(IMonopolyUtil.FIELD_SIZE);

		start = new StartGameUI(controller);
		tui = new TextUI(controller);

	}

	public TextUI getTextUI() {
		return tui;
	}

	public IController getController() {
		return controller;
	}

	public static void main(String[] args) {

		Monopoly.getInstance();

		// Note: it is important to start gui first, otherwise the tui waits for input. Maybe fix it.
		start.startGame();
		tui.startGame();

		boolean run = true;
        Scanner scanner = new Scanner(System.in);
		while (run) {
			run = tui.processInputLine(scanner.next());
		}
		System.out.println("Spiel beendet!");
		scanner.close();
	}

}
