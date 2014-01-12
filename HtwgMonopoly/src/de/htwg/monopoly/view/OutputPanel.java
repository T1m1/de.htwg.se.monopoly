package de.htwg.monopoly.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;

public class OutputPanel extends JPanel implements ActionListener, IObserver {

	
	private static final long serialVersionUID = -3497906149119900058L;

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

	public void actionPerformed(Event e) {
			// ?? wie alle ereignise abfangen und ausgeben?
		// evtl. mitm controller get string
		
		taAusgabe.setText(contr.getMessage());
		System.out.println("Huhu");
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		taAusgabe.setText(contr.getMessage());
		System.out.println("Huhu");
		
	}

	@Override
	public void update(Event e) {
		taAusgabe.setText(contr.getMessage());
		System.out.println("Huhu");
		JOptionPane.showMessageDialog(this, "LALA");
		
	}

	@Override
	public void update(int e) {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		taAusgabe.setText(contr.getCurrentPlayer().getName()+": "+contr.getMessage());
		
	}
}
