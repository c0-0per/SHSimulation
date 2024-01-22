package reports;

import API.EventAPI;
import objects.Device;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EventReport implements Report { // ??????????????????????????
    List<Device> devices;
    String folderForReports;

    public EventReport(List<Device> devices, String folderForReports) {
        this.devices = devices;
        this.folderForReports = folderForReports;
    }

    @Override
    public void generateReport() {
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\reports\\" + folderForReports + "\\" + "eventReport.txt");
            for (Device device : devices) {
                myWriter.write("---Source device: " + device + "\n\n");
                EventAPI eventAPI = device.getEventAPI();
                if (eventAPI.getEventReportStructs().isEmpty()) {
                    continue;
                } else {
                    for (EventReportStruct eventReportStruct : eventAPI.getEventReportStructs()) {
                        myWriter.write("Type of event: " + eventReportStruct.getEventType().getEventType().toString() + "\n");
                        myWriter.write("Source: " + eventReportStruct.getSourceSensor().getClass().getName() + "\n");
                        myWriter.write("Listeners: ");
                        for (int i = 0; i < eventReportStruct.getListeners().size(); i++) {
                            if (i < eventReportStruct.getListeners().size() - 1) {
                                myWriter.write(eventReportStruct.getListeners().get(i).getClass().getName() + ", ");
                            } else myWriter.write(eventReportStruct.getListeners().get(i).getClass().getName());
                        }
                        myWriter.write("\n\n");
                    }
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
