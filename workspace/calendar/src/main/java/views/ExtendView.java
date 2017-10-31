package views;
/**
 * Warit Siasakul  5810405339
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controllers.EditController;
import models.Event;

public class ExtendView extends JPanel {

	private JPanel eventFlow = new JPanel();
	private EditController editor;
	private int state, evCount = 0;
	private JButton allButton = new JButton("   All   ");
	private JButton dayButton = new JButton("  Daily  ");
	private JButton weekButton = new JButton("Weekly");
	private JButton monthButton = new JButton("Monthly");
	private JButton yearButton = new JButton(" Yearly ");
	private JButton[] buttons = { allButton, dayButton, weekButton, monthButton, yearButton };
	ArrayList<Event> calendar;

	private MenuPanel parent;

	public ExtendView(ArrayList<Event> c, EditController editor) {
		this.editor = editor;
		calendar = c;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		eventFlow.setLayout(new BoxLayout(eventFlow, BoxLayout.Y_AXIS));
		eventFlow.setBackground(Color.BLACK);
		JScrollPane EScroll = new JScrollPane(eventFlow);
		EScroll.setPreferredSize(new Dimension(640, 700));
		this.add(CreateMidMenu());
		this.add(EScroll);
		//stateAction();
	}

	public void stateAction() {
		buttons[state].setBackground(Color.WHITE);
		for (JButton button : buttons) {
			if (buttons[state] != button) {
				button.setBackground(new Color(210, 210, 210));
			}
		}
		eventFlow.removeAll();
		evCount = 0;
		for (int i = 0; i < calendar.size(); i++) {
			if (!calendar.get(i).getRepeater().equals("-")) {
				Event ev = calendar.get(i);
				EventPanel ep = new EventPanel(ev.getDate().toLocaleString(), ev.getDetail(), ev.getId(), editor);
				ep.setParent(parent);
				estimate(ep, calendar.get(i).getRepeater());
			}
		}
		if (evCount == 0) {
			MainView.displayEmpty(getEventFlow());
		}
		this.revalidate();
	}

	public void estimate(EventPanel ep, String repeater) {
		EventPanel epp = ep;
		String[] states = { "-", "d", "w", "m", "y" };
		String[] stateFulls = { "-", "Daily", "Weekly", "Monthly", "Yearly" };
		if (this.state != 0) {
			for (int i = 0; i < states.length; i++) {
				if (repeater.equals(states[i]) && this.state == i) {
					eventFlow.add(epp.mod(stateFulls[i]));
					evCount++;
				}
			}
		} else if (!repeater.equals(states[0])) {
			for (int i = 0; i < states.length; i++) {
				if (repeater.equals(states[i])) {
					eventFlow.add(epp.mod(stateFulls[i]));
					evCount++;
				}
			}
		}
	}



	private void addButtonListener(JButton b, int i) {
		final int s = i;
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state = s;
				stateAction();
			}
		});
	}

	private JComponent CreateMidMenu() {
		JPanel inner = new JPanel();
		inner.setBackground(new Color(210, 210, 210));
		inner.setLayout(new FlowLayout(FlowLayout.RIGHT));

		allButton.setBackground(new Color(210, 210, 210));
		dayButton.setBackground(new Color(210, 210, 210));
		weekButton.setBackground(new Color(210, 210, 210));
		monthButton.setBackground(new Color(210, 210, 210));
		yearButton.setBackground(new Color(210, 210, 210));
		addButtonListener(allButton, 0);
		addButtonListener(dayButton, 1);
		addButtonListener(weekButton, 2);
		addButtonListener(monthButton, 3);
		addButtonListener(yearButton, 4);

		JLabel text = new JLabel("Repeating :");
		text.setFont(new Font("Arial Black", Font.PLAIN, 15));
		inner.add(text);
		inner.add(Box.createRigidArea(new Dimension(35, 10)));
		inner.add(dayButton);
		inner.add(weekButton);
		inner.add(monthButton);
		inner.add(yearButton);
		inner.add(allButton);
		return inner;
	}

	public JPanel getEventFlow() {
		return eventFlow;
	}

	public void setParent(MenuPanel parent) {
		this.parent = parent;
	}
}
