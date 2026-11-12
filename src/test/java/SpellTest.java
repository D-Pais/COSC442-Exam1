import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import game.Effect;
import game.Spell;

class SpellTest {
    @Test
    void returnsSpellPropertiesAndRequiresTarget() {
        Effect effect = new Effect(3);
        Spell spell = new Spell("Heal", 7, effect);

        assertEquals("Heal", spell.name());
        assertEquals(7, spell.manaCost());
        assertTrue(spell.requiresTarget());
    }

    @Test
    void returnsIndependentCopiesOfItsEffect() {
        Spell spell = new Spell("Heal", 7, new Effect(2));
        Effect first = spell.effect();
        Effect second = spell.effect();

        assertNotSame(first, second);
        first.update(null);
        first.update(null);

        assertTrue(first.isDone());
        assertFalse(second.isDone());
        assertFalse(spell.effect().isDone());
    }
}
