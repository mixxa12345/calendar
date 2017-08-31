package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	private Date date;
	private String detail;
	private int id;
	
	public Event(String s, String dt) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMMyyyyh:mm");
		detail = dt;
		date = format.parse(s);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public String getDetail() {
		return detail;
	}
	
	
}
