package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import views.MainView;

public class ListenerManager {
	private MainView frame;

	public ListenerManager(MainView frame) {
		this.frame = frame;
	}

	public void initListener() {
		
		frame.getMenu().getCreateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getMenu().getDView().setVisible(true);
				frame.getMenu().getDView().pack();
			}
		});
		
		frame.getMenu().getDView().getSubButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.getMenu().addEventPanel();
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
