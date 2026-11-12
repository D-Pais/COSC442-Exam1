import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Inventory;
import game.Item;

class InventoryTest {
    private Inventory inventory;
    private Item first;
    private Item second;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(2);
        first = new Item('a', Color.WHITE, "First", "First");
        second = new Item('b', Color.WHITE, "Second", "Second");
    }

    @Test
    void addsItemsAndReportsWhenFull() {
        assertFalse(inventory.isFull());

        inventory.add(first);
        inventory.add(second);

        assertSame(first, inventory.get(0));
        assertSame(second, inventory.get(1));
        assertTrue(inventory.isFull());

        inventory.add(new Item('c', Color.WHITE, "Extra", "Extra"));
        assertSame(first, inventory.get(0));
        assertSame(second, inventory.get(1));
    }

    @Test
    void exposesItemsAndReturnsNullForEmptySlots() {
        assertNull(inventory.get(0));
        assertSame(inventory.getItems()[0], inventory.get(0));
    }

    @Test
    void removesOnlyTheMatchingItem() {
        inventory.add(first);
        inventory.add(second);
        Item notStored = new Item('c', Color.WHITE, "Other", "Other");

        inventory.remove(notStored);
        assertSame(first, inventory.get(0));
        assertSame(second, inventory.get(1));

        inventory.remove(first);
        assertNull(inventory.get(0));
        assertSame(second, inventory.get(1));
        assertFalse(inventory.isFull());
    }

    @Test
    void checksContainmentByIdentity() {
        inventory.add(first);

        assertTrue(inventory.contains(first));
        assertFalse(inventory.contains(second));
    }

    @Test
    void zeroCapacityInventoryIsFull() {
        assertTrue(new Inventory(0).isFull());
    }
}
