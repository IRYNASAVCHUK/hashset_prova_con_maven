package com.example.logger;

import com.example.hashset.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;

public class MyFormatter extends Formatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(LogRecord record) {

        String className = record.getSourceClassName();
        String methodName = record.getSourceMethodName();
        Object[] params = record.getParameters();

        String event = "unknown";
        if (record.getMessage().contains("ENTRY")) {
            event = "func_pre";
        } else if (record.getMessage().contains("RETURN")) {
            event = "func_post";
        }

        ArrayNode paramsNode = JsonNodeFactory.instance.arrayNode();

        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("event", event)
                .put("name", className + "." + methodName);

        if (params != null && params.length > 0) {
            for (Object param : params) {
                if (param instanceof Object[]) {
                    paramsNode.add(Arrays.toString((Object[]) param));
                } else {
                    paramsNode.add(param.toString());
                }
            }
            ((ObjectNode) jsonNode).set("args", paramsNode);

            if (params[0] instanceof Customer || params[0] instanceof MyHashSet) {
                ((ObjectNode) jsonNode).put("target", System.identityHashCode(params[0]));
            } else {
                ((ObjectNode) jsonNode).put("target", this != null ? System.identityHashCode(this) : null);
            }
        }
        // return jsonNode.toString() + System.lineSeparator();
        return jsonNode.toPrettyString() + System.lineSeparator();// piu leggibile per debugging
    }

}
