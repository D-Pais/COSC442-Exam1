import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Effect;
import game.Item;
import game.Spell;

class ItemTest {
    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item('!', Color.RED, "Staff", "Red Staff");
    }

    @Test
    void returnsConstructorValuesAndDefaults() {
        assertEquals('!', item.glyph());
        assertSame(Color.RED, item.color());
        assertEquals("Staff", item.name());
        assertEquals("Red Staff", item.appearance());
        assertEquals(0, item.foodValue());
        assertEquals(0, item.attackValue());
        assertEquals(0, item.defenseValue());
        assertEquals(1, item.thrownAttackValue());
        assertEquals(0, item.rangedAttackValue());
    }

    @Test
    void usesNameWhenAppearanceIsNull() {
        Item unnamedAppearance = new Item('?', Color.BLUE, "Potion", null);

        assertEquals("Potion", unnamedAppearance.appearance());
    }

    @Test
    void modifiesCombatAndFoodValues() {
        item.modifyFoodValue(4);
        item.modifyFoodValue(-1);
        item.modifyAttackValue(3);
        item.modifyAttackValue(-1);
        item.modifyDefenseValue(2);
        item.modifyDefenseValue(-1);
        item.modifyThrownAttackValue(2);
        item.modifyThrownAttackValue(-1);
        item.modifyRangedAttackValue(5);
        item.modifyRangedAttackValue(-2);

        assertEquals(3, item.foodValue());
        assertEquals(2, item.attackValue());
        assertEquals(1, item.defenseValue());
        assertEquals(2, item.thrownAttackValue());
        assertEquals(3, item.rangedAttackValue());
    }

    @Test
    void buildsDetailsForNonDefaultValues() {
        assertEquals("", item.details());

        item.modifyAttackValue(2);
        item.modifyThrownAttackValue(2);
        item.modifyRangedAttackValue(4);
        item.modifyDefenseValue(3);
        item.modifyFoodValue(5);

        assertEquals("  attack:2  thrown:3  ranged:4  defense:3  food:5", item.details());
    }

    @Test
    void storesQuaffEffect() {
        Effect effect = new Effect(3);

        item.setQuaffEffect(effect);

        assertSame(effect, item.quaffEffect());
    }

    @Test
    void addsWrittenSpellsInOrder() {
        Effect firstEffect = new Effect(5);
        Effect secondEffect = new Effect(3);

        item.addWrittenSpell("Fireball", 10, firstEffect);
        item.addWrittenSpell("Eldritch Blast", 5, secondEffect);

        assertEquals(2, item.writtenSpells().size());
        Spell first = item.writtenSpells().get(0);
        Spell second = item.writtenSpells().get(1);
        assertEquals("Fireball", first.name());
        assertEquals(10, first.manaCost());
        assertNotSame(firstEffect, first.effect());
        assertEquals("Eldritch Blast", second.name());
        assertEquals(5, second.manaCost());
    }
}
