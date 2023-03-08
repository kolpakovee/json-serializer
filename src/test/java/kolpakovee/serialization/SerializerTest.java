package kolpakovee.serialization;

import kolpakovee.json.JsonObject;
import kolpakovee.model.Car;
import kolpakovee.model.Card;
import kolpakovee.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    @Test
    @DisplayName("Тест на возвращение JsonObject с null, если переданный объект равен null")
    void serialization_null_object_test_expected_JsonObject_with_null_fields() throws IllegalAccessException {
        JsonObject jsonObject = Serializer.serialize(null);

        assertNull(jsonObject.getObject());
        assertNull(jsonObject.getMap());
    }

    @Test
    @DisplayName("Тест на возвращение JsonObject с переданным объектом, если он является строкой")
    void serialization_String_object_test_expected_JsonObject_with_String_field() throws IllegalAccessException {
        String str = "Egor";
        JsonObject jsonObject = Serializer.serialize(str);

        assertEquals(jsonObject.getObject().toString(), str);
    }

    @Test
    @DisplayName("Тест на возвращение JsonObject с переданным объектом, если он является числом")
    void serialization_Number_object_test_expected_JsonObject_with_number_field() throws IllegalAccessException {
        int num = 5;
        JsonObject jsonObject = Serializer.serialize(num);

        assertEquals(jsonObject.getObject(), num);
    }

    @Test
    @DisplayName("Тест на возвращение JsonObject с переданным объектом, если он является булианом")
    void serialization_Boolean_object_test_expected_JsonObject_with_boolean_field() throws IllegalAccessException {
        Boolean b = true;
        JsonObject jsonObject = Serializer.serialize(b);

        assertEquals(jsonObject.getObject(), b);
    }

    @Test
    @DisplayName("Тест на возвращение JsonObject с map, если переданный объект содержит только поля, " +
            "помеченные аннотацией JsonProperty")
    void serialization_object_with_JsonProperty_fields_test_expected_JsonObject_with_map()
            throws IllegalAccessException {
        Car car = new Car("Audi", "A5", 2022);
        JsonObject jsonObject = Serializer.serialize(car);

        Map<String, Object> map = new HashMap<>();
        map.put("manufacturer", "Audi");
        map.put("model", "A5");
        map.put("year", 2022);

        assertEquals(jsonObject.getMap(), map);
    }

    @Test
    @DisplayName("Тест на возвращение JsonObject с пустым map, если переданный объект не содержит поля, " +
            "помеченные аннотацией JsonProperty")
    void serialization_object_without_JsonProperty_fields_test_expected_JsonObject_with_empty_map()
            throws IllegalAccessException {
        Person person = new Person("Egor", 19);
        JsonObject jsonObject = Serializer.serialize(person);

        assertTrue(jsonObject.getMap().isEmpty());
    }

    @Test
    @DisplayName("Тест на возвращение JsonObject с map, если переданный объект " +
            "содержит несколько полей, помеченных аннотацией JsonProperty. Ожидается, " +
            "что map будет содержать только помеченные аннотацией JsonProperty поля")
    void serialization_object_with_several_JsonProperty_fields_test_expected_JsonObject_with_map()
            throws IllegalAccessException {
        Card card = new Card("5336 6901 7419 4562", "4356");
        JsonObject jsonObject = Serializer.serialize(card);

        Map<String, Object> map = new HashMap<>();
        map.put("Card number", "5336 6901 7419 4562");

        assertEquals(jsonObject.getMap(), map);
    }
}