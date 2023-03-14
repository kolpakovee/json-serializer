package kolpakovee.model;

import kolpakovee.annotation.JsonProperty;

/**
 * Класс, написанный для тестирования
 * Не все поля помечены аннотацией JsonProperty
 */
public class Card {
    @JsonProperty("Card number")
    private final String number;
    private final String pinCode;

    public Card(String number, String pinCode) {
        this.number = number;
        this.pinCode = pinCode;
    }
}
