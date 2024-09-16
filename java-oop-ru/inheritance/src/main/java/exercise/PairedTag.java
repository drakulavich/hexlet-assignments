package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    public String toString() {
        var attributesStr = attributes.entrySet().stream()
                .map(e -> "%s=\"%s\"".formatted(e.getKey(), e.getValue()))
                .collect(Collectors.joining(" "));
        var childrenStr = children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
        if (attributesStr.isBlank()) {
            return "<%s>%s%s</%s>".formatted(name, body, childrenStr, name);
        }
        return "<%s %s>%s%s</%s>".formatted(name, attributesStr, body, childrenStr, name);
    }
}
// END
