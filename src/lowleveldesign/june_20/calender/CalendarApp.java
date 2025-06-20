package lowleveldesign.june_20.calender;

import java.time.LocalDateTime;

public class CalendarApp {
    public static void main(String[] args) {
        CalendarService service = new CalendarService();

        // Add users
        service.addUser("u1", "Jothi");
        service.addUser("u2", "Mariam");

        // Create events
        service.createEvent("u1", "Meeting", "Project discussion",
                LocalDateTime.of(2025, 6, 21, 10, 0),
                LocalDateTime.of(2025, 6, 21, 11, 0));

        service.createEvent("u1", "Doctor Visit", "Routine checkup",
                LocalDateTime.of(2025, 6, 22, 9, 30),
                LocalDateTime.of(2025, 6, 22, 10, 0));

        // View user events
        System.out.println("\nEvents for u1:");
        service.viewEvents("u1");

        // Delete an event (pass event ID you got earlier)
        // service.deleteEvent("u1", "<event_id_here>");
    }
}
