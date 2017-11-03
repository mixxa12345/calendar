/**
 * Warit Siasakul  5810405339
 */
package server.controllers;
import java.text.ParseException;
import java.util.ArrayList;

import common.models.Event;

public class EventController {

	private ArrayList<Event> events = new ArrayList<>();
	private CalendarSource database;

	public void acceptDelete(int id) {
		for (Event event : events) {
			if (event.getId() == id) {
				System.out.println("DELETE:" + event.toString());
				database.deleteToDB(event);
				events.remove(event);
				break;
			}
		}
	}

	public void acceptInsert(Event event) throws ParseException {
		System.out.println("INSERT:" + event.toString());
		database.insertToDB(event);
	}

	public void setDatabase(SQLiteManager database) {
		this.database = database;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

}
