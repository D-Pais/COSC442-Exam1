import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Effect;

class EffectTest {
    private Effect effect;

    @BeforeEach
    void setUp() {
        effect = new Effect(2);
    }

    @Test
    void becomesDoneAsUpdatesReduceDuration() {
        assertFalse(effect.isDone());

        effect.update(null);
        assertFalse(effect.isDone());
        effect.update(null);
        assertTrue(effect.isDone());
    }

    @Test
    void copyKeepsItsOwnDuration() {
        Effect copy = new Effect(effect);

        assertNotSame(effect, copy);
        effect.update(null);
        effect.update(null);

        assertTrue(effect.isDone());
        assertFalse(copy.isDone());
    }

    @Test
    void startAndEndLeaveDurationUnchanged() {
        effect.start(null);
        effect.end(null);

        assertFalse(effect.isDone());
    }

    @Test
    void zeroAndNegativeDurationsAreDone() {
        assertTrue(new Effect(0).isDone());
        assertTrue(new Effect(-1).isDone());
    }
}
