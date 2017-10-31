/**
 * Warit Siasakul  5810405339
 */
package controllers;

import common.ActionComponent;
import views.MainView;


public class MainController {

	MainView frame = new MainView();
	DBController DBC = new DBController(); // << will delete

	public void startApplication(){
		//load from DB
		DBC.loadDBtoMainView(frame.initFrame().getMenu()); // << 1 time Load
		           			                                // may move to AC
	}

	public void setAC(ActionComponent AC) {
		frame.getMenu().setActionComponent(AC);
	}
}
