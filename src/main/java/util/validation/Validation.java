package util.validation;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class Validation {

    private List<String> errorMessages;

    public Validation() {
        this.errorMessages = new ArrayList<String>();
    }

    public boolean isValid() {
        return errorMessages.isEmpty();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public abstract void validateFieldsFromRequest(HttpServletRequest req);

}
