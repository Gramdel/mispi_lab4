package other;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "web_lab3_results")
public class Point {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "r")
    private double r;

    @Column(name = "is_in_area")
    private boolean isInArea;

    @Column(name = "date")
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setInArea(boolean inArea) {
        isInArea = inArea;
    }

    public boolean isInArea() {
        return isInArea;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "<tr><td>" + x + "</td><td>" + y + "</td><td>" + r + "</td><td>" + (isInArea ? "Да" : "Нет") + "</td><td>" + (new SimpleDateFormat("dd.MM.yy HH:mm:ss")).format(date) + "</td></tr>";
    }
}
