package views;
/**
 * Warit Siasakul  5810405339
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import models.Event;

public class DetailView extends JFrame {
	private int frameWidth = 320;
	private int frameHeight = 440;

	private JLabel dateHead = new JLabel("xx xxx xxxx");
	private JLabel timeMid = new JLabel("xx : xx");
	private JButton subButton = new JButton("Submit");
	private JButton delButton = new JButton("Cancel");

	private JTextArea text = new JTextArea();
	
	private String[] hour = new String[24];
	private String[] min = new String[60];
	private String[] day1 = new String[31];
	private String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private String[] year = {"2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"};

	private JComboBox hourCom;
	private JComboBox minCom;
	private JComboBox dCom;
	private JComboBox mCom = new JComboBox(month);
	private JComboBox yCom = new JComboBox(year);

	public void initFrame() {
		for (int i = 0; i < 31; i++) {
			day1[i] = Integer.toString(i+1);
		}
		dCom = new JComboBox(day1);
		for (int i = 0; i < 24; i++) {
			hour[i] = Integer.toString(i);
		}
		hourCom = new JComboBox(hour);
		for (int i = 0; i < 60; i++) {
			min[i] = Integer.toString(i);
		}
		minCom = new JComboBox(min);
		yCom.setSelectedIndex(3);

		dateHead.setFont(new Font("Arial Black", Font.PLAIN, 20));
		timeMid.setFont(new Font("Arial Black", Font.PLAIN, 17));
		setTitle("Event Detail");
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setResizable(false);

		add(createTopDate(), BorderLayout.NORTH);
		add(createDateDetail(), BorderLayout.CENTER);
		add(createDownButton(), BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private JComponent createTopDate() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
		inner.add(createDateSelector());
		inner.add(createTimeSelector());
		return inner;
	}
	private JComponent createDateSelector() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		inner.add(dCom);
		inner.add(mCom);
		inner.add(yCom);
		return inner;
	}
	private JComponent createTimeSelector() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		inner.add(hourCom);
		inner.add(new JLabel(" : "));
		inner.add(minCom);
		return inner;
	}

	private JComponent createDateDetail() {
		JPanel inner = new JPanel();
		text.setFont(new Font("Arial Black", Font.PLAIN, 15));
		JScrollPane areaScrollPane = new JScrollPane(text);
		areaScrollPane.setPreferredSize(new Dimension(300, 300));
		inner.add(areaScrollPane);
		return inner;
	}

	private JComponent createDownButton() {
		JPanel inner = new JPanel();
		inner.setLayout(new GridLayout(1, 2));
		subButton.setPreferredSize(new Dimension(300, 40));
		inner.add(delButton);
		inner.add(subButton);
		return inner;
	}

	public String getComboResult() {
		//"ddMMMyyyyhmm""
		String result = (String) dCom.getSelectedItem() +
				 (String) mCom.getSelectedItem() +
				 (String) yCom.getSelectedItem() + 
				 (String) hourCom.getSelectedItem() + ":" +
				 (String) minCom.getSelectedItem();
		return result;
	}
	
	public void setCombo(Event event) {
		//System.out.println(date.getDate()+" "+date.getMonth()+" "+(date.getYear()+1900)+" "+date.getHours()+" "+date.getMinutes());
		Date date = event.getDate();
		dCom.setSelectedItem(date.getDate());
		mCom.setSelectedItem(date.getMonth());
		yCom.setSelectedItem(date.getYear()+1900);
		hourCom.setSelectedItem(date.getHours());
		minCom.setSelectedItem(date.getMinutes());
		text.setText(event.getDetail());
	}
	
	public String getInputText() {
		return text.getText();
	}
	
	public JButton getSubButton() {
		return subButton;
	}

	public JButton getDelButton() {
		return delButton;
	}
}
