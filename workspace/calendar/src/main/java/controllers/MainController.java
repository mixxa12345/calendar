package controllers;
import java.sql.SQLException;
/**
 * Warit Siasakul  5810405339
 */
import java.text.ParseException;

import views.MainView;

public class MainController {
	public void startApplication(){
		MainView frame = new MainView();
		DBController DBC = new DBController();

		
		ListenerManager listener = new ListenerManager(frame, DBC);
		listener.initListener();
		
		//load from DB
		try {
			try {
				DBC.createDB(DBC.loadDB());
			} catch(SQLException e) {
				System.out.println("sth. fail");
			}
			DBC.getDB(DBC.loadDB(), frame.getMenu().getCalendar());
			frame.getMenu().calendarToPanel();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//init main frame
		frame.initFrame();
	}

}
