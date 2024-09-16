package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String value;
    private TagInterface child;

    public LabelTag(String value, TagInterface child) {
        this.value = value;
        this.child = child;
    }

    public String render() {
        return "<label>" + value + child.render() + "</label>";
    }
}
// END
