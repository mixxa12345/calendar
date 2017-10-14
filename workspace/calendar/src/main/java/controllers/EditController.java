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
import views.MenuPanel;

public class EditController {

	private ArrayList<Event> list;
	private DetailView dView;
	private DBController DBC;
	private MenuPanel menu;

	public EditController(DetailView dView, ArrayList<Event> list, MenuPanel menu) {
		this.dView = dView;
		this.list = list;
		this.menu = menu;
	}

	public void acceptDelete(int id) {
		for (Event event : list) {
			if (event.getId() == id) {
				DBC.delDB(DBC.loadDB(), event);
				list.remove(event);
				refresh();
				break;
			}
		}
	}

	public void acceptModify(int id) {
		Event target = null;
		for (Event ev : list) {
			if (ev.getId() == id) {
				target = ev;
			}
		}
		dView.setCombo(target);
		dView.setId(id);
		dView.setVisible(true);
		refresh();
	}

	public void setDBC(DBController DBC) {
		this.DBC = DBC;
	}

	public void refresh() {
		//force update to all view
		//1
		menu.getEventFlow().removeAll();
		menu.calendarToPanel();
		//2
		menu.getExView().stateAction();
		//3
		if (menu.getSchView().isVisible()) {
			menu.getSchView().changeSerach();
		}
		
	}

	public static ArrayList<Event> sortArrayList(ArrayList<Event> list) {
		ArrayList<Event> newList = list;
		Collections.sort(newList, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				Date oo1 = o1.getDate();
				Date oo2 = o2.getDate();
				if (oo1.getTime() < oo2.getTime())
					return -1;
				else if (oo1.getTime() == oo2.getTime())
					return 0;
				else
					return 1;
			}
		});
		return newList;
	}

}
