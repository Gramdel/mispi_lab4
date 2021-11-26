package jobs;

import db.DBUnit;
import listeners.SessionListener;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import other.Point;

import java.util.List;

public class SessionKillJob implements Job {
    private final DBUnit db = new DBUnit();

    public void execute(JobExecutionContext arg0) {
        System.out.println("Job is executed");
        List<Point> results = db.getResults();
        System.out.println("List of current sessions: " + (SessionListener.sessions.isEmpty() ? "empty" : ""));
        for (String s : SessionListener.sessions) {
            System.out.println("\t" + s);
        }
        for (Point point : results) {
            if (!SessionListener.sessions.contains(point.getSessionId())) {
                db.removeResult(point);
            }
        }
    }
}
