package game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CombatRulesTest {
    
    @ParameterizedTest
    @CsvSource ({
        "50, -33, 17",
        "50, -1, 49",
        "50, 0, 50",
        "50, 1, 51",
        "50, 23, 73",
        "50, 51, 100",
        "50, 97, 100"
    })
    void testCalculateNewHp(int currentHp, int amount, int expectedHp) {
        assert(expectedHp == CombatRules.calculateNewHp(currentHp, 100, amount));
    }

    @ParameterizedTest
    @CsvSource ({
        "17, false",
        "1, false",
        "0, true",
        "-1, true",
        "-13, true"
    })
    void testIsDead(int currentHp, boolean expectedHp) {
        assert(expectedHp == CombatRules.isDead(currentHp));
    }
}
