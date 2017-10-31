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
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import models.Event;

public class DetailView extends JFrame {
	private int frameWidth = 320;
	private int frameHeight = 440;

	private int id = -1;

	private JLabel dateHead = new JLabel("xx xxx xxxx");
	private JLabel timeMid = new JLabel("xx : xx");
	private JButton subButton = new JButton("Submit");
	private JButton delButton = new JButton("Cancel");

	private JTextArea text = new JTextArea();

	private String[] hour = new String[24];
	private String[] min = new String[60];
	private String[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String[] month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private String[] year = { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010" };

	private JComboBox hourCom;
	private JComboBox minCom;
	private JComboBox dCom = new JComboBox(day);;
	private JComboBox mCom = new JComboBox(month);
	private JComboBox yCom = new JComboBox(year);

	private ButtonGroup repeater = new ButtonGroup();

	public DetailView() {
		delButton.addActionListener(arg -> {
			this.id = -1;
			this.setVisible(false);
		});
	}

	public void initFrame() {
		for (int i = 0; i < 24; i++) {
			hour[i] = Integer.toString(i);
		}
		hourCom = new JComboBox(hour);
		for (int i = 0; i < 60; i++) {
			min[i] = Integer.toString(i);
		}
		minCom = new JComboBox(min);
		yCom.setSelectedIndex(3);
		
		((JLabel)minCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel)hourCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel)dCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel)mCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel)yCom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		dateHead.setFont(new Font("Arial Black", Font.PLAIN, 20));
		timeMid.setFont(new Font("Arial Black", Font.PLAIN, 17));
		setTitle("Event Detail");
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setResizable(false);

		add(createTopHeader(), BorderLayout.NORTH);
		add(createDateDetail(), BorderLayout.CENTER);
		add(createDownButton(), BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private JComponent createTopHeader() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
		inner.add(createTopDate());
		inner.add(createRepeaterHeader());
		return inner;
	}

	private JComponent createRepeaterHeader() {
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		JRadioButton nButton = new JRadioButton("---");
		JRadioButton dButton = new JRadioButton("Daily");
		JRadioButton wButton = new JRadioButton("Weekly");
		JRadioButton mButton = new JRadioButton("Monthly");
		JRadioButton yButton = new JRadioButton("Yearly");
		nButton.setActionCommand("-");
		dButton.setActionCommand("d");
		wButton.setActionCommand("w");
		mButton.setActionCommand("m");
		yButton.setActionCommand("y");
		// dButton.set
		repeater.add(nButton);
		repeater.add(dButton);
		repeater.add(wButton);
		repeater.add(mButton);
		repeater.add(yButton);
		nButton.setSelected(true);
		inner.add(nButton);
		inner.add(dButton);
		inner.add(wButton);
		inner.add(mButton);
		inner.add(yButton);
		return inner;
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
		// "ddMMMyyyyhmm""
		String result = (String) dCom.getSelectedItem() + (String) mCom.getSelectedItem()
				+ (String) yCom.getSelectedItem() + (String) hourCom.getSelectedItem() + ":"
				+ (String) minCom.getSelectedItem();
		return result;
	}

	public void setCombo(Event event) {
		// System.out.println(date.getDate()+" "+date.getMonth()+"
		// "+(date.getYear()+1900)+" "+date.getHours()+" "+date.getMinutes());
		Date date = event.getDate();
		dCom.setSelectedItem(date.getDate());
		mCom.setSelectedItem(date.getMonth());
		yCom.setSelectedItem(date.getYear() + 1900);
		hourCom.setSelectedItem(date.getHours());
		minCom.setSelectedItem(date.getMinutes());
		text.setText(event.getDetail());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInputText() {
		return text.getText();
	}

	public String getRepeater() {
		return repeater.getSelection().getActionCommand();
	}

	public JButton getSubButton() {
		return subButton;
	}

	public JButton getDelButton() {
		return delButton;
	}
}
