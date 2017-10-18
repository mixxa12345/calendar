/**
 * Warit Siasakul  5810405339
 */
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
		DBC.loadDBtoMainView(frame);

		//init main frame
		frame.initFrame();
	}

}
