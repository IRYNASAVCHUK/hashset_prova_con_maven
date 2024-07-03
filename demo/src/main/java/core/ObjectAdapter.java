package core;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.*;

import core.utils.Records;

public class ObjectAdapter extends TypeAdapter<Object> {
    private Records.Levels level;
    private boolean allFields;
    private boolean insideObject = false;

    public ObjectAdapter(Records.Levels level, boolean allFields) {
        this.level = level;
        this.allFields = allFields;
    }

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        insideObject = false;
        out.beginObject();
        insideObject = true;
        try {
            writeFields(out, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (insideObject)
                out.endObject();
        }
    }

    private void writeFields(JsonWriter out, Object value) throws IllegalAccessException, IOException {
        Field[] fields = value.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            var fieldName = field.getName();
            var fieldValue = field.get(value);
            writeField(out, fieldName, fieldValue);
        }
    }

    private void writeField(JsonWriter out, String fieldName, Object fieldValue)
            throws IOException, IllegalAccessException {
        switch (fieldName) {
            case "target":
                writeTargetField(out, fieldName, fieldValue);
                break;
            case "args":
                writeArgsField(out, fieldName, fieldValue);
                break;
            case "result":
                writeResultField(out, fieldName, fieldValue);
                break;
            default:
                out.name(fieldName);
                out.value(String.valueOf(fieldValue));
                break;
        }
    }

    private void writeResultField(JsonWriter out, String fieldName, Object fieldValue)
            throws IOException, IllegalAccessException {
        var pair = (Object[]) fieldValue;
        var key = pair[0];
        var val = pair[1];
        if (!key.equals(void.class)) {
            out.name(fieldName);
            writeJsonValue(out, key, (val == null) ? null : val, fieldName, level.result());
        }
    }

    private void writeArgsField(JsonWriter out, String fieldName, Object fieldValue)
            throws IOException, IllegalAccessException {
        out.name(fieldName);
        out.beginArray();
        var list = (List<?>) fieldValue;
        for (Object item : list) {
            var pair = (Object[]) item;
            var key = pair[0];
            var val = pair[1];
            if (key instanceof Class)
                writeJsonValue(out, key, val, fieldName, level.args());
        }
        out.endArray();
    }

    private void writeTargetField(JsonWriter out, String fieldName, Object fieldValue)
            throws IOException, IllegalAccessException {
        out.name(fieldName);
        if (fieldValue instanceof String)
            out.value((String) fieldValue);
        else
            valueInfo(out, fieldValue, fieldName, level.target());
    }

    private static boolean isWrapperOrString(Object obj) {
        return obj instanceof Integer || obj instanceof Double || obj instanceof Float ||
                obj instanceof Character || obj instanceof Boolean || obj instanceof Short ||
                obj instanceof Byte || obj instanceof Long || obj instanceof String;
    }

    private void wrapsValue(JsonWriter out, Object val) throws IOException {
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
        return pkg != null && pkg.getName().startsWith("helper");
    }

    private void exploreObject(JsonWriter out, Object obj, int currentLevel)
            throws IOException, IllegalArgumentException, IllegalAccessException {
        if (currentLevel < 0 || obj == null)
            return;
        Class<?> clazz = obj.getClass();
        while (clazz != null && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            if (isInternalClass(clazz)) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    var fieldName = field.getName();
                    Object fieldValue = null;
                    var typeValue = field.getType();
                    fieldValue = field.get(obj);
                    out.name(fieldName);
                    writeJsonValue(out, typeValue, (fieldValue == null) ? null : fieldValue, fieldName, currentLevel);
                }
            }
            if (allFields) {
                clazz = clazz.getSuperclass();
            } else
                break;
        }

    }

    private void valueInfo(JsonWriter out, Object val, String fieldName, int currentLevel)
            throws IOException, IllegalArgumentException, IllegalAccessException {
        out.beginObject();
        out.name("@");
        out.value(System.identityHashCode(val));
        out.name("class");
        out.value(val.getClass().getName());
        if (isWrapperOrString(val) && !fieldName.equals("target")) {
            out.name("const");
            wrapsValue(out, val);
        } else
            exploreObject(out, val, currentLevel - 1);
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

    private void writeJsonValue(JsonWriter out, Object key, Object val, String fieldName, int currentLevel)
            throws IOException, IllegalArgumentException, IllegalAccessException {
        if (val == null)
            out.nullValue();
        else if (((Class<?>) key).isPrimitive())
            primitiveValue(out, key, val);
        else
            valueInfo(out, val, fieldName, currentLevel);
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }
}
