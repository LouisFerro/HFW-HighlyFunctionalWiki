package wiki.hf.foundation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class LikeFormatTest implements LikeFormat {

    @Test
    void formatExpression() {
        assertThat(formatExpression("Like")).isEqualTo("Like%");
        assertThat(formatExpression("%Like")).isEqualTo("%Like");
    }
}