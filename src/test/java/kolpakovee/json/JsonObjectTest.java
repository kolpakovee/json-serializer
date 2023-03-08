package kolpakovee.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonObjectTest {
    @Test
    @DisplayName("Тест на передачу null в конструктор, ожидается, что объект класса JsonObject не будет null")
    void null_to_constructor_test_expected_not_null_jsonObject() {
        JsonObject jsonObject = new JsonObject(null);
        assertNotNull(jsonObject);
    }

    @Test
    @DisplayName("Тест на создание объекта класса JsonObject с непустыми значениями, ожидается, " +
            "что map в JsonObject проинициализируется корректно")
    public void non_empty_map_constructor_test_expected_equals() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", 2);

        JsonObject jsonObject = new JsonObject(map);

        assertEquals("value1", jsonObject.getMap().get("key1"));
        assertEquals(2, jsonObject.getMap().get("key2"));
    }

    @Test
    @DisplayName("Тест на создание объекта класса JsonObject с пустыми значениями, ожидается, " +
            "что map в JsonObject не будут полей с пустыми значениями, но будут остальные поля")
    public void null_values_in_map_to_constructor_test_expected_not_contains() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", null);
        map.put("key2", "value2");

        JsonObject jsonObject = new JsonObject(map);

        assertFalse(jsonObject.getMap().containsKey("key1"));
        assertEquals("value2", jsonObject.getMap().get("key2"));
    }

    @org.junit.Test(expected = NullPointerException.class)
    @DisplayName("Тест на выбрасывание исключения при передаче ключа со значением null")
    public void null_key_in_map_to_constructor_test_expected_NullPointerException() {
        Map<String, Object> map = new HashMap<>();
        map.put(null, "value");

        new JsonObject(map);
    }

    @Test
    @DisplayName("Тест на создание строки для пустого объекта, ожидается null")
    public void toString_test_empty_object_expected_null() {
        JsonObject jsonObject = new JsonObject(null);
        String actual = jsonObject.toString();

        assertNull(actual);
    }

    @Test
    @DisplayName("Тест на создание строки для объекта типа String, ожидается объект типа String" +
            "в двойных кавычках")
    public void toString_test_with_string_object_expected_add_quote_marks() {
        JsonObject jsonObject = new JsonObject("test");

        String expected = "\"test\"";
        String actual = jsonObject.toString();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест на создание строки для объекта типа Integer, ожидается число")
    public void toString_test_with_integer_object_expected_number() {
        JsonObject jsonObject = new JsonObject(42);

        String expected = "42";
        String actual = jsonObject.toString();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест на создание строки для объекта типа Map с непустыми значениями, ожидается " +
            "строка формата JSON, корректно отображающая все ключи:значения")
    public void toString_test_with_non_empty_map_expected_json() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", 2);

        JsonObject jsonObject = new JsonObject(map);

        String expected = "{\"key1\":\"value1\",\"key2\":2}";
        String actual = jsonObject.toString();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест на создание строки для объекта типа Map с пустыми значениями, ожидается " +
            "строка формата JSON, не содержащая пустые значения")
    public void toString_test_with_null_values_map_expected_json_without_null_fields() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", null);
        map.put("key2", "value2");

        JsonObject jsonObject = new JsonObject(map);

        String expected = "{\"key2\":\"value2\"}";
        String actual = jsonObject.toString();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест на создание строки для объекта типа Map с ключами и значениями типа String, " +
            "ожидается корректная строка в формате JSON")
    public void toString_test_with_string_map_expected_json() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        JsonObject jsonObject = new JsonObject(map);

        String expected = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        String actual = jsonObject.toString();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест на создание строки для пустого объекта типа Map, ожидается {}")
    public void toString_test_with_empty_map_expected_json() {
        Map<String, Object> map = new HashMap<>();

        JsonObject jsonObject = new JsonObject(map);

        String expected = "{}";
        String actual = jsonObject.toString();

        assertEquals(expected, actual);
    }
}