/**
 * Warit Siasakul  5810405339
 */
package server.controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import common.models.Event;

public class SQLiteManager implements CalendarSource{

	public SQLiteManager() {
		try {
			this.createDB();
		} catch(SQLException e) {
			//if not found .db file ,then it will auto generate
		}
	}

	// inner method call -------------------------------------
	private Connection reconnect(){
		return loadDB();
	}
	private Connection loadDB() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");
			return conn;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	private void createDB() throws SQLException {
		Connection conn = reconnect();
		String query = "CREATE TABLE 'event' ( `id` INTEGER, `date` TEXT, `detail` TEXT, `repeater` TEXT )";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.executeUpdate();
		System.out.println("creating .db file .. .. .. ");
	}
	//-------------------------------------------------

	public void insertToDB(Event event) {
		Connection conn = reconnect();
		try {
			if (conn != null) {
				String query = "INSERT INTO EVENT (id, date, detail, repeater) VALUES(?,?,?,?)";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, event.getId());
				statement.setString(2, event.getDateFormat());
				statement.setString(3, event.getDetail());
				statement.setString(4, event.getRepeater());
				statement.executeUpdate();
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteToDB(Event event) {
		Connection conn = reconnect();
		try {
			if (conn != null) {
				String query = "DELETE FROM EVENT WHERE id = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, event.getId());
				statement.executeUpdate();
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void getEventsFromDB(ArrayList<Event> events){
		events.clear();
		//loading block --------------------
		Connection conn = reconnect();
		try {
			if (conn != null) {
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("Select * from event");
				while (resultSet.next()) {
					int id = resultSet.getInt(1);
					String date = resultSet.getString(2);
					String detail = resultSet.getString(3);
					String repeater = resultSet.getString(4);
					Event ev = new Event(date, detail);
					ev.setId(id);
					ev.setRepeater(repeater);
					System.out.println(id + date + detail + repeater);
					events.add(ev);
				}
				conn.close();
			}
		} catch (SQLException | ParseException ex) {
			ex.printStackTrace();
		}
		//-------------------------------------
	}


}