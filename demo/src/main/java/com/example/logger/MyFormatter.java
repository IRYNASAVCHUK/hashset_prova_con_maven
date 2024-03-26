package com.example.logger;

import com.example.record.*;

import java.util.logging.*;


public class MyFormatter extends Formatter {
    
    @Override
    public String format(LogRecord record) {
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
            if (target == null)
                resultString += "\n\t\t\"target\": \"" + record.getSourceClassName() + "\",";
            else
                resultString += "\n\t\t\"target\": \"" + System.identityHashCode(target) + "\",";

            resultString += "\n\t\t\"args\": [";
            Object[] args = myRecord.params();
            if (args != null && args.length > 0) {
                for (Object arg : args)
                    resultString += "\"" + arg + "\",";
                resultString = resultString.substring(0, resultString.length() - 1); // Rimuove l'ultima virgola
            }
            resultString += "],";
            if (myRecord instanceof MyRecordExiting<?>) {
                resultString += "\n\t\t\"result\": [";
                Class<?> returnType = ((MyRecordExiting<?>) myRecord).returnType();
                if (!returnType.equals(void.class)) {
                    Object returnValue = ((MyRecordExiting<?>) myRecord).result();
                    if (returnValue != null)
                        if (returnType.isPrimitive())
                            //primitiveReturnValue(returnType, returnValue);
                        //else
                            resultString += "\"" + returnValue + "\"";
                    else
                        resultString += "\"" + null + "\"";
                }

                resultString += "],";
            }

        }
        resultString += "\n\t\t\"name\": \"" + record.getSourceClassName() + "." + record.getSourceMethodName()
                + "\"\n\t}";

        return resultString;
    }


    // private void primitiveReturnValue(Class<?> returnType,
    //         Object returnValue) {
    //     switch (returnType.getTypeName()) {
    //         case "boolean":
    //             jsonNode.put("result", (boolean) returnValue);
    //             break;
    //         case "byte":
    //             jsonNode.put("result", (byte) returnValue);
    //             break;
    //         case "char":
    //             jsonNode.put("result", (char) returnValue);
    //             break;
    //         case "short":
    //             jsonNode.put("result", (short) returnValue);
    //             break;
    //         case "int":
    //             jsonNode.put("result", (int) returnValue);
    //             break;
    //         case "long":
    //             jsonNode.put("result", (long) returnValue);
    //             break;
    //         case "float":
    //             jsonNode.put("result", (float) returnValue);
    //             break;
    //         case "double":
    //             jsonNode.put("result", (double) returnValue);
    //             break;
    //     }
    // }

}
