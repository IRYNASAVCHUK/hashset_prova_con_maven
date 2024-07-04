package core.logger.formatter;

import java.util.*;
import java.util.logging.LogRecord;

import core.utils.Records;


public class LogInfo {
    public static Records.LogInfoInterface create(String event, Object target, List<Object[]> args, Object[] result,
            String name, Class<?> returnType, Records.EnterExitInterface myRecord) {
        return (myRecord instanceof Records.Exit && !returnType.equals(void.class))
                ? new Records.LogInfoWithResult(event, target, args, result, name)
                : new Records.LogInfoWithOutResult(event, target, args, name);
    }

    public static Records.LogInfoInterface create(LogRecord record) {
        var params = record.getParameters();
        if (params == null || params.length == 0)
            throw new IllegalArgumentException("No parameters found in the record.");
        var event = record.getMessage();
        var className = record.getSourceClassName();
        var name = className + "." + record.getSourceMethodName();
        var myRecord = (Records.EnterExitInterface) params[0];
        var target = (getTargetObject(myRecord) == null) ? className : myRecord.thisObject();
        var args = createArgsPair(myRecord);
        Class<?> returnType = null;
        Object[] result = null;
        if (myRecord instanceof Records.Exit) {
            returnType = ((Records.Exit<?>) myRecord).returnType();
            result = new Object[] { returnType, ((Records.Exit<?>) myRecord).result() };
        }

        return LogInfo.create(event, target, args, result, name, returnType, myRecord);
    }

    private static Object getTargetObject(Records.EnterExitInterface myRecord) {
        return (myRecord instanceof Records.Exit) ? ((Records.Exit<?>) myRecord).thisObject()
                : ((Records.Enter) myRecord).thisObject();
    }

    private static List<Object[]> createArgsPair(Records.EnterExitInterface myRecord) {
        Object[] argObjects = myRecord.params();
        Class<?>[] argsType = myRecord.paramsType();
        List<Object[]> pair = new ArrayList<>();
        if (argObjects == null || argObjects.length == 0)
            return pair;
        if (argObjects.length != argsType.length)
            throw new IllegalArgumentException("Mismatched array lengths.");
        for (int i = 0; i < argObjects.length; i++)
            pair.add(new Object[] { argsType[i], argObjects[i] });
        return pair;
    }
}
