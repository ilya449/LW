package exception;

import static config.Constants.FIELD_PARSING_EXCEPTION;

public class FieldParsingException extends MyCustomException {
  public FieldParsingException(final String message) {
    super(String.format(FIELD_PARSING_EXCEPTION, message));
    super.logError(super.getMessage());
  }
}
