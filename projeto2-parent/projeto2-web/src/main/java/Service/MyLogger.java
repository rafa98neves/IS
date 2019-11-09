package Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    final static Logger logger = LogManager.getLogger(MyLogger.class);

    public void doWork() {
        logger.info("MyService's doWork() called");
    }
}