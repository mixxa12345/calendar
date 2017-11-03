/**
 * Warit Siasakul  5810405339
 */
package client;

import common.ActionComponent;
import common.models.Event;
import client.views.MainView;


public class MainController {

	MainView frame = new MainView();
	ActionComponent AC;

	public void startApplication(){
		//load from DB << 1 time Load
		frame.initFrame();
		frame.getMenu().initMenu();

		System.out.println("Client Side DataBase Loading...");
		int n = AC.size();
		System.out.println("Server Size :" + n);
		for (int i = 0; i < n; i++) {
			Event ev = AC.iterateEvent(i);
			frame.getMenu().getCalendar().add(ev);
			System.out.println(ev);
		}

		System.out.println("Client Size :" + frame.getMenu().getCalendar().size());

		frame.getMenu().refreshScene();
	}

	public void setAC(ActionComponent AC) {
		this.AC = AC;
		frame.getMenu().setActionComponent(AC);
	}
}
