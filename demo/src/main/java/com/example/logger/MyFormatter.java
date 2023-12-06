package com.example.logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(LogRecord record) {
        String className = record.getSourceClassName();
        String methodName = record.getSourceMethodName();
        Object[] params = record.getParameters();
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("event", methodName.equals("entering") ? "func_pre" : "func_post")
                .put("class", className)
                .put("method", record.getSourceMethodName());
        if (params != null && params.length > 0) {
            ((ObjectNode) jsonNode).put("targetId", System.identityHashCode(params[0]));
        }
        return jsonNode.toString() + System.lineSeparator();
        //return jsonNode.toPrettyString() + System.lineSeparator();
    }
}
