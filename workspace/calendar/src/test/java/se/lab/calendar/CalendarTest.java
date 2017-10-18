package se.lab.calendar;
/**
 * Warit Siasakul  5810405339
 */
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import controllers.DBController;
import junit.framework.*;
import models.Event;

public class CalendarTest extends TestCase {
	
	private Event event1, event2, event3, event4;
	private DBController DBC = new DBController();
	private ArrayList<Event> events = new ArrayList<Event>();

	public void setUp() throws ParseException{
		event1 = new Event("20Aug201716:0" , "movie");
		event2 = new Event("22Aug201716:30" , "lab");
		event3 = new Event("1Jan201722:0" , "homework");
		event4 = new Event("30Dec20177:0" , "class");
		
		event1.setId(0);
		event2.setId(1);
		event3.setId(2);
		event4.setId(3);
		
		event1.setRepeater("-");
		event2.setRepeater("d");
		event3.setRepeater("m");
		event4.setRepeater("y");

		events.add(event1);
		events.add(event2);
		events.add(event3);
		events.add(event4);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSample() {
		assertEquals("movie", event1.getDetail());
		assertEquals("lab", event2.getDetail());
		assertEquals("homework", event3.getDetail());
		assertEquals("class", event4.getDetail());
		assertEquals("class", event4.getDetail());
		
		assertEquals(20, event1.getDate().getDate());
		assertEquals(22, event2.getDate().getDate());
		assertEquals(1, event3.getDate().getDate());
		assertEquals(30, event4.getDate().getDate());
		
		assertEquals(16, event1.getDate().getHours());
		assertEquals(16, event2.getDate().getHours());
		assertEquals(22, event3.getDate().getHours());
		assertEquals(7, event4.getDate().getHours());
		
		assertEquals("20Aug201716:0", event1.getDateFormat());
		assertEquals("22Aug201716:30", event2.getDateFormat());
		assertEquals("1Jan201722:0", event3.getDateFormat());
		assertEquals("30Dec20177:0", event4.getDateFormat());
		
		assertEquals("-", event1.getRepeater());
		assertEquals("d", event2.getRepeater());
		assertEquals("m", event3.getRepeater());
		assertEquals("y", event4.getRepeater());
		
		assertTrue(0 == event1.getId());
		assertTrue(1 == event2.getId());
		assertTrue(2 == event3.getId());
		assertTrue(3 == event4.getId());
		
		assertFalse(1 == event1.getDate().getMonth());
		assertFalse(10 == event2.getDate().getMonth());
		assertFalse(2 == event3.getDate().getMonth());
		assertFalse(7 == event4.getDate().getMonth());
		
	}
	
	@Test
	public void testDBConnection() throws ParseException {
		
		DBC.insertDB(DBC.loadDB(), event1);
		DBC.insertDB(DBC.loadDB(), event2);
		DBC.insertDB(DBC.loadDB(), event3);
		DBC.insertDB(DBC.loadDB(), event4);
		
		ArrayList<Event> testList1 = new ArrayList<Event>();
		DBC.getDB(DBC.loadDB(), testList1);
		assertEquals(events.size(), testList1.size());
		assertEquals(events.get(0).toString(), testList1.get(0).toString());
		assertEquals(events.get(1).toString(), testList1.get(1).toString());
		assertEquals(events.get(2).toString(), testList1.get(2).toString());
		assertEquals(events.get(3).toString(), testList1.get(3).toString());
		
		DBC.delDB(DBC.loadDB(), event1);
		DBC.delDB(DBC.loadDB(), event2);
		DBC.delDB(DBC.loadDB(), event3);
		DBC.delDB(DBC.loadDB(), event4);
		
		ArrayList<Event> testList2 = new ArrayList<Event>();
		DBC.getDB(DBC.loadDB(), testList2);
		assertTrue(0 == testList2.size());
		
	}
}
