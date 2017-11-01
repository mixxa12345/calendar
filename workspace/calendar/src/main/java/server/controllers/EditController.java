/**
 * Warit Siasakul  5810405339
 */
package server.controllers;
import java.text.ParseException;
import java.util.ArrayList;

import common.models.Event;

public class EditController {

	private ArrayList<Event> events = new ArrayList<>();
	private DBController DBC;

	public void acceptDelete(int id) {
		for (Event event : events) {
			if (event.getId() == id) {
				System.out.println("DELETE:" + event.toString());
				DBC.delDB(event);
				events.remove(event);
				break;
			}
		}
	}

	public void acceptInsert(String id, String detail, String re, String date) throws ParseException {
		Event event = new Event(id, detail, re, date);
		System.out.println("INSERT:" + event.toString());
		DBC.insertDB(event);
	}

	public void setDBC(DBController DBC) {
		this.DBC = DBC;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

}
