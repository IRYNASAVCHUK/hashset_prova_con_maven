package com.example.logger;

import com.example.hashset.Customer;
import com.example.hashset.MyHashSet;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(LogRecord record) {
        String className = record.getSourceClassName();
        String methodName = record.getSourceMethodName();
        Object[] params = record.getParameters();
        Level level = record.getLevel();
        Object returnValue = (params != null && params.length > 0)?params[0]:null;
        String event = "unknown";
        if (record.getMessage().contains("ENTRY")) {
            event = "func_pre";
        } else if (record.getMessage().contains("RETURN")) {
            event = "func_post";
        }
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("event", event)
                .put("class", className)
                .put("method", methodName);
        if (params != null && params.length > 0) {
            // Aggiungi il target solo se è un oggetto
            if (params[0] != null && params[0] instanceof Customer || params[0] instanceof MyHashSet) {
                ((ObjectNode) jsonNode).put("target", System.identityHashCode(params[0]));
            }

            // Aggiungi gli argomenti passati
            // ArrayNode paramsNode = objectMapper.createArrayNode();
            ArrayNode paramsNode = JsonNodeFactory.instance.arrayNode();
            for (Object param : params) {
                paramsNode.add(param.toString());
            }
            ((ObjectNode) jsonNode).set("arguments", paramsNode);
        }
        // Aggiungi il nome completo del metodo
        ((ObjectNode) jsonNode).put("fullMethodName", className + "." + methodName);

        // Se è un'uscita e il livello è FINE o superiore, aggiungi il valore restituito
        if (record.getMessage().contains("RETURN") && level.intValue() >= Level.FINE.intValue()
                && returnValue != null) {
            ((ObjectNode) jsonNode).put("returnValue", returnValue.toString());
        }

        // return jsonNode.toString() + System.lineSeparator();
        return jsonNode.toPrettyString() + System.lineSeparator();
    }
}
