package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final CharSequence text;

    public ReversedSequence(String input) {
        this.text = new StringBuilder(input).reverse();
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public char charAt(int index) {
        return text.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return text.subSequence(start, end);
    }
}
// END
