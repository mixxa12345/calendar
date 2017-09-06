package controllers;
/**
 * Warit Siasakul  5810405339
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import models.Event;
import views.DetailView;
import views.MainView;

public class ListenerManager {
	private MainView frame;
	private DBController DBC;

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
		
		//add
		frame.getMenu().getDView().getSubButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Event ev = frame.getMenu().addEventPanel();
					DBC.insertDB(DBC.loadDB(), ev);
					
				} catch (ParseException e) {
					//e.printStackTrace();
				}
				frame.pack();
				frame.getMenu().getDView().setVisible(false);
			}
		});		
		
		frame.getMenu().getDView().getDelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getMenu().getDView().setVisible(false);
			}
		});
	}
}
