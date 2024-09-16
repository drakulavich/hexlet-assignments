package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    public String toString() {
        var attributesStr = attributes.entrySet().stream()
                .map(e -> "%s=\"%s\"".formatted(e.getKey(), e.getValue()))
                .collect(Collectors.joining(" "));

        var result = attributesStr.isBlank() ? "<%s>".formatted(name) : "<%s %s>".formatted(name, attributesStr);
        return result;
    }
}
// END
