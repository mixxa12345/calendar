package views;
/**
 * Warit Siasakul  5810405339
 */
import sun.applet.Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class MainView extends JFrame {
	private int frameWidth = 1000;
	private int frameHeight = 800;
	private MenuPanel menu;
	public MainView() {
		UIManager.put("Button.font", new Font("Arial Black", Font.PLAIN, 13));
/*		this.comp = comp;
		bar = new MenuBar();*/
		menu = new MenuPanel();
		
	}

	public MainView initFrame() {
		setTitle("Date Calendar");
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(menu, BorderLayout.NORTH);
		menu.setVisible(true);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		return this;
	}

	public static void displayEmpty(JPanel eventFlow) {
		eventFlow.removeAll();
		JTextField jf = new JTextField("No Event");
		jf.setFont(new Font("Arial Black", Font.BOLD, 14));
		jf.setHorizontalAlignment(JTextField.CENTER);
		jf.setEditable(false);
		eventFlow.add(jf);
	}


	public MenuPanel getMenu() {
		return menu;
	}
	
}
