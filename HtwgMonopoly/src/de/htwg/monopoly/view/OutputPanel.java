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
import de.htwg.monopoly.observer.Event;

public class OutputPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -3497906149119900058L;
	private static final int LINES = 3;
	private static final int COLUMS = 3;

	private IController contr;

	private JTextArea taOutput;

	/**
	 * create panel for graphic user interf
	 * ace logger
	 * 
	 * @param controller
	 */
	public OutputPanel(IController controller) {
		contr = controller;
        initUI();
	}

    private void initUI() {
        JPanel pOutput = new JPanel();
        taOutput = new JTextArea(LINES, COLUMS);
        taOutput.setEditable(false);
        Border border2 = BorderFactory.createEtchedBorder();
        taOutput.setBorder(border2);

        border2 = BorderFactory.createTitledBorder("Log");
        pOutput.setBorder(border2);
        pOutput.setLayout(new BorderLayout());
        pOutput.add(BorderLayout.CENTER, new JScrollPane(taOutput));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(pOutput);
    }

    public void actionPerformed(Event e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void update() {
		taOutput.setText(contr.getCurrentPlayer().getName() + " (" + contr.getCurrentPlayer().getFigure() + ") : "
                + contr.getMessage());

	}
	
	public JTextArea getTaOutput() {
		return this.taOutput;
	}
}
