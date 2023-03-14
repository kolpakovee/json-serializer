package kolpakovee;

import kolpakovee.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A class for serializing an object. Contains one method.
 */
public class Serializer {
    /**
     * Класс, который представляет собой объект JSON
     */
    public static class JsonObject {
        /**
         * Поле, чтобы хранить объекты пользовательских классов
         * Хранит в себе название поля и его значение
         */
        private Map<String, Object> map;
        /**
         * Поле, чтобы хранить объекты типа String, Number или Boolean
         */
        private Object object = null;

        public Map<String, Object> getMap() {
            return map;
        }

        public Object getObject() {
            return object;
        }

        public JsonObject(Map<String, Object> m) {
            if (m == null) {
                this.map = null;
            } else {
                this.map = new HashMap<>(m.size());

                for (final var element : m.entrySet()) {
                    if (element.getKey() == null) {
                        throw new NullPointerException("Null key.");
                    }

                    final Object value = element.getValue();

                    if (value != null) {
                        this.map.put(element.getKey(), value);
                    }
                }
            }
        }

        public JsonObject(Object obj) {
            if (obj != null) {
                if (obj instanceof String || obj instanceof Number || obj instanceof Boolean) {
                    object = obj;
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            // в случае, если у нас объект не пользовательского класса
            if (object != null) {
                if (object instanceof String) {
                    sb.append("\"")
                            .append(object)
                            .append("\"");
                    return sb.toString();
                } else {
                    return object.toString();
                }
            }

            // в случае, если передали null во конструктор на map
            if (map == null) {
                return null;
            }

            // в случае, если не было не одного поля
            if (map.isEmpty()) {
                return "{}";
            }

            // в ином случае
            sb.append("{");

            for (final var element : map.entrySet()) {
                sb.append("\"")
                        .append(element.getKey())
                        .append("\":");

                if (element.getValue() instanceof String) {
                    sb.append("\"")
                            .append(element.getValue())
                            .append("\"");
                } else {
                    sb.append(element.getValue());
                }

                sb.append(",");
            }

            sb.replace(sb.length() - 1, sb.length(), "}");

            return sb.toString();
        }
    }
    /**
     * A method for serializing objects. The fields of the objects we want to sterilize must be marked with annotation JsonProperty().
     *
     * @param obj the object we want to serialize.
     * @return JSONObject derived from obj (object fields in JSON format).
     * @throws IllegalAccessException   – if this Field object is enforcing Java language access control and the underlying field is inaccessible.
     * @throws IllegalArgumentException – if the specified object is not an instance of the class or interface declaring the underlying field (or a subclass or implementor thereof).
     */
    public static String serialize(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return new JsonObject(null).toString();
        }

        if (obj instanceof String || obj instanceof Number || obj instanceof Boolean) {
            return new JsonObject(obj).toString();
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

        return new JsonObject(map).toString();
    }
}
