package kolpakovee.serialization;

import kolpakovee.json.JsonObject;
import kolpakovee.json.JsonProperty;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A class for serializing an object. Contains one method.
 */
public class Serializer {
    /**
     * A method for serializing objects. The fields of the objects we want to sterilize must be marked with annotation JsonProperty().
     *
     * @param obj the object we want to serialize.
     * @return JSONObject derived from obj (object fields in JSON format).
     * @throws IllegalAccessException   – if this Field object is enforcing Java language access control and the underlying field is inaccessible.
     * @throws IllegalArgumentException – if the specified object is not an instance of the class or interface declaring the underlying field (or a subclass or implementor thereof).
     */
    public static JsonObject serialize(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return new JsonObject(null);
        }

        if (obj instanceof String || obj instanceof Number || obj instanceof Boolean) {
            return new JsonObject(obj);
        }

        Map<String, Object> map = new HashMap<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            // елси поле помечено аннотацией JsonProperty
            if (field.getAnnotation(JsonProperty.class) != null) {
                // устанавливаем доступность поля
                field.setAccessible(true);

                // получаем значение поля
                Object value = field.get(obj);

                // формируем map для JSONObject
                map.put(Objects.equals(field.getAnnotation(JsonProperty.class).value(), "") ?
                                field.getName() :
                                field.getAnnotation(JsonProperty.class).value(),
                        value);
            }
        }

        return new JsonObject(map);
    }
}
