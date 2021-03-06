/**
 * Warit Siasakul  5810405339
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import models.Event;
import views.DetailView;
import views.MainView;

public class ListenerManager {
	private MainView frame;
	private DBController DBC; //act insert, delete

	public ListenerManager(MainView frame, DBController DBC) {
		this.frame = frame;
		this.DBC = DBC;
	}

	public void initListener() {
		frame.getMenu().setDBOnEditor(DBC);
		frame.getMenu().getCreateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getMenu().getDView().setVisible(true);
				frame.getMenu().getDView().pack();
			}
		});
		
		frame.getMenu().getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getMenu().getSchView().setVisible(true);
				frame.getMenu().getSchView().pack();
			}
		});
		
		//add
		frame.getMenu().getDView().getSubButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (frame.getMenu().getDView().getId() > -1) {
						int id = frame.getMenu().getDView().getId();
						Event event = frame.getMenu().removeEvent(id);
						DBC.delDB(event);
						frame.getMenu().getDView().setId(-1);
					}
					Event ev = frame.getMenu().addEventPanel();
					DBC.insertDB(ev);
				} catch (ParseException e) {
					//e.printStackTrace();
				}
				frame.getMenu().getEditor().refresh();
				frame.pack();
				frame.getMenu().getDView().setVisible(false);
			}
		});		
		
		frame.getMenu().getDView().getDelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getMenu().getDView().setId(-1);
				frame.getMenu().getDView().setVisible(false);
			}
		});
	}

}
