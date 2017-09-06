package views;
/**
 * Warit Siasakul  5810405339
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.DBController;
import controllers.EditController;
import models.Event;

public class MenuPanel extends JPanel {
	private Color c = new Color(0, 0, 0);
	private JPanel header = new JPanel();
	private JPanel midder;
	private JPanel eventFlow = new JPanel();
	private JButton createButton = new JButton("Create");
	private JButton dayButton = new JButton("  Day  ");
	private JButton weekButton = new JButton("Week");
	private JButton monthButton = new JButton("Month");
	private JButton allButton = new JButton("   All   ");

	private DetailView dView = new DetailView();
	ArrayList<Event> calendar = new ArrayList<>();
	private EditController editor;

	public MenuPanel() {
		editor = new EditController(dView, calendar);
		dView.initFrame();
		eventFlow.setLayout(new BoxLayout(eventFlow, BoxLayout.Y_AXIS));
		eventFlow.setBackground(Color.BLACK);
		midder = (JPanel) CreateMidMenu();
		this.add(CreateTopMenu(), BorderLayout.NORTH);
		this.setBackground(c);
	}

	private JComponent CreateTopMenu() {
		JPanel inner = new JPanel();
		inner.setLayout(new BorderLayout());
		inner.setBackground(c);
		JLabel head = new JLabel("Date Calendar");
		head.setForeground(Color.WHITE);
		head.setFont(new Font("Arial Black", Font.PLAIN, 20));
		head.setBackground(c);
		head.setOpaque(true);
		createButton.setPreferredSize(new Dimension(100, 50));
		createButton.setFont(new Font("Arial Black", Font.PLAIN, 15));
		// createButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		JPanel inn = new JPanel();
		inn.setBackground(c);
		inn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		inn.add(head);
		inn.add(Box.createRigidArea(new Dimension(30, 10)));
		inn.add(createButton);

		inner.add(inn, BorderLayout.NORTH);
		inner.add(Box.createRigidArea(new Dimension(10, 15)));

		JScrollPane EScroll = new JScrollPane(eventFlow);
		EScroll.setPreferredSize(new Dimension(325, 680));
		inner.add(EScroll, BorderLayout.SOUTH);

		return inner;
	}

	private JComponent CreateMidMenu() {
		JPanel inner = new JPanel();
		inner.setBackground(Color.WHITE);
		inner.setLayout(new FlowLayout(FlowLayout.RIGHT));
		inner.add(dayButton);
		inner.add(weekButton);
		inner.add(monthButton);
		inner.add(allButton);
		return inner;
	}

	public Event addEventPanel() throws ParseException {
		eventFlow.removeAll();
		Event ev = new Event(dView.getComboResult(), dView.getInputText());
		if (calendar.size()>0) {
			ev.setId(calendar.get(calendar.size()-1).getId()+1);
		}
		calendar.add(ev);
		calendarToPanel();
		return ev;
	}

	public void calendarToPanel() {
		ArrayList<Event> viewList = calendar;
		Collections.sort(viewList, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				Date oo1 = o1.getDate();
				Date oo2 = o2.getDate();
				if (oo1.getTime() < oo2.getTime())
					return -1;
				else if (oo1.getTime() == oo2.getTime())
					return 0;
				else
					return 1;
			}
		});
		for (int i = 0; i < viewList.size(); i++) {
			Event current = viewList.get(i);
			EventPanel ev = new EventPanel(current.getDate().toLocaleString(), current.getDetail(), current.getId(), editor);
			eventFlow.add(ev);
		}
	}

	public ArrayList<Event> getCalendar() {
		return calendar;
	}

	public JPanel getEventFlow() {
		return eventFlow;
	}

	public void setDBOnEditor(DBController DBC) {
		editor.setDBC(DBC);
		;
	}

	public DetailView getDView() {
		return dView;
	}

	public JButton getDayButton() {
		return dayButton;
	}

	public JButton getCreateButton() {
		return createButton;
	}
}