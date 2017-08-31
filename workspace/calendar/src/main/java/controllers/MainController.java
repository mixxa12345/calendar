package controllers;

import views.MainView;

public class MainController {
	public void startApplication() {
		MainView frame = new MainView();
		frame.initFrame();
		ListenerManager listener = new ListenerManager(frame);
		listener.initListener();
	}

}
