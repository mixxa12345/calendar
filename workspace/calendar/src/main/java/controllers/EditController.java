/**
 * Warit Siasakul  5810405339
 */
package controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import models.Event;
import views.DetailView;
import views.MenuPanel;

public class EditController {

	private ArrayList<Event> events = new ArrayList<>();
	private DBController DBC;

	public void acceptDelete(MenuPanel menu, int id) {
		for (Event event : events) {
			if (event.getId() == id) {
				DBC.delDB(event);
				events.remove(event);
				menu.refreshScene();
				break;
			}
		}
	}

	public void acceptModify(MenuPanel menu, int id) {
		Event target = null;
		for (Event ev : events) {
			if (ev.getId() == id) {
				target = ev;
			}
		}
		menu.getdView().setCombo(target);
		menu.getdView().setId(id);
		menu.getdView().setVisible(true);
		menu.refreshScene();
	}

	public void acceptInsert(MenuPanel menu, Event event) {
		DBC.insertDB(event);
		menu.refreshScene();
	}

	public void setDBC(DBController DBC) {
		this.DBC = DBC;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
}
