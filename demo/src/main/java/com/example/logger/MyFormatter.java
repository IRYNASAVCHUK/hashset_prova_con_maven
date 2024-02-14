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
        MyRecord<?> myRecord = (MyRecord<?>) params[0];
        
        Object target = null;
        if (params != null && params.length > 0) {
            if (params[0] instanceof MyRecord<?>) {
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
        
        if (params != null && params.length > 0) {
            if (params[0] instanceof MyRecord) {
                // Controllo se params è vuoto
                Object[] args = myRecord.params();
                if (args != null && args.length > 0) {
                    ArrayNode argsNode = objectMapper.createArrayNode();
/*
    addPOJO() è un metodo della libreria Jackson ObjectMapper che consente di aggiungere 
    un oggetto Java POJO (Plain Old Java Object) come valore a un nodo di tipo ArrayNode 
    o ObjectNode durante la serializzazione JSON. Questo metodo si occupa automaticamente 
    di convertire l'oggetto Java in un formato JSON corrispondente.
*/                   
                    for (Object arg : args)
                        argsNode.addPOJO(arg);
                    jsonNode.set("args", argsNode);
                }
                // Controllo se returnType è void.class
                Class<?> returnType = myRecord.returnType();
                if (!returnType.equals(void.class)) {
                    Object returnValue = myRecord.result();
                    if (returnValue != null)
                        jsonNode.putPOJO("returnValue", returnValue);
                    else
                        jsonNode.putNull("returnValue");
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