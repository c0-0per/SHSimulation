package API;


import reports.EventReportStruct;

import java.util.ArrayList;
import java.util.List;

public class EventAPI {


    private final List<EventReportStruct> eventReportStructs = new ArrayList<>();

    public void addNewEventReportStruct(EventReportStruct eventReportStruct) {
        eventReportStructs.add(eventReportStruct);
    }

    public List<EventReportStruct> getEventReportStructs() {
        return eventReportStructs;
    }
}
