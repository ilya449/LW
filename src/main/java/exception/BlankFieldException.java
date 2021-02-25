package exception;

import static config.Constants.BLANK_FIELD_ERROR_MESSAGE;

public class BlankFieldException extends MyCustomException {
  public BlankFieldException(final String message) {
    super(String.format(BLANK_FIELD_ERROR_MESSAGE, message));
    super.logError(super.getMessage());
  }
}
