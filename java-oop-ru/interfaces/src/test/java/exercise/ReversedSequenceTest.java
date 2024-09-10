package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReversedSequenceTest {
    CharSequence text;

    @BeforeEach
    void setUp() {
        text = new ReversedSequence("abcdef");
    }

    @Test
    void length() {
        assertThat(text.length()).isEqualTo(6);
    }

    @Test
    void charAt() {
        assertThat(text.charAt(1)).isEqualTo('e');
    }

    @Test
    void subSequence() {
        assertThat(text.subSequence(1, 4)).isEqualTo("edc");
    }
}