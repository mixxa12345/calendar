/**
 * Warit Siasakul  5810405339
 */
package client;

import common.ActionComponent;
import common.models.Event;
import client.views.MainView;


public class MainController {

	MainView frame = new MainView();

	public void startApplication(){
		//load from DB << 1 time Load
		frame.initFrame();
		frame.getMenu().initMenu();

	}

	public void setAC(ActionComponent AC) {
		frame.getMenu().setActionComponent(AC);
	}
}
