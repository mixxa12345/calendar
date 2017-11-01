package common;

import common.models.Event;


public interface ActionComponent {

    void requestInsert(String id, String detail, String re, String date);

    void requestDelete(int id);

    //---experiment
    int size();
    Event iterateEvent(int target);


    String out(String text);
}
