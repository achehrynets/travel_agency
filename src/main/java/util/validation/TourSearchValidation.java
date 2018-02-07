package util.validation;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TourSearchValidation extends Validation {

    private static final Logger LOGGER = Logger.getLogger(TourSearchValidation.class);

    public TourSearchValidation() {

    }

    public boolean isValid() {
        return super.isValid();
    }


    public List<String> getErrorMessages() {
        return super.getErrorMessages();
    }


    public void validateFieldsFromRequest(HttpServletRequest req) {

    }

}
