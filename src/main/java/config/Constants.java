package config;

public class Constants {
  public static final String LOG_FILE = "logs/LW_log_from_exception.log";
  public static final String BLANK_FIELD_ERROR_MESSAGE = "Field %s should not be blank!";
  public static final String FIELD_PARSING_EXCEPTION = "Field %s can not be parsed!";
  public static final String FILE_WRITING_ERROR_MESSAGE =
      "Error while writing to file. Error message: ";
  public static final String CONVERT_TO_JSON_ERROR_MESSAGE =
      "Error while converting to JSON with object: ";
  public static final String DIGITS_REGEX = "\\d+";
  public static final String DOMAIN_REGEX =
      "^((?!-)[A-Za-z0-9-]" + "{1,63}(?<!-)\\.)" + "+[A-Za-z]{2,6}";
  public static final String WRONG_FILE_HEADER = "File have wrong header, can not parse!";
  public static final String NAME = "name";
  public static final String ADDRESS = "address";
  public static final String EMPLOYEES_COUNT = "employeesCount";
  public static final String CREATION_DATE = "creationDate";
  public static final String MFO = "mfo";
  public static final String SITE = "site";
  public static final String COMMERCIALLY = "commercially";
  public static final String EMPTY_FILE_ERROR_MESSAGE = "File should not be empty!";
  public static final int MFO_LENGTH = 6;
  public static final int PROPERTIES_COUNT = 7;
  public static final String DOT = ".";
  public static final String JSON = ".json";
  public static final String NEW_LINE = "\n";
  public static final String COMMA = ",";
  public static final String EMPTY_LINE = "";
  public static final String SPACE = " ";
  public static final String CAN_NOT_PARSE_LINE = "Can not parse line ";
  public static final String DOUBLE_DOTS = ": ";
  public static final String LOGGING_ERROR_MESSAGE = "Exception while logging: ";
  public static final String RUN_WITH_WRONG_ARGUMENTS = "Trying to run with wrong args: ";
  public static final String ENTER_VALID_ARGUMENT_MESSAGE =
      "Please enter valid argument (filename) and try again!";
}
