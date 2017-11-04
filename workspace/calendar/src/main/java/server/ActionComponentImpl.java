package server;

import common.ActionComponent;
import server.controllers.SQLiteManager;
import server.controllers.EventController;
import common.models.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;

import java.text.ParseException;
import java.util.ArrayList;


public class ActionComponentImpl implements ActionComponent {
    private EventController editor;
    private SQLiteManager database;
    private ArrayList<Event> tempEvents;

    public ActionComponentImpl() throws RemoteException {
        ApplicationContext bf =
               new ClassPathXmlApplicationContext("controller.xml");
        database = (SQLiteManager) bf.getBean("dbc");
        editor = (EventController) bf.getBean("editor");

        editor.setDatabase(database);
        System.out.println("Server Side DataBase Loading..");
        database.getEventsFromDB(editor.getEvents());
    }


    @Override
    public void requestInsert(Event event) {
        synchronized (database) {
            try {
                editor.acceptInsert(event);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            database.getEventsFromDB(editor.getEvents());
        }

    }

    @Override
    public void requestDelete(int id) {
        synchronized (database) {
            editor.acceptDelete(id);
            database.getEventsFromDB(editor.getEvents());
        }

    }


    //-------
    @Override
    public int size(){
        tempEvents = editor.getEvents();
        return tempEvents.size();
    }

    @Override
    public Event iterateEvent(int target){
        return tempEvents.get(target);
    }

}
