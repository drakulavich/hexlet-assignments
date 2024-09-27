package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) {
        try {
            Files.writeString(path, car.serialize(), StandardOpenOption.CREATE);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Car extract(Path path) {
        try {
            return Car.deserialize(Files.readString(path));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
// END
