package kolpakovee;

import kolpakovee.model.Car;
import kolpakovee.model.Card;
import kolpakovee.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    @Test
    @DisplayName("Тест на возвращение JSON null, если переданный объект равен null")
    void serialization_null_object_test_expected_JsonObject_with_null() throws IllegalAccessException {
        String json = Serializer.serialize(null);

        assertNull(json);
    }

    @Test
    @DisplayName("Тест на возвращение JSON, если передать строку. Ожидается переданная строка в ковычках")
    void serialization_String_object_test_expected_JsonObject_with_String_field() throws IllegalAccessException {
        String str = "Egor";
        String json = Serializer.serialize(str);

        String expected = "\"Egor\"";
        assertEquals(expected, json);
    }

    @Test
    @DisplayName("Тест на возвращение JSON, если передать число. Ожидается корректный для числа JSON, то есть само число")
    void serialization_Number_object_test_expected_JsonObject_with_number_field() throws IllegalAccessException {
        int num = 5;
        String json = Serializer.serialize(num);

        assertEquals(Integer.toString(num), json);
    }

    @Test
    @DisplayName("Тест на возвращение JSON, если передать булиан. Ожидается true / false")
    void serialization_Boolean_object_test_expected_JsonObject_with_boolean_field() throws IllegalAccessException {
        Boolean b = true;
        String json = Serializer.serialize(b);

        assertEquals(b.toString(), json);
    }

    @Test
    @DisplayName("Тест на возвращение JSON, если переданный объект содержит только поля, " +
            "помеченные аннотацией JsonProperty. Ожидается, что мы получим JSON со всеми полями объекта.")
    void serialization_object_with_JsonProperty_fields_test_expected_JsonObject_with_map()
            throws IllegalAccessException {
        Car car = new Car("Audi", "A5", 2022);

        String json = Serializer.serialize(car);
        String expected = "{\"year\":2022,\"model\":\"A5\",\"manufacturer\":\"Audi\"}";

        assertEquals(expected, json);
    }

    @Test
    @DisplayName("Тест на возвращение пустого JSON, если переданный объект не содержит поля, " +
            "помеченные аннотацией JsonProperty. Ожидается {}")
    void serialization_object_without_JsonProperty_fields_test_expected_JsonObject_with_empty_map()
            throws IllegalAccessException {
        Person person = new Person("Egor", 19);

        String json = Serializer.serialize(person);
        String expected = "{}";

        assertEquals(expected, json);
    }

    @Test
    @DisplayName("Тест на возвращение JSON, если переданный объект " +
            "содержит несколько полей, помеченных аннотацией JsonProperty. Ожидается, " +
            "что JSON будет содержать только помеченные аннотацией JsonProperty поля")
    void serialization_object_with_several_JsonProperty_fields_test_expected_JsonObject_with_map()
            throws IllegalAccessException {
        Card card = new Card("5336 6901 7419 4562", "4356");

        String json = Serializer.serialize(card);
        String expected = "{\"Card number\":\"5336 6901 7419 4562\"}";

        assertEquals(expected, json);
    }

    @Test
    @DisplayName("Тест на создание объекта класса JSON, если передвался объект с полем null. " +
            "Ожидается, что null-поля не будут в JSON ")
    public void serialization_object_with_null_values_test_expected_not_contains() throws IllegalAccessException {
        Car car = new Car("Audi", null, 2022);

        String json = Serializer.serialize(car);
        String expected = "{\"year\":2022,\"manufacturer\":\"Audi\"}";

        assertEquals(expected, json);
    }
}