package com.example.logger;

import com.example.record.*;

import java.util.logging.*;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        String event = record.getMessage().contains("ENTRY") ? ConfigLoader.getConfigValue("eventEntry")
                : record.getMessage().contains("RETURN") ? ConfigLoader.getConfigValue("eventReturn") : "";

        String resultString = "{\n\t\t\"event\": \"" + event + "\",";

        Object[] params = record.getParameters();

        Object target = null;

        if (params == null || params.length == 0)
            throw new IllegalArgumentException("No parameters found in the record.");

        if (params[0] instanceof MyRecord) {
            MyRecord myRecord = (MyRecord) params[0];
            if (myRecord instanceof MyRecordExiting) {
                MyRecordExiting<?> exitingRecord = (MyRecordExiting<?>) myRecord;
                target = exitingRecord.thisObject();
            } else if (myRecord instanceof MyRecordEntering) {
                MyRecordEntering enteringRecord = (MyRecordEntering) myRecord;
                target = enteringRecord.thisObject();
            }
            // chiamate di hashCode() inaspetatti : addCustomer, removeCustomer,
            // containsCustomer,
            resultString += "\n\t\t\"target\": \"";
            resultString += (target == null) ? record.getSourceClassName() : System.identityHashCode(target);
            resultString += "\",";
            Object[] args = myRecord.params();
            if (args != null && args.length > 0) {
                resultString += "\n\t\t\"args\": [";
                for (Object arg : args) {
                    if (isPrimitiveOrWrapper(arg)) {
                        resultString += arg+ ",";
                    } else if (arg instanceof String) {
                        resultString += "\"" + arg + "\",";
                    } else
                        resultString += "\"" + System.identityHashCode(arg) + "\",";
                }
                resultString = resultString.substring(0, resultString.length() - 1); // Rimuove l'ultima virgola
                resultString += "],";
            }
            if (myRecord instanceof MyRecordExiting<?>) {
                Class<?> returnType = ((MyRecordExiting<?>) myRecord).returnType();
                if (!returnType.equals(void.class)) {
                    resultString += "\n\t\t\"result\": [";
                    Object returnValue = ((MyRecordExiting<?>) myRecord).result();
                    if (returnValue != null) {

                        if (returnType.isPrimitive()) {
                            resultString += primitiveReturnValue(returnType, returnValue);
                        } else if (returnValue instanceof String) {
                            resultString += "\"" + returnValue + "\"";
                        } else if (isWrapper(returnValue)) {
                            resultString += returnValue;
                        } else {
                            resultString += "\"" + System.identityHashCode(returnValue) + "\"";
                        }
                    } else
                        resultString += "\"" + null + "\"";
                    resultString += "],";
                }
            }
        }
        resultString += "\n\t\t\"name\": \"" + record.getSourceClassName() + "." + record.getSourceMethodName()
                + "\"\n\t}";
        return resultString;
    }

    private String primitiveReturnValue(Class<?> returnType,
            Object returnValue) {
        String resultString = "";
        switch (returnType.getTypeName()) {
            case "boolean":
                resultString += (boolean) returnValue;
                break;
            case "byte":
                resultString += (byte) returnValue;
                break;
            case "char":
                resultString += (char) returnValue;
                break;
            case "short":
                resultString += (short) returnValue;
                break;
            case "int":
                resultString += (int) returnValue;
                break;
            case "long":
                resultString += (long) returnValue;
                break;
            case "float":
                resultString += (float) returnValue;
                break;
            case "double":
                resultString += (double) returnValue;
                break;
        }
        return resultString;
    }

    private boolean isPrimitiveOrWrapper(Object obj) {
        return obj.getClass().isPrimitive() || isWrapper(obj);
    }

    private boolean isWrapper(Object obj) {
        return obj instanceof Integer ||
                obj instanceof Double ||
                obj instanceof Float ||
                obj instanceof Character ||
                obj instanceof Boolean ||
                obj instanceof Short ||
                obj instanceof Byte ||
                obj instanceof Long;
    }

}
