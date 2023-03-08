package kolpakovee;

import kolpakovee.json.JsonObject;
import kolpakovee.serialization.Serializer;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        JsonObject jsonpObject = Serializer.serialize("");
        System.out.println(jsonpObject);
    }
}