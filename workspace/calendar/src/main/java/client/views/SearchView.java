package client.views;
/**
 * Warit Siasakul  5810405339
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import common.ActionComponent;
import common.models.Event;

public class SearchView extends JFrame {
	private int frameWidth = 640;
	private int frameHeight = 700;
	private ArrayList<Event> calendar;
	private ActionComponent actionComponent;
	private JPanel eventFlow = new JPanel();
	private JButton sButton = new JButton("   search   ");

	private String[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String[] month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private String[] year = { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010" };

	private JComboBox dCom = new JComboBox(day);;
	private JComboBox mCom = new JComboBox(month);
	private JComboBox yCom = new JComboBox(year);

	private MenuPanel parent;


	public SearchView(ArrayList<Event> calendar, ActionComponent actionComponent) {
		this.calendar = calendar;
		this.actionComponent = actionComponent;

		((JLabel) dCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) mCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) yCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		setTitle("Search Window");
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(false);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				eventFlow.removeAll();
				e.getWindow().dispose();
			}
		});
		sButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				search((String) dCom.getSelectedItem(), (String) mCom.getSelectedItem(),
						(String) yCom.getSelectedItem());
			}
		});

		eventFlow.setLayout(new BoxLayout(eventFlow, BoxLayout.Y_AXIS));
		eventFlow.setBackground(Color.BLACK);
		yCom.setSelectedIndex(3);
		this.add(createDateSelector(), BorderLayout.NORTH);
		JScrollPane EScroll = new JScrollPane(eventFlow);
		this.add(EScroll, BorderLayout.CENTER);
	}

	public void search(String d, String m, String y) {
		int count = 0;
		eventFlow.removeAll();
		ArrayList<Event> viewList = calendar;
		viewList.sort(Comparator.comparing(Event::getDate));
		for (int i = 0; i < viewList.size(); i++) {
			Event current = viewList.get(i);
			String cd = Integer.toString(current.getDate().getDate());
			String cm = month[current.getDate().getMonth()];
			String cy = Integer.toString(current.getDate().getYear() + 1900);
			if (d.equals(cd) && m.equals(cm) && y.equals(cy)) {
				count++;
				EventPanel ev = new EventPanel(current.getDate().toLocaleString(), current.getDetail(), current.getId(),
						actionComponent);
				ev.setParent(parent);
				eventFlow.add(ev.mod(current.getRepeater()));
			}
		}
		if (count == 0) {
			MainView.displayEmpty(getEventFlow());
		}
		eventFlow.revalidate();
	}

	public void changeSearch() {
		search((String) dCom.getSelectedItem(), (String) mCom.getSelectedItem(),
				(String) yCom.getSelectedItem());
	}

	private JComponent createDateSelector() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		inner.add(dCom);
		inner.add(mCom);
		inner.add(yCom);
		inner.add(sButton);
		return inner;
	}

	public JPanel getEventFlow() {
		return eventFlow;
	}

	public void setParent(MenuPanel parent) {
		this.parent = parent;
	}

}
