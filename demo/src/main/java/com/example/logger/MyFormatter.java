package com.example.logger;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.logging.*;

public class MyFormatter extends Formatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(LogRecord record) {
        String event = record.getMessage().contains("ENTRY") ? "func_pre"
                : record.getMessage().contains("RETURN") ? "func_post" : "";

        ObjectNode jsonNode = objectMapper.createObjectNode().put("event", event);

        Object[] params = record.getParameters();
        // TODO: non sono sicura che si fa cosi
        // FIXME: target
        Object target = null;
        if (params != null && params.length > 0) {
            if (params[0] instanceof MyRecord<?>) {
                MyRecord<?> myRecord = (MyRecord<?>) params[0];
                target = myRecord.result();
            } else
                target = params[0];
        }
        if (target == null)
            jsonNode.putNull("target");
        else if (target instanceof Class<?>)
            jsonNode.put("target", ((Class<?>) target).getName());
        else
            jsonNode.put("target", System.identityHashCode(target));

        if (params != null && params.length > 0)
            paramsControl(jsonNode, params);

        jsonNode.put("name", record.getSourceClassName() + "." + record.getSourceMethodName());

        // return jsonNode.toString() + System.lineSeparator();
        return jsonNode.toPrettyString() + System.lineSeparator();
    }

    private void paramsControl(ObjectNode jsonNode, Object[] params) {
        if (params[0] instanceof MyRecord) {
            MyRecord<?> myRecord = (MyRecord<?>) params[0];
            Object[] args = myRecord.params();
            if (args != null && args.length > 0) {
                ArrayNode argsNode = objectMapper.createArrayNode();
                for (Object arg : args)
                    argsNode.addPOJO(arg);
                jsonNode.set("args", argsNode);
            }
            Class<?> returnType = myRecord.returnType();
            if (!returnType.equals(void.class)) {
                Object returnValue = myRecord.result();
                if (returnValue != null) {
                    if (returnType.isPrimitive())
                        primitiveReturnValue(jsonNode, returnType, returnValue);
                    else
                        jsonNode.putPOJO("result", returnValue);
                } else
                    jsonNode.putNull("result");
            }
        } else {
            ArrayNode argsNode = objectMapper.createArrayNode();
            for (Object arg : params)
                argsNode.addPOJO(arg);
            jsonNode.set("args", argsNode);
        }
    }
    // The primitive types: boolean, byte, char, short, int, long, float, double
    private void primitiveReturnValue(ObjectNode jsonNode, Class<?> returnType, Object returnValue) {
        switch (returnType.getTypeName()) {
            case "boolean":
                jsonNode.put("result", (boolean) returnValue);
                break;
            case "byte":
                jsonNode.put("result", (byte) returnValue);
                break;
            case "char":
                jsonNode.put("result", (char) returnValue);
                break;
            case "short":
                jsonNode.put("result", (short) returnValue);
                break;
            case "int":
                jsonNode.put("result", (int) returnValue);
                break;
            case "long":
                jsonNode.put("result", (long) returnValue);
                break;
            case "float":
                jsonNode.put("result", (float) returnValue);
                break;
            case "double":
                jsonNode.put("result", (double) returnValue);
                break;
        }
    }
}