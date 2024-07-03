package logic.logger;

import java.util.logging.*;

import logic.utils.Constants;
import logic.utils.Records;
import logic.websocket.MyClient;

public class MyLogger extends Logger {

    protected MyLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
    }

    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    static {
        configureLogger();
    }

    private static void configureLogger() {
        logger.setLevel(Level.ALL);
        if (Constants.ONLINE)
            // SimpleHttpClient.configureHttpClient(logger);
            MyClient.configureWebSocketClient(logger);
        else
            MyFileHandler.configureHandler(logger);
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void logMethodEntry(String methodName, Object thisObject, Object[] params, Class<?>[] paramsType) {
        logger.logp(Level.ALL, thisObject.getClass().getName(), methodName, Constants.ENTRY,
                new Records.Enter(params, paramsType, thisObject));
    }

    public static <T> void logMethodExit(String methodName, Object thisObject, Class<T> returnType, T result,
            Object[] params, Class<?>[] paramsType) {
        logger.logp(Level.ALL, thisObject.getClass().getName(), methodName, Constants.RETURN,
                new Records.Exit<>(returnType, result, params, paramsType, thisObject));
    }

    public static void logStaticMethodEntry(String className, String methodName, Object[] params,
            Class<?>[] paramsType) {
        logger.logp(Level.ALL, className, methodName, Constants.ENTRY,
                new Records.Enter(params, paramsType, null));
    }

    public static <T> void logStaticMethodExit(String className, String methodName, Class<T> returnType, T result,
            Object[] params, Class<?>[] paramsType) {
        logger.logp(Level.ALL, className, methodName, Constants.RETURN,
                new Records.Exit<>(returnType, result, params, paramsType, null));
    }
}
