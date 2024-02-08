package com.example.logger;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;

public class MyFormatter extends Formatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(LogRecord record) {
        System.out.println("\n***");
        String className = record.getSourceClassName();
        String methodName = record.getSourceMethodName();
        Object[] params = record.getParameters();// exiting ha Object params non arrayyy

        String event="";
        if (record.getMessage().contains("ENTRY")) 
            event = "func_pre";
        if (record.getMessage().contains("RETURN")) 
            event = "func_post";

        ArrayNode paramsNode = JsonNodeFactory.instance.arrayNode();

        ObjectNode jsonNode = objectMapper.createObjectNode()// cambiato da  Json Node
        
                .put("event", event);

        if (params != null && params.length > 0) {
            int contatore = 0;
            for (Object param : params) {
                System.out.println(contatore++ +" Param: \t"+param);
                if (param instanceof Object[]) {
                    paramsNode.add(Arrays.toString((Object[]) param));
                } else {
                    paramsNode.add(param.toString());
                }
            }
/*
             * TODO:
             * - obj == null -> restituisce null
             * - obj instaceof Class<?> -> restituisce obj.getName()
             * - altrimenti restituisce System.identityHashCode(obj)
             */

            /*
             * if (params[0] instanceof Customer || params[0] instanceof MyHashSet) {
             *     ((ObjectNode) jsonNode).put("target", System.identityHashCode(params[0]));
             * }
             */
            
            if (params[0] == null) {
                ((ObjectNode) jsonNode).putNull("target");
            } else if (params[0] instanceof Class<?>) {
                ((ObjectNode) jsonNode).put("target", ((Class<?>) params[0]).getName());
            } else {
                ((ObjectNode) jsonNode).put("target", System.identityHashCode(params[0]));
            }


            ((ObjectNode) jsonNode).set("args", objectMapper.valueToTree(params[0]));

            // new returnValue
            if (record.getMessage().contains("RETURN")){
                if (params[0] != Void.class) {
                    ((ObjectNode) jsonNode).set("returnValue", objectMapper.valueToTree(params[0]));
                }
                //((ObjectNode) jsonNode).set("returnValue", paramsNode);
            }
            
                
            System.out.println(className + "." + methodName);
            System.out.println("***");
            ((ObjectNode)jsonNode).put("name", className + "." + methodName);

            
        } else {
            ((ObjectNode) jsonNode).put("args", "null");
        }
        // return jsonNode.toString() + System.lineSeparator();
        // piu leggibile per debugging
        return jsonNode.toPrettyString() + System.lineSeparator();
    }

}
