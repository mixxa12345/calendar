package common;

import common.models.Event;


public interface ActionComponent {

    void requestInsert(Event event);

    void requestDelete(int id);

    //---get event from database in server
    int size();
    Event iterateEvent(int target);

}
