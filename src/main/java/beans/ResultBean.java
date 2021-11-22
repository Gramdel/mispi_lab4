package beans;

import db.DBUnit;
import other.Point;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class ResultBean {
    private double x;
    private double y;
    @ManagedProperty(value = "#{checkboxBean.options}")
    private boolean[] options = new boolean[5];
    private final DBUnit db = new DBUnit();
    private List<Point> results = new ArrayList<>();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOptions(boolean[] options) {
        this.options = options;
    }

    public List<Point> getResults() {
        return results;
    }

    @PostConstruct
    public void init() {
        try {
            List<Point> results = db.getResults();
            if (results != null) {
                this.results = results;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public void add() {
        for (double i = 0; i < 5; i++) {
            if (options[(int) i]) {
                Point point = new Point();
                point.setX(x);
                point.setY(y);
                point.setR(i / 2 + 1);
                point.setInArea(check());
                point.setDate(new Date());
                results.add(point);
                try {
                    db.sendResult(point);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private boolean check() {
        return true;
    }

    public String displayError() {
        return results.isEmpty() ? "<div class='error'>История запросов пуста, поэтому таблица не загружена.</div>" : "";
    }

    public String displayTableStart() {
        return !results.isEmpty() ? "<table class='history'><thead><tr><th>Значение X</th><th>Значение Y</th><th>Значение R</th><th>Попадание</th><th>Дата и время</th></tr></thead><tbody>" : "";
    }

    public String displayTableEnd() {
        return !results.isEmpty() ? "</tbody></table>" : "";
    }
}
