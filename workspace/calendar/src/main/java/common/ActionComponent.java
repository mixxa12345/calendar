package common;

import models.Event;
import views.MainView;
import views.MenuPanel;

public interface ActionComponent {

    void loadData(MenuPanel menu);

    void requestInsert(MenuPanel parent,Event event);

    void requestDelete(MenuPanel parent,int id);

    void requestModify(MenuPanel parent,int id);

    String out(String text);
}
