package views;
/**
 * Warit Siasakul  5810405339
 */
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

import controllers.EditController;

public class EventPanel extends JPanel {

	private JLabel dateHead = new JLabel("xx xxx xxxx");
	private JButton xButton = new JButton("X");
	private JButton modButton = new JButton(" O ");
	private int id;
	private JPanel header;
	JTextArea text = new JTextArea();

	public EventPanel(String s, String d, int iid, EditController Editor) {
		this.id = iid;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new LineBorder(Color.BLACK, 3));
		dateHead.setFont(new Font("Arial Black", Font.PLAIN, 12));
		dateHead.setText(s);
		header = createHeader();
		this.add(header);
		createDateDetail(d);
		
		xButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				del();
				Editor.acceptDelete(id);
				
			}
		});
		modButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Editor.acceptModify(id);
			}
		});
	}

	private void del(){
		this.removeAll();
		this.revalidate();
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
	
	public int sendSignal() {
		return id;
	}
	
	public JPanel getHeader() {
		return header;
	}
	
	public JButton getXButton() {
		return xButton;
	}

	public JTextArea getText() {
		return text;
	}

	public void setText(String text) {
		this.text.setText(text);
	}
	
}
