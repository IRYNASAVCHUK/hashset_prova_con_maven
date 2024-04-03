package com.example.logger;

import com.example.record.*;

import java.util.logging.*;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        System.out.println(record);
        System.out.println(record.getMessage());

        String event = record.getMessage().contains("ENTRY") ? ConfigLoader.getConfigValue("eventEntry")
                : record.getMessage().contains("RETURN") ? ConfigLoader.getConfigValue("eventReturn") : "";

        String resultString = "\n\t{\n\t\t\"event\": \"" + event + "\",";

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
            // chiamate di hashCode() inaspetatti : addCustomer, removeCustomer, containsCustomer,
            resultString += "\n\t\t\"target\": \"";
            resultString += (target == null) ? record.getSourceClassName() : System.identityHashCode(target);
            resultString += "\",";
            // resultString += "\n\t\t\"args\": [";
            // Object[] args = myRecord.params();
            // if (args != null && args.length > 0) {
            //     for (Object arg : args)
            //         resultString += "\"" + arg + "\",";
            //     resultString = resultString.substring(0, resultString.length() - 1); // Rimuove l'ultima virgola
            // }
            // resultString += "],";
            // if (myRecord instanceof MyRecordExiting<?>) {
            //     Class<?> returnType = ((MyRecordExiting<?>) myRecord).returnType();
            //     if (!returnType.equals(void.class)) {
            //         resultString += "\n\t\t\"result\": [";
            //         Object returnValue = ((MyRecordExiting<?>) myRecord).result();
            //         if (returnValue != null)
            //             if (returnType.isPrimitive())
            //                 primitiveReturnValue(returnType, returnValue, resultString);
            //             else
            //                 resultString += "\"" + returnValue + "\"";
            //         else
            //             resultString += "\"" + null + "\"";
            //         resultString += "],";
            //     }
            // }
        }
        resultString += "\n\t\t\"name\": \"" + record.getSourceClassName() + "." + record.getSourceMethodName()
                + "\"\n\t}";
        return resultString;
    }

    private void primitiveReturnValue(Class<?> returnType,
            Object returnValue, String resultString) {
        switch (returnType.getTypeName()) {
            case "boolean":
                resultString += "\"" + (boolean) returnValue + "\"";
                break;
            case "byte":
                resultString += "\"" + (byte) returnValue + "\"";
                break;
            case "char":
                resultString += "\"" + (char) returnValue + "\"";
                break;
            case "short":
                resultString += "\"" + (short) returnValue + "\"";
                break;
            case "int":
                resultString += "\"" + (int) returnValue + "\"";
                break;
            case "long":
                resultString += "\"" + (long) returnValue + "\"";
                break;
            case "float":
                resultString += "\"" + (float) returnValue + "\"";
                break;
            case "double":
                resultString += "\"" + (double) returnValue + "\"";
                break;
        }
    }

}
