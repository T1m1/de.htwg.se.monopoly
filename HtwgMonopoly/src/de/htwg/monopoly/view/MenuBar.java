package de.htwg.monopoly.view;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;

public class MenuBar extends JMenuBar implements ActionListener {

	private final Logger logger = LogManager.getLogger("MenuBar");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1262745107060789415L;

	private IController controller;

	private JMenuItem miSource, miRegeln;
	private JMenuItem miNewGame;
	private JMenuItem miExitGame;

	private static final String RULES = "http://de.wikipedia.org/wiki/Monopoly";
	private static final String SOURCE = "https://github.com/T1m1/de.htwg.se.monopoly";

	public MenuBar(IController controller) {
		this.controller = controller;

		// Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		JMenu mDatei = new JMenu("Game");
		JMenu mHelp = new JMenu("Help");
		menuBar.add(mDatei);
		menuBar.add(mHelp);

		miNewGame = new JMenuItem("Neues Spiel starten...");
		miNewGame.addActionListener(this);
		mDatei.add(miNewGame);

		JMenuItem subMenu = new JMenuItem("Optionen Einstellen");
		mDatei.add(subMenu);

		mDatei.addSeparator();

		miExitGame = new JMenuItem("Spiel beenden");
		miExitGame.addActionListener(this);
		mDatei.add(miExitGame);

		miSource = new JMenuItem("Source Code");
		miSource.addActionListener(this);
		mHelp.add(miSource);
		miRegeln = new JMenuItem("Monopoly Rules");
		miRegeln.addActionListener(this);
		mHelp.add(miRegeln);

		this.add(menuBar);

	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (event.equals(miExitGame)) {
			System.exit(0);
		} else if (event.equals(miSource)) {
			showURL(SOURCE);
		} else if (event.equals(miRegeln)) {
			showURL(RULES);

		} else if (event.equals(miNewGame)) {
			//controller.startNewGame();
		}

	}

	private void showURL(String source) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		try {
			desktop.browse(new URI(source));
		} catch (Exception e) {
			logger.info("Exception in MenuBar");

		}

	}
}