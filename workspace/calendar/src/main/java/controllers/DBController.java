/**
 * Warit Siasakul  5810405339
 */
package controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import models.Event;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import views.MainView;
import views.MenuPanel;

import javax.sql.DataSource;

public class DBController {

	public Connection loadDB() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");
			return conn;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void createDB() throws SQLException {
		Connection conn = reconnect();
		String query = "CREATE TABLE 'event' ( `id` INTEGER, `date` TEXT, `detail` TEXT, `repeater` TEXT )";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.executeUpdate();
		System.out.println("createDB");
	}

	public void insertDB(Event event) {
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

	public void delDB(Event event) {
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

	public void getDB(ArrayList<Event> list) throws ParseException {
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
					list.add(ev);
				}
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void loadDBtoMainView(MenuPanel menu){
		try {
			this.createDB();
		} catch(SQLException e) {
			//not found DB or something ,but don't affect program work
		} finally {
			try {
				this.getDB(menu.getCalendar());
			} catch (ParseException e) {
				//normally not go to this
				e.printStackTrace();
			}
			menu.setDBOnEditor(this);
			menu.calendarToPanel();
		}
	}


	private Connection reconnect(){
		return loadDB();
	}

}
