package de.htwg.monopoly.game;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.view.GraphicUserInterface;
import de.htwg.monopoly.view.StartGameUI;
import de.htwg.monopoly.view.TextUI;

import java.util.Scanner;

public final class Monopoly {

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

	private static Injector injector = Guice
			.createInjector(new MonopolyModule());

	private Monopoly() {

		// TODO: configure log4j

		// get instances from Guice
		controller = injector.getInstance(IController.class);
		tui = injector.getInstance(TextUI.class);
		GraphicUserInterface gui = injector.getInstance(GraphicUserInterface.class);

		// create welcome panel
		start = new StartGameUI(controller, gui);

	}

	public TextUI getTextUI() {
		return tui;
	}

	public IController getController() {
		return controller;
	}

	public static void main(String[] args) {

		Monopoly.getInstance();

		// Note: it is important to start gui first, otherwise the tui waits for
		// input. Maybe fix it.
		start.startGame();
		tui.startGame();

		Scanner scanner = new Scanner(System.in);
		boolean run = true;
		while (run) {
			run = tui.processInputLine(scanner.next());
		}
		scanner.close();
	}

}
