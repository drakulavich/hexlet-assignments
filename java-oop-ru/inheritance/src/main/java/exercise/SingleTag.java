package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    public String toString() {
        return "<%s%s>".formatted(getName(), stringifyAttributes());
    }
}
// END
