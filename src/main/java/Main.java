import lombok.extern.log4j.Log4j;
import service.FIleProcessorService;
import service.FileProcessorServiceImpl;

import java.util.Arrays;

import static config.Constants.ENTER_VALID_ARGUMENT_MESSAGE;
import static config.Constants.RUN_WITH_WRONG_ARGUMENTS;

@Log4j
public class Main {

  public static void main(final String[] args) {
    final FIleProcessorService service;
    if (args.length == 1) {
      service = new FileProcessorServiceImpl();
      service.process(args[0]);
    } else {
      log.debug(RUN_WITH_WRONG_ARGUMENTS + Arrays.toString(args));
      System.out.println(ENTER_VALID_ARGUMENT_MESSAGE);
    }
  }
}
