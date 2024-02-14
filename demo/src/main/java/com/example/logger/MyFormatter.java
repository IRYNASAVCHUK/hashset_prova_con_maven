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
            } else {
                target = params[0];
            }
        }
        if (target == null)
            jsonNode.putNull("target");
        else if (target instanceof Class<?>)
            jsonNode.put("target", ((Class<?>) target).getName());
        else
            jsonNode.put("target", System.identityHashCode(target));

        if (params != null && params.length > 0) {
            if (params[0] instanceof MyRecord) {
                MyRecord<?> myRecord = (MyRecord<?>) params[0];
                // Controllo se params è vuoto
                Object[] args = myRecord.params();
                /*
                 * addPOJO() è un metodo della libreria Jackson ObjectMapper che consente di
                 * aggiungere un oggetto Java POJO (Plain Old Java Object) come valore a un nodo
                 * di tipo ArrayNode o ObjectNode durante la serializzazione JSON. Questo metodo
                 * si occupa automaticamente di convertire l'oggetto Java in un formato JSON
                 * corrispondente.
                 */
                if (args != null && args.length > 0) {
                    ArrayNode argsNode = objectMapper.createArrayNode();
                    for (Object arg : args)
                        argsNode.addPOJO(arg);
                    jsonNode.set("args", argsNode);
                }
                // Controllo se returnType è void.class
                Class<?> returnType = myRecord.returnType();
                if (!returnType.equals(void.class)) {
                    Object returnValue = myRecord.result();
                    if (returnValue != null) {
                        // Verifica se il risultato è un tipo primitivo
                        // The primitive Java types: boolean, byte, char, short, int, long, float,
                        // double
                        if (returnType.isPrimitive()) {
                            switch (returnType.getTypeName()) {
                                case "boolean":
                                    boolean boolValue = (boolean) returnValue;
                                    jsonNode.put("result", boolValue);
                                    break;
                                case "byte":
                                    byte byteValue = (byte) returnValue;
                                    jsonNode.put("result", byteValue);
                                    break;
                                case "char":
                                    char charValue = (char) returnValue;
                                    jsonNode.put("result", charValue);
                                    break;
                                case "short":
                                    short shortValue = (short) returnValue;
                                    jsonNode.put("result", shortValue);
                                    break;
                                case "int":
                                    int intValue = (int) returnValue;
                                    jsonNode.put("result", intValue);
                                    break;
                                case "long":
                                    long longValue = (long) returnValue;
                                    jsonNode.put("result", longValue);
                                    break;
                                case "float":
                                    float floatValue = (float) returnValue;
                                    jsonNode.put("result", floatValue);
                                    break;
                                case "double":
                                    double doubleValue = (double) returnValue;
                                    jsonNode.put("result", doubleValue);
                                    break;
                            }
                        }
                    }

                    else
                        jsonNode.putNull("result");
                }
            } else {
                // Gestisco il caso in cui params non è un'istanza di MyRecord, ma un Object[]
                ArrayNode argsNode = objectMapper.createArrayNode();
                for (Object arg : params)
                    argsNode.addPOJO(arg);
                jsonNode.set("args", argsNode);
            }
        }
        jsonNode.put("name", record.getSourceClassName() + "." + record.getSourceMethodName());

        // return jsonNode.toString() + System.lineSeparator();
        return jsonNode.toPrettyString() + System.lineSeparator();
    }
}