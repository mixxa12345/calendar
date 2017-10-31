package server;

import common.ActionComponent;
import controllers.DBController;
import controllers.EditController;
import models.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import views.MainView;
import views.MenuPanel;

import java.text.ParseException;

public class ActionComponentImpl implements ActionComponent {

    private EditController editor;
    private DBController DBC;

    public ActionComponentImpl(){
        ApplicationContext bf =
                new ClassPathXmlApplicationContext("controller.xml");
        DBC = (DBController) bf.getBean("dbc");
        editor = (EditController) bf.getBean("editor");
        editor.setDBC(DBC);
        try {
            DBC.getDB(editor.getEvents());
        } catch (ParseException e) {
            System.out.println("ERROR IN AC +++++++++++++++++++*");
            e.printStackTrace();
        }
    }

    @Override
    public void loadData(MenuPanel menu) {
        DBC.loadDBtoMainView(menu);
    }

    @Override
    public void requestInsert(MenuPanel parent, Event event) {
        editor.acceptInsert(parent, event);
    }

    @Override
    public void requestDelete(MenuPanel parent,int id) {
        editor.acceptDelete(parent, id);
    }

    @Override
    public void requestModify(MenuPanel parent,int id) {
        editor.acceptModify(parent, id);
    }

    @Override
    public String out(String text) {
        return text+text;
    }
}
