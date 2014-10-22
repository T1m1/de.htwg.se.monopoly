package de.htwg.monopoly.game;

import java.util.Scanner;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.view.GraphicUserInterface;
import de.htwg.monopoly.view.TextUI;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Monopoly {

	private static Scanner scanner;
	private static Monopoly instance = null;
	private IController controller;
	private static TextUI tui;
	private static GraphicUserInterface gui;

	public static Monopoly getInstance() {
		if (instance == null) {
			instance = new Monopoly();
		}
		return instance;
	}

	private Monopoly() {

		Injector injector = Guice.createInjector(new MonopolyModule());

		this.controller = injector.getInstance(IController.class);

		tui = new TextUI(controller);

		/*
		 * TODO MUSS VOR DEM AUFRUF VON DER GUI GESTARTET WERDEN DAMIT MAN WEIß
		 * WIE VIELE SPIELER
		 */
		tui.startGame();

		gui = new GraphicUserInterface(controller);

	}

	public TextUI getTextUI() {
		return tui;
	}

	public IController getController() {
		return controller;
	}

	public static void main(String[] args) {

		Monopoly.getInstance();

		gui.run();
		boolean run = true;
		scanner = new Scanner(System.in);
		while (run) {
			run = tui.processInputLine(scanner.next());
		}
		System.out.println("Spiel beendet!");
	}

}
