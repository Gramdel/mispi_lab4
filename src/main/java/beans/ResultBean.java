package beans;

import db.DBUnit;
import other.Point;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class ResultBean {
    private double x;
    private double y;
    private double r;
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

    public double getR() {
        return r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setOptions(boolean[] options) {
        this.options = options;
    }

    public List<Point> getResults() {
        List<Point> results = new ArrayList<>();
        for (Point result : this.results) {
            if (result.getSessionId().equals(FacesContext.getCurrentInstance().getExternalContext().getSessionId(false))) {
                results.add(result);
            }
        }
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
                r = i / 2 + 1;
                Point point = new Point();
                point.setX(x);
                point.setY(y);
                point.setR(r);
                point.setInArea(check());
                point.setDate(new Date());
                point.setSessionId(FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));
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
        return pointIsInCircle() || pointIsInRectangle() || pointIsInTriangle();
    }

    public String displayError() {
        return getResults().isEmpty() ? "<div class='error'>История запросов пуста, поэтому таблица не загружена.</div>" : "";
    }

    public String displayTableStart() {
        return !getResults().isEmpty() ? "<table class='history'><thead><tr><th>Значение X</th><th>Значение Y</th><th>Значение R</th><th>Попадание</th><th>Дата и время</th></tr></thead><tbody>" : "";
    }

    public String displayTableEnd() {
        return !getResults().isEmpty() ? "</tbody></table>" : "";
    }

    public String displayScript() {
        StringBuilder s = new StringBuilder();
        s.append("<script>\n");
        s.append("function drawPointsFromHistory(){\n");
        s.append("let canvas = document.getElementById('canvas');\n");
        s.append("let context = canvas.getContext('2d');\n");
        s.append("context.fillStyle = \"#FF0000\";\n");
        getResults().forEach(result -> s.append("context.fillRect(\"").append(result.getX()*36+133-1.5).append("\", \"").append(result.getY()*-36+133-2).append("\", 2, 2);\n"));
        s.append("}\n");
        s.append("</script>\n");
        return s.toString();
    }

    private boolean pointIsInTriangle() {
        return (y <= r / 2 - x / 2) && (y >= 0) && (x >= 0);
    }

    private boolean pointIsInCircle() {
        return (x * x + y * y <= r * r / 4) && (y >= 0) && (x <= 0);
    }

    private boolean pointIsInRectangle() {
        return (y >= -r) && (y <= 0) && (x >= -r) && (x <= 0);
    }
}
