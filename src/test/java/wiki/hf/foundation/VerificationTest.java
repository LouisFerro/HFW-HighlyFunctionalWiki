package wiki.hf.foundation;

import static org.junit.jupiter.api.Assertions.*;
import static wiki.hf.foundation.NullabilityVerification.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
class VerificationTest {

    @Test
    void CheckNullability() {
        assertThat(NullabilityVerification.notNull("not null")).isEqualTo("not null");

        assertThat(assertThrows(IllegalArgumentException.class, () -> NullabilityVerification.notNull(null)))
                .hasMessageContaining("argument must not be null!");
    }

    @Test
    void CheckNullabilityFilter() {
        assertTrue(nullFilter.test(null));
        assertFalse(nullFilter.test("null"));
        assertTrue(nonNullFilter.test("null"));
        assertFalse(nonNullFilter.test(null));
    }
}