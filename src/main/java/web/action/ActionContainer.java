package web.action;

import constant.InfoMessages;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ActionContainer {

    private static final Logger LOGGER = Logger.getLogger(ActionContainer.class);

    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("login", new LoginAction());
        actions.put("logout", null);
        actions.put("registrationPage", new ViewRegistrationPageAction());
        actions.put("registration", new RegistrationAction());
        actions.put("noActions", null);
    }

    public static Action getWebAction(String actionName) {
        if (actionName == null || !actions.containsKey(actionName)) {
            LOGGER.trace(InfoMessages.INFO_ACTION_NOT_FOUND);
            return actions.get("noAction");
        }
        return actions.get(actionName);
    }

}
