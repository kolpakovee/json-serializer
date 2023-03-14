package kolpakovee;

import kolpakovee.model.Car;
import kolpakovee.model.Card;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Car car = new Car("Audi", null, 2022);
        String json = Serializer.serialize(car);
        System.out.println(json);
    }
}