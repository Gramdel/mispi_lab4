package listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

public class SessionListener implements HttpSessionListener {
    public static ArrayList<String> sessions = new ArrayList<>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Session created: " + event.getSession().getId());
        sessions.add(event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session destroyed: " + event.getSession().getId());
        sessions.remove(event.getSession().getId());
    }
}
