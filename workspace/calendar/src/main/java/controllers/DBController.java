package controllers;
/**
 * Warit Siasakul  5810405339
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import models.Event;

public class DBController {
	public Connection loadDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:data.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				/*
				 * DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				 * System.out.println("Connected to the database");
				 * System.out.println("Driver name: " + dm.getDriverName());
				 * System.out.println("Driver version: " +
				 * dm.getDriverVersion()); System.out.println("Product name: " +
				 * dm.getDatabaseProductName());
				 * System.out.println("Product version: " +
				 * dm.getDatabaseProductVersion());
				 */
				return conn;
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void insertDB(Connection conn ,Event event) {
		try {
			if (conn != null) {
				String query = "INSERT INTO EVENT (id, date, detail, repeater) VALUES(?,?,?,?)";
				PreparedStatement statement = conn.prepareStatement(query);
		        statement.setInt(1,event.getId());
		        statement.setString(2,event.getDateFormat());
		        statement.setString(3,event.getDetail());
		        statement.setString(4,event.getRepeater());
		        statement.executeUpdate();
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void delDB(Connection conn ,Event event) {
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
	
	public void getDB(Connection conn ,ArrayList<Event> list) throws ParseException {
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
}
