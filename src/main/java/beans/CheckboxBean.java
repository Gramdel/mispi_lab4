package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class CheckboxBean implements Serializable {
    private boolean[] options = new boolean[5];

    public CheckboxBean() {
        options[0] = true;
        for (int i = 1; i < 5; i++) {
            options[i] = false;
        }
    }

    public boolean[] getOptions() {
        return options;
    }

    public void setOptions(boolean[] options) {
        this.options = options;
    }
}