package game;

public class CombatRules {

    public static int calculateNewHp(int currentHp, int maxHp, int amount) {

        //foresees the HP after the incoming change
        int newHp = currentHp + amount;

        //no overheals
        if (newHp > maxHp)
            return maxHp;

        //return results of hp modification
        return newHp;
    }

    //checks if at lethal health levels
    public static boolean isDead(int hp) {
        return hp < 1;
    }
}
