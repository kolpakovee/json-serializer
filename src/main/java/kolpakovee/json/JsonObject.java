package kolpakovee.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, который представляет собой объект JSON
 */
public class JsonObject {
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
