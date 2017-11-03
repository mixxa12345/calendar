package server.controllers;

import common.models.Event;

import java.util.ArrayList;

public interface CalendarSource {

    void insertToDB(Event event);

    void deleteToDB(Event event);

    void getEventsFromDB(ArrayList<Event> events);
}
