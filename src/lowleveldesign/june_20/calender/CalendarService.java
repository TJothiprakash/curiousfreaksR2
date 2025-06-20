package lowleveldesign.june_20.calender;

import java.time.LocalDateTime;
import java.util.*;

public class CalendarService {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<Event>> userEvents = new HashMap<>();

    public void addUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        userEvents.put(userId, new ArrayList<>());
    }

    public void createEvent(String userId, String title, String desc,
                            LocalDateTime start, LocalDateTime end) {
        if (!users.containsKey(userId)) {
            System.out.println("User does not exist.");
            return;
        }
        Event event = new Event(UUID.randomUUID().toString(), userId, title, desc, start, end);
        userEvents.get(userId).add(event);
        System.out.println("Event created: " + event);
    }

    public void viewEvents(String userId) {
        if (!users.containsKey(userId)) {
            System.out.println("User not found.");
            return;
        }
        List<Event> events = userEvents.get(userId);
        events.sort(Comparator.comparing(Event::getStartTime));
        for (Event e : events) {
            System.out.println(e);
        }
    }

    public void deleteEvent(String userId, String eventId) {
        List<Event> events = userEvents.get(userId);
        events.removeIf(e -> e.getId().equals(eventId));
        System.out.println("Event deleted (if existed).");
    }
}
