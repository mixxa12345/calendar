package client.views;
/**
 * Warit Siasakul  5810405339
 */
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import common.ActionComponent;
import common.models.Event;

public class EventPanel extends JPanel {

	private JLabel dateHead = new JLabel("xx xxx xxxx");
	private JButton xButton = new JButton("X");
	private JButton modButton = new JButton(" O ");
	private int id;
	private JPanel header;
	private JTextArea text = new JTextArea();
	private MenuPanel parent;

	public EventPanel(String s, String d, int iid, ActionComponent actionComponent) {
		this.id = iid;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new LineBorder(Color.BLACK, 3));
		dateHead.setFont(new Font("Arial Black", Font.PLAIN, 12));
		dateHead.setText(s);
		header = createHeader();
		this.add(header);
		createDateDetail(d);

		//delete
		xButton.addActionListener((ActionEvent arg0) -> {
            actionComponent.requestDelete(id);
            parent.removeEvent(id);
            decompose();
            System.out.println("deleted");
        });
		//modify
		modButton.addActionListener(arg0 -> select(id));
	}

	private void decompose(){
		this.removeAll();
		this.revalidate();
		parent.refreshScene();
	}

	public void select(int id) {
		Event target = null;
		for (Event ev : parent.getCalendar()) {
			if (ev.getId() == id) {
				target = ev;
			}
		}
		parent.getdView().setCombo(target);
		parent.getdView().setId(id);
		parent.getdView().setVisible(true);
	}
	
	public JPanel createHeader() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		inner.add(xButton);
		inner.add(modButton);
		inner.add(Box.createRigidArea(new Dimension(65, 10)));
		inner.add(dateHead);
		return inner;
	}
	
	public JComponent createDateDetail(String d) {
		JPanel inner = new JPanel();
		text.setText(d);
		text.setFont(new Font("Arial Black", Font.PLAIN, 15));
		text.setEditable(false);
		JScrollPane areaScrollPane = new JScrollPane(text);
		areaScrollPane.setPreferredSize(new Dimension(300, 80));
		this.add(areaScrollPane);
		return inner;
	}
	
	public EventPanel mod(String s) {
		JLabel label = new JLabel(s + "      ");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getHeader().add(Box.createRigidArea(new Dimension(220, 10)));
		getHeader().add(label);
		return this;
	}

	public void setParent(MenuPanel parent) {
		this.parent = parent;
	}

	public JPanel getHeader() {
		return header;
	}
	
}
