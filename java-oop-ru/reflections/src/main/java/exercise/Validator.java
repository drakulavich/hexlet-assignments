package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        var fields = object.getClass().getDeclaredFields();
        var errors = new ArrayList<String>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(object) == null) {
                        errors.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return errors;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        var resultMap = new HashMap<String, List<String>>();
        var fields = object.getClass().getDeclaredFields();
        for (Field field: fields) {
            var errors = new ArrayList<String>();

            if (field.isAnnotationPresent(MinLength.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(object) != null) {
                        var minLength = field.getAnnotation(MinLength.class).value();
                        if (field.get(object).toString().length() < minLength) {
                            errors.add("length less than " + minLength);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(object) == null) {
                        errors.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!errors.isEmpty()) {
                resultMap.put(field.getName(), errors);
            }
        }
        return resultMap;
    }
}
// END
