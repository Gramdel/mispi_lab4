package other;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Point {
    private final double x;
    private final double y;
    private final double r;
    private final boolean isInArea;
    private final Date date;

    public Point(double x, double y, double r, boolean isInArea, Date date) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInArea = isInArea;
        this.date = date;
    }

    @Override
    public String toString() {
        return "<tr><td>" + x + "</td><td>" + y + "</td><td>" + r + "</td><td>" + (isInArea ? "Да" : "Нет") + "</td><td>" + (new SimpleDateFormat("dd.MM.yy HH:mm:ss")).format(date) + "</td></tr>";
    }
}
