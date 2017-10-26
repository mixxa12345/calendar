/**
 * Warit Siasakul  5810405339
 */
package controllers;
import java.sql.SQLException;
/**
 * Warit Siasakul  5810405339
 */
import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import views.MainView;

public class MainController {

	MainView frame;
	DBController DBC;
	ListenerManager listener;

	public void startApplication(){
		ApplicationContext bf =
				new ClassPathXmlApplicationContext("controller.xml");
		DBC = (DBController) bf.getBean("dbc");
		frame = (MainView) bf.getBean("frame");
		listener = (ListenerManager) bf.getBean("listener");

		//init listener
		listener.initListener();
		//load from DB
		DBC.loadDBtoMainView(frame);
		//init main frame
		frame.initFrame();
	}


}
