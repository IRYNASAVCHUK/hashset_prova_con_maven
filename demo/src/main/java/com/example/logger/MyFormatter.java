package com.example.logger;

import com.example.record.*;


import java.util.logging.*;

public class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        String event = record.getMessage().contains("ENTRY") ? ConfigLoader.getConfigValue("eventEntry")
                : record.getMessage().contains("RETURN") ? ConfigLoader.getConfigValue("eventReturn") : "";

        /*
         * {"event":"func_post",
         * "target":1458540918,
         * "result":0,
         * "name":"com.example.hashset.MyHashSet.getSize"}
         */

        String resultString = "\n\t{\n\t\t\"event\":\t\"" + event + "\",";
        
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
                resultString += "\n\t\t\"target\":\t\"" + record.getSourceClassName() + "\",";
            else
                resultString += "\n\t\t\"target\":\t\"" + System.identityHashCode(target) + "\",";

            //paramsControl(jsonNode, myRecord);
        }
        resultString += "\n\t\t\"name\":\t\"" + record.getSourceClassName() + "." + record.getSourceMethodName()
                + "\"\n\t}";
        return resultString;
    }

    // private void paramsControl(ObjectNode jsonNode, MyRecord record) {
    //     Object[] args = record.params();
    //     addArgsNode(jsonNode, args);
    //     if (record instanceof MyRecordExiting)
    //         handleExitingRecord(jsonNode, (MyRecordExiting<?>) record);
    // }

    // private void handleExitingRecord(ObjectNode jsonNode, MyRecordExiting<?> exitingRecord) {
    //     Class<?> returnType = exitingRecord.returnType();
    //     if (!returnType.equals(void.class)) {
    //         Object returnValue = exitingRecord.result();
    //         if (returnValue != null)
    //             if (returnType.isPrimitive())
    //                 primitiveReturnValue(jsonNode, returnType, returnValue);
    //             else
    //                 jsonNode.putPOJO("result", returnValue);
    //         else
    //             jsonNode.putNull("result");
    //     }
    // }

    // private void addArgsNode(ObjectNode jsonNode, Object[] args) {
    //     if (args != null && args.length > 0) {
    //         ArrayNode argsNode = objectMapper.createArrayNode();
    //         for (Object arg : args)
    //             argsNode.addPOJO(arg);
    //         jsonNode.set("args", argsNode);
    //     }
    // }

    // private void primitiveReturnValue(ObjectNode jsonNode, Class<?> returnType, Object returnValue) {
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
