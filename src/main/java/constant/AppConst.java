package constant;

import db.DBName;

public class AppConst {

    public static final DBName USED_DB_NAME = DBName.POSTGRESQL;

    public static final String LOG4J_PROPERTIES_PATH = "WEB-INF/log4j.properties";
    public static final String MAIL_SENDER_PROPERTIES_PATH = "WEB-INF/mailSender.properties";

    public static final String ENCODING_INIT_PARAM_FOR_FILTER = "encoding";

    public static final String FORWARD = "forward";
    public static final String REDIRECT = "redirect";
    public static final String PRG_HELPER = "/redirect";

    public static final String LINE_SEPARATOR = System.lineSeparator();

}
