package util.validation;

import constant.ErrorMessages;
import constant.InfoMessages;
import org.apache.log4j.Logger;
import constant.Fields;
import constant.RegularExpression;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidation extends Validation {

    private static final Logger LOGGER = Logger.getLogger(LoginValidation.class);
    private Pattern pattern;
    private Matcher matcher;

    public void validateFieldsFromRequest(HttpServletRequest req) {
        validateLogin(req);
        validatePassword(req);
    }

    private void validateLogin(HttpServletRequest req) {
        String login = req.getParameter(Fields.USER_LOGIN);
        pattern = Pattern.compile(RegularExpression.LOGIN_REGEX);
        matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_LOGIN_FORMAT);
        }
    }
    private void validatePassword(HttpServletRequest req) {
        String password = req.getParameter(Fields.USER_PASSWORD);
        pattern = Pattern.compile(RegularExpression.PASSWORD_REGEX);
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_PASSWORD_FORMAT);
        }
    }

    public boolean isValid() {
        if (getErrorMessages().isEmpty()) {
            LOGGER.info(InfoMessages.INFO_CORRECT_LOGIN_FIELDS);
            return true;
        }
        LOGGER.info(InfoMessages.INFO_INCORRECT_LOGIN_FIELDS);
        return false;
    }

}
