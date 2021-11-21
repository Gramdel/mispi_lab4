package beans;

import other.Point;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean
@ApplicationScoped
public class ResultBean {
    private double x;
    private double y;
    @ManagedProperty(value = "#{checkboxBean.options}")
    private boolean[] options = new boolean[5];
    private final ArrayList<Point> history = new ArrayList<>();

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

    public ArrayList<Point> getHistory() {
        return history;
    }

    public void add() {
        for (double i = 0; i < 5; i++) {
            if (options[(int) i]) {
                history.add(new Point(x, y, i/2+1, check(), new Date()));
                break;
            }
        }
    }

    private boolean check() {
        return true;
    }

    public String displayError() {
        return history.isEmpty() ? "<div class='error'>История запросов пуста, поэтому таблица не загружена.</div>" : "";
    }

    public String displayTableStart() {
        return !history.isEmpty() ? "<table class='history'><thead><tr><th>Значение X</th><th>Значение Y</th><th>Значение R</th><th>Попадание</th><th>Дата и время</th></tr></thead><tbody>" : "";
    }

    public String displayTableEnd() {
        int c = 0;
        for (Point pb : history) {
            System.out.println(c+":");
            System.out.println(pb);
            c++;
        }
        return !history.isEmpty() ? "</tbody></table>" : "";
    }
}
