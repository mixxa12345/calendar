package controllers;
/**
 * Warit Siasakul  5810405339
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import models.Event;
import views.DetailView;
import views.EventPanel;

public class EditController {

	private ArrayList<Event> list;
	private DetailView dView;
	private DBController DBC;

	public EditController(DetailView dView, ArrayList<Event> list) {
		this.dView = dView;
		this.list = list;
	}

	public void acceptDelete(int id) {	
		for (Event event : list) {
			if (event.getId() == id) {
				DBC.delDB(DBC.loadDB(), event);
				list.remove(event);
				break;
			}
		}
	}

	public void acceptModify(int id) {
		dView.setCombo(list.get(id));
		dView.setId(id);
		dView.setVisible(true);
	}

	public void setDBC(DBController DBC) {
		this.DBC = DBC;
	}

}
