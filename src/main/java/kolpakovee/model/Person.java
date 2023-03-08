package kolpakovee.model;

/**
 * Класс, написанный для тестирования
 * Все поля намеренно не помечены аннотацией JsonProperty
 */
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
