package views;
/**
 * Warit Siasakul  5810405339
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;


public class MainView extends JFrame {
	private int frameWidth = 340;
	private int frameHeight = 800;
	private MenuPanel menu;
	public MainView() {
		UIManager.put("Button.font", new Font("Arial Black", Font.PLAIN, 13));
/*		this.comp = comp;
		bar = new MenuBar();*/
		menu = new MenuPanel();
		
	}

	public void initFrame() {
		setTitle("Date Calendar");
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(menu, BorderLayout.NORTH);
		menu.setVisible(true);
/*		add(menu, BorderLayout.NORTH);*/

		pack();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	
	public MenuPanel getMenu() {
		return menu;
	}
	
}
