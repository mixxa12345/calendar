package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class EventPanel extends JPanel {

	private JLabel dateHead = new JLabel("xx xxx xxxx");
	private JButton xButton = new JButton("x");
	private int id;

	public EventPanel(String s, String d, int id) {
		this.id = id;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new LineBorder(Color.BLACK, 3));
		dateHead.setFont(new Font("Arial Black", Font.PLAIN, 12));
		dateHead.setText(s);
		this.add(createHeader());
		createDateDetail(d);
		
		xButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				del();
			}
		});
	}

	private void del(){
		this.removeAll();
		this.revalidate();
	}
	
	public JComponent createHeader() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		inner.add(dateHead);
		inner.add(Box.createRigidArea(new Dimension(115, 10)));
		inner.add(xButton);
		return inner;
	}
	
	public JComponent createDateDetail(String d) {
		JPanel inner = new JPanel();
		JTextArea text = new JTextArea("   " + d);
		text.setFont(new Font("Arial Black", Font.PLAIN, 15));
		text.setEditable(true);
		JScrollPane areaScrollPane = new JScrollPane(text);
		areaScrollPane.setPreferredSize(new Dimension(300, 80));
		this.add(areaScrollPane);
		return inner;
	}

	public int sendSignal() {
		return id;
	}
	
	public JButton getXButton() {
		return xButton;
	}
}
