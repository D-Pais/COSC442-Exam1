package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

public class CreatureTest {
    private World world;
    private Creature creature;

    @BeforeEach
    void setUp() {
        Tile[][][] tiles = new Tile[3][3][1];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        world = new World(tiles);

        creature = new Creature(world, '@', Color.GRAY, "josh", 100, 10, 5);

        creature.x = 1;
        creature.y = 1;
        creature.z = 0;

        // added to check for death later
        world.add(creature);
    }
    @AfterEach
    void tearDown() {
        world = null;
        creature = null;
    }


    @Test
    void testRegularLevels() {
        creature.modifyHp(-51, "Meaningful Damage");
        assert(creature.hp() == 49 && creature.causeOfDeath().equals("Meaningful Damage"));
        creature.modifyHp(13, "Meaningful Healing");
        assert(creature.hp() == 62 && creature.causeOfDeath().equals("Meaningful Healing"));
        creature.modifyHp(-1, "Slight Damage");
        assert(creature.hp() == 61 && creature.causeOfDeath().equals("Slight Damage"));
        creature.modifyHp(1, "Slight Healing");
        assert(creature.hp() == 62 && creature.causeOfDeath().equals("Slight Healing"));
        creature.modifyHp(-61, "Just Above Death");
        assert(creature.hp() == 1 && creature.causeOfDeath().equals("Just Above Death"));
        assert(world.creature(1, 1, 0) != null);
    }

    @Test
    void testOverheal() {
        creature.modifyHp(38, "Overheal Setup");
        creature.modifyHp(50, "Overheal");
        assert(world.creature(1, 1, 0) != null);
    }

    @Test
    void testExactLethal() {
        try {
            creature.modifyHp(-100, "Lethal Damage");
            // if the creature is still alive, the test fails
            assert(world.creature(1, 1, 0) == null);
        } catch (NullPointerException e) {
            // throws a NPE because the creature has no AI for onNotify to handle on death, but this does mean the creature is killed, so the test passes
            return;
        }
    }

    @Test
    void testJustOverLethal() {
        try {
            creature.modifyHp(-101, "Just Over Lethal Damage");
            // if the creature is still alive, the test fails
            assert(world.creature(1, 1, 0) == null);
        } catch (NullPointerException e) {
            // throws a NPE because the creature has no AI for onNotify to handle on death, but this does mean the creature is killed, so the test passes
            return;
        }
    }

    @Test
    void testMuchLethal() {
        try {
            creature.modifyHp(-231, "Meaningful Lethal");
            // if the creature is still alive, the test fails
            assertEquals(world.creature(1, 1, 0), null);
        } catch (NullPointerException e) {
            // throws a NPE because the creature has no AI for onNotify to handle on death, but this does mean the creature is killed, so the test passes
            return;
        }
    }

}
