package server;

import common.ActionComponent;
import server.controllers.DBController;
import server.controllers.EditController;
import common.models.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;

import java.text.ParseException;


public class ActionComponentImpl implements ActionComponent {
    private EditController editor;
    private DBController DBC;

    public ActionComponentImpl() throws RemoteException {
        ApplicationContext bf =
               new ClassPathXmlApplicationContext("controller.xml");
        DBC = (DBController) bf.getBean("dbc");
        editor = (EditController) bf.getBean("editor");

        editor.setDBC(DBC);
        System.out.println("Server Side DataBase Loading..");
        DBC.manageDB(editor.getEvents());
    }


    @Override
    public void requestInsert(String id, String detail, String re, String date) {
        try {
            editor.acceptInsert(id, detail, re, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DBC.manageDB(editor.getEvents());
    }

    @Override
    public void requestDelete(int id) {
        editor.acceptDelete(id);
        DBC.manageDB(editor.getEvents());
    }


    //-------experiment

    @Override
    public int size(){
        return editor.getEvents().size();
    }

    @Override
    public Event iterateEvent(int target){
        return editor.getEvents().get(target);
    }


    @Override
    public String out(String text) {
        return text+text;
    }
}
