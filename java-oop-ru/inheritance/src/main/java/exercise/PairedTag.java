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
        String attributes = stringifyAttributes();
        String name = getName();
        String value = children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());

        return "<%s%s>%s%s</%s>".formatted(name, attributes, body, value, name);
    }
}
// END
