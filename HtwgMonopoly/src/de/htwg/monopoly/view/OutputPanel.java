package de.htwg.monopoly.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.impl.Controller;

public class OutputPanel extends JPanel implements ActionListener {

	private IController contr;

	private JTextArea taAusgabe;

	/**
	 * create panel for graphic user interface logger
	 * 
	 * @param controller
	 */
	public OutputPanel(IController controller) {
		contr = controller;

		JPanel pAusgabe = new JPanel();
		taAusgabe = new JTextArea(3, 20);
		taAusgabe.setEditable(false);
		Border border2 = BorderFactory.createEtchedBorder();
		taAusgabe.setBorder(border2);

		border2 = BorderFactory.createTitledBorder("Log");
		pAusgabe.setBorder(border2);
		pAusgabe.setLayout(new BorderLayout());
		pAusgabe.add(BorderLayout.CENTER, new JScrollPane(taAusgabe));

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(pAusgabe);

	}

	public void actionPerformed(ActionEvent e) {
			// ?? wie alle ereignise abfangen und ausgeben?
		// evtl. mitm controller get string
	}
}
