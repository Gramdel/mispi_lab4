package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;

@ManagedBean
@ApplicationScoped
public class CheckboxBean implements Serializable {
    private boolean[] options = new boolean[5];

    public CheckboxBean() {
        for (int i = 0; i < 4; i++) {
            options[i] = false;
        }
        options[4] = true;
    }

    public boolean[] getOptions() {
        return options;
    }

    public void setOptions(boolean[] options) {
        this.options = options;
    }
}