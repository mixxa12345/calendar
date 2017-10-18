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
import javax.swing.JTextField;

import controllers.DBController;
import controllers.EditController;
import models.Event;
public class MenuPanel extends JPanel {
	private Color c = new Color(0, 0, 0);
	private JPanel eventFlow = new JPanel();
	private JButton createButton = new JButton("Create..");
	private JButton searchButton = new JButton("Search..");
	private JButton dayButton = new JButton("  Day  ");
	private DetailView dView = new DetailView();
	ArrayList<Event> calendar = new ArrayList<>();
	private EditController editor = new EditController(dView, calendar, this);
	private ExtendView exView = new ExtendView(calendar, editor);
	private SearchView schView = new SearchView(calendar, editor);

	public MenuPanel() {
		dView.initFrame();
		eventFlow.setLayout(new BoxLayout(eventFlow, BoxLayout.Y_AXIS));
		eventFlow.setBackground(Color.BLACK);
		this.add(CreateTopMenu(), BorderLayout.EAST);
		this.add(exView, BorderLayout.WEST);
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
		
		searchButton.setPreferredSize(new Dimension(100, 20));
		searchButton.setFont(new Font("Arial Black", Font.PLAIN, 15));
		createButton.setPreferredSize(new Dimension(100, 50));
		createButton.setFont(new Font("Arial Black", Font.PLAIN, 15));

		JPanel inn = new JPanel();
		inn.setBackground(c);
		inn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		inn.add(head);
		inn.add(Box.createRigidArea(new Dimension(35, 10)));
		inn.add(searchButton);
		inn.add(createButton);

		inner.add(inn, BorderLayout.NORTH);
		inner.add(searchButton);
		

		JScrollPane EScroll = new JScrollPane(eventFlow);
		EScroll.setPreferredSize(new Dimension(330, 680));
		inner.add(EScroll, BorderLayout.SOUTH);

		return inner;
	}

	public Event addEventPanel() throws ParseException {
		eventFlow.removeAll();
		exView.getEventFlow().removeAll();
		Event ev = new Event(dView.getComboResult(), dView.getInputText());
		ev.setRepeater(dView.getRepeater());
		if (calendar.size() > 0) {
			ev.setId(calendar.get(calendar.size() - 1).getId() + 1);
		}
		calendar.add(ev);
		calendarToPanel();
		return ev;
	}

	public void calendarToPanel() {
		//check event#
		int normalCount = 0,repeatCount = 0;
		for (Event ev : calendar) {
			if (ev.getRepeater().equals("-")) {
				normalCount++;
			} else {
				repeatCount++;
			}
		}
		if (normalCount == 0) {
			MainView.displayEmpty(getEventFlow());
			if (repeatCount == 0) {
				return;
			}
		} 

		ArrayList<Event> viewList = EditController.sortArrayList(calendar);
		for (int i = 0; i < viewList.size(); i++) {
			Event current = viewList.get(i);
			EventPanel ev = new EventPanel(current.getDate().toLocaleString(), current.getDetail(), current.getId(),
					editor);
			if (current.getRepeater().equals("-")) {
				//add to view1
				eventFlow.add(ev);
			} else {
				//add to view2
				exView.stateAction();
			}

		}
	}
	
	public Event removeEvent(int id) {
		for (Event event : calendar) {
			if (event.getId() == id) {
				// System.out.println("CLEAR");
				calendar.remove(event);
				return event;
			}
		}
		return null;
	}
	
	public EditController getEditor() {
		return editor;
	}

	public ArrayList<Event> getCalendar() {
		return calendar;
	}

	public JPanel getEventFlow() {
		return eventFlow;
	}

	public void setDBOnEditor(DBController DBC) {
		editor.setDBC(DBC);
	}

	public DetailView getDView() {
		return dView;
	}
	
	public ExtendView getExView() {
		return exView;
	}
	
	public SearchView getSchView() {
		return schView;
	}

	public JButton getDayButton() {
		return dayButton;
	}

	public JButton getCreateButton() {
		return createButton;
	}
	
	public JButton getSearchButton() {
		return searchButton;
	}
}