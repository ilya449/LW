package exception;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static config.Constants.DOUBLE_DOTS;
import static config.Constants.LOGGING_ERROR_MESSAGE;
import static config.Constants.LOG_FILE;

@Getter
@Log4j
public class MyCustomException extends RuntimeException {
  private static final String logFileName = LOG_FILE;
  private static final Logger logger = Logger.getLogger(MyCustomException.class);
  private Object[] args;

  public MyCustomException(final String message) {
    super(message);
  }

  public MyCustomException(final String message, final Object... args) {
    super(message);
    this.args = args;
  }

  public void logError(final String message) {
    final SimpleLayout layout = new SimpleLayout();
    final FileAppender appender;
    try {
      appender = new FileAppender(layout, logFileName, true);
      logger.setAdditivity(true);
      logger.addAppender(appender);
      logger.setLevel(Level.DEBUG);
      logger.error(
          LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
              + DOUBLE_DOTS
              + message);
    } catch (final IOException e) {
      log.error(LOGGING_ERROR_MESSAGE + e.getMessage());
    }
  }
}
