package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@SessionScoped
public class PointBean implements Serializable {
    private double x;
    private double y;
    private double r;
    private boolean isInArea;
    private Date date;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isInArea() {
        return isInArea;
    }

    public Date getDate() {
        return date;
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

    public void setInArea(boolean inArea) {
        isInArea = inArea;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
