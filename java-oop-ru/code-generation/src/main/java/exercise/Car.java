package exercise;

import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Data
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            return "";
        }
    }

    public static Car deserialize(String json) {
        try {
            return new ObjectMapper().readValue(json, Car.class);
        } catch (Exception e) {
            return null;
        }
    }
    // END
}
