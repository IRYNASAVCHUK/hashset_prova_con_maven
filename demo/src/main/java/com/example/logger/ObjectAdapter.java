package com.example.logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ObjectAdapter extends TypeAdapter<Object> {
    private int level;
    private boolean allFields;
    private boolean insideObject = false;

    public ObjectAdapter(int level, boolean allFields) {
        this.level = level;
        this.allFields = allFields;
    }

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        insideObject = false; // Resetta lo stato dell'oggetto

        out.beginObject();
        insideObject = true; // Indica che siamo all'interno di un oggetto

        try {
            Field[] fields = value.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                var fieldName = field.getName();
                var fieldValue = field.get(value);
                switch (fieldName) {
                    case "target":
                        out.name(fieldName);
                        if (fieldValue instanceof String)
                            out.value((String) fieldValue);
                        else
                            valueInfo(out, fieldValue, fieldName);
                        break;
                    case "args":
                        out.name(fieldName);
                        out.beginArray();
                        var list = (List<?>) fieldValue;
                        for (Object item : list) {
                            var pair = (Object[]) item;
                            var key = pair[0];
                            var val = pair[1];
                            if (key instanceof Class)
                                writeJsonValue(out, key, val, fieldName);
                        }
                        out.endArray();
                        break;
                    case "result":
                        var pair = (Object[]) fieldValue;
                        var key = pair[0];
                        var val = pair[1];
                        if (!key.equals(void.class)) {
                            out.name(fieldName);
                            writeJsonValue(out, key, (val==null)?null:val, fieldName);
                        }
                        break;
                    default:
                        out.name(fieldName);
                        out.value(String.valueOf(fieldValue));
                        break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (insideObject)
                out.endObject();
        }
    }

    private static boolean isWrapperOrString(Object obj) {
        return obj instanceof Integer || obj instanceof Double || obj instanceof Float ||
                obj instanceof Character || obj instanceof Boolean || obj instanceof Short ||
                obj instanceof Byte || obj instanceof Long || obj instanceof String;
    }

    private void wraperValue(JsonWriter out, Object val) throws IOException {
        if (val instanceof Number)
            out.value((Number) val);
        else if (val instanceof Boolean)
            out.value((boolean) val);
        else if (val instanceof Character)
            out.value((char) val);
        else
            out.value(String.valueOf(val));
    }
    private boolean isInternalClass(Class<?> clazz) {
        Package pkg = clazz.getPackage();
        return pkg != null && pkg.getName().startsWith("com.example");
    }
    
    private void exploreObject(JsonWriter out, Object obj, int currentLevel) throws IOException {
        if (currentLevel <= 0 || obj == null)
            return;
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
    System.out.println("clazz\t"+clazz);
    System.out.println("fields\t"+fields);
        if (isInternalClass(clazz)) {
            for (Field field : fields) {
                System.out.println("**********************");
                field.setAccessible(true);
                String fieldName = field.getName();
                System.out.println("fieldName\t"+fieldName);
                Object fieldValue = null;
                Object typeValue = field.getType();
                System.out.println("typeValue\t"+typeValue);
                try {
                    fieldValue = field.get(obj);
                    System.out.println("FieldValue\t"+fieldValue);
                } catch (IllegalAccessException e) {
                    System.out.println("ecezione");
                    e.printStackTrace();
                }
                out.name(fieldName);
                writeJsonValue(out, typeValue, (fieldValue==null)?null:fieldValue, fieldName);
                System.out.println("**********************");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private void valueInfo(JsonWriter out, Object val, String fieldName) throws IOException {
        out.beginObject();
        out.name("@");
        out.value(System.identityHashCode(val));
        out.name("class");
        out.value(val.getClass().getName());
        if (isWrapperOrString(val) && !fieldName.equals("target")) {
            out.name("const");
            wraperValue(out, val);
        }else{
            exploreObject(out, val, level);
        }
        out.endObject();
    }

    private void primitiveValue(JsonWriter out, Object key, Object val) throws IOException {
        switch (((Class<?>) key).getTypeName()) {
            case "boolean" -> out.value((boolean) val);
            case "byte" -> out.value((byte) val);
            case "char" -> out.value((char) val);
            case "short" -> out.value((short) val);
            case "int" -> out.value((int) val);
            case "long" -> out.value((long) val);
            case "float" -> out.value((float) val);
            case "double" -> out.value((double) val);
            default -> out.value(String.valueOf(val));
        }
    }

    private void writeJsonValue(JsonWriter out, Object key, Object val, String fieldName) throws IOException {
        if (val == null)
            out.nullValue();
        else if (((Class<?>) key).isPrimitive())
            primitiveValue(out, key, val);
        else
            valueInfo(out, val, fieldName);
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }
}
