package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private String name;
    private Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String stringifyAttributes() {
        return attributes.entrySet().stream()
                .map(e -> " %s=\"%s\"".formatted(e.getKey(), e.getValue()))
                .collect(Collectors.joining(""));
    }
}
// END
