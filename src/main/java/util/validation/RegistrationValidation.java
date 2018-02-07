package util.validation;

import constant.ErrorMessages;
import constant.Fields;
import constant.InfoMessages;
import constant.RegularExpression;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidation extends Validation {

    private static final Logger LOGGER = Logger.getLogger(RegistrationValidation.class);
    private Pattern pattern;
    private Matcher matcher;

    public void validateFieldsFromRequest(HttpServletRequest req) {
        validateLogin(req);
        validatePassword(req);
        validateEmail(req);
        validateName(req);
        validateSurname(req);
        validateMiddleName(req);
        validatePassportId(req);
        validateInternationalPassportId(req);
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

    private void validateEmail(HttpServletRequest req) {
        String email = req.getParameter(Fields.USER_EMAIL);
        pattern = Pattern.compile(RegularExpression.EMAIL_REGEX);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_EMAIL_FORMAT);
        }
    }

    private void validateName(HttpServletRequest req) {
        String name = req.getParameter(Fields.NAME);
        pattern = Pattern.compile(RegularExpression.NAME_REGEX);
        matcher = pattern.matcher(name);
        if(!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_NAME_FORMAT);
        }
    }

    private void validateSurname(HttpServletRequest req) {
        String surname = req.getParameter(Fields.NAME);
        pattern = Pattern.compile(RegularExpression.NAME_REGEX);
        matcher = pattern.matcher(surname);
        if(!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_SURNAME_FORMAT);
        }
    }

    private void validateMiddleName(HttpServletRequest req) {
        String middleName = req.getParameter(Fields.USER_MIDDLE_NAME);
        pattern = Pattern.compile(RegularExpression.NAME_REGEX);
        matcher = pattern.matcher(middleName);
        if(!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_MIDDLE_NAME_FORMAT);
        }
    }

    private void validatePassportId(HttpServletRequest req) {
        String passportId = req.getParameter(Fields.USER_PASSPORT_ID);
        pattern = Pattern.compile(RegularExpression.PASSPORT_REGEX);
        matcher = pattern.matcher(passportId);
        if(!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_PASSPORT_FORMAT);
        }
    }
    private void validateInternationalPassportId(HttpServletRequest req) {
        String passportId = req.getParameter(Fields.USER_INTERNATIONAL_PASSPORT_ID);
        pattern = Pattern.compile(RegularExpression.PASSPORT_REGEX);
        matcher = pattern.matcher(passportId);
        if(!matcher.matches()) {
            getErrorMessages().add(ErrorMessages.ERROR_INTERNATIONAL_PASSPORT_FORMAT);
        }
    }

    public boolean isValid() {
        if (getErrorMessages().isEmpty()) {
            LOGGER.info(InfoMessages.INFO_CORRECT_REGISTRATION_FIELDS);
            return true;
        }
        LOGGER.info(InfoMessages.INFO_INCORRECT_REGISTRATION_FIELDS);
        return false;
    }

}
