package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        var fields = object.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .filter(field -> {
                    Object value;
                    try {
                        field.setAccessible(true);
                        value = field.get(object);
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return value == null;
                })
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        var resultMap = new HashMap<String, List<String>>();
        var fields = object.getClass().getDeclaredFields();

        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(MinLength.class) || field.isAnnotationPresent(NotNull.class))
                .forEach(field -> {
                    var errors = new ArrayList<String>();
                    String value;
                    try {
                        field.setAccessible(true);
                        value = (String) field.get(object);
                        if (field.isAnnotationPresent(MinLength.class)) {
                            var minLength = field.getAnnotation(MinLength.class).value();
                            if (value == null || value.length() < minLength) {
                                errors.add("length less than " + minLength);
                            }
                        }
                        if (field.isAnnotationPresent(NotNull.class) && value == null) {
                            errors.add("can not be null");
                        }
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    if (!errors.isEmpty()) {
                        resultMap.put(field.getName(), errors);
                    }
                });

        return resultMap;
    }
}
// END
