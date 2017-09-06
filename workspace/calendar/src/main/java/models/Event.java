package models;
/**
 * Warit Siasakul  5810405339
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	private Date date;
	private String detail;
	private int id;
	private String dateFormat;
	
	public Event(String s, String dt) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMMyyyyh:mm");
		dateFormat = s;
		detail = dt;
		date = format.parse(s);
	}
	
	public String toString() {
		return Integer.toString(id)+ " " + date.getDate()+" "+date.getMonth()+" "+(date.getYear()+1900)+" "+date.getHours()+" "+date.getMinutes();
		
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
	
	public String getDateFormat() {
		return dateFormat;
	}
}
