package de.htwg.monopoly.view;

import de.htwg.monopoly.controller.IController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutputPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -3497906149119900058L;
	private static final int LINES = 3;
	private static final int COLUMNS = 3;

	private final IController contr;

	private JTextArea taOutput;

	/**
	 * create panel for graphic user interface
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
        taOutput = new JTextArea(LINES, COLUMNS);
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

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void update() {
		taOutput.setText(contr.getCurrentPlayer().getName() + " (" + contr.getCurrentPlayer().getIcon().getDescription() + ") : "
                + contr.getMessage());

	}
	
	public JTextArea getTaOutput() {
		return this.taOutput;
	}
}
