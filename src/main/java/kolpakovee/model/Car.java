package kolpakovee.model;

import kolpakovee.json.JsonProperty;

/**
 * Класс, написанный для тестирования
 * Все поля намеренно помечены аннотацией JsonProperty
 */
public class Car {
    @JsonProperty()
    private final String manufacturer;

    @JsonProperty()
    private final String model;

    @JsonProperty("year")
    private final int yearOfProduction;

    public Car(String manufacturer, String model, int yearOfProduction) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
    }
}
