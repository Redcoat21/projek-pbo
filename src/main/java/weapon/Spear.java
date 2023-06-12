package weapon;

/**
 * Spear weapon have increased range but can only attack in thrusting motion.
 */

public class Spear extends Weapon {
    public Spear(Rarity weaponRarity, String weaponName, int damage, int phase, float weight) {
        super(weaponRarity, weaponName, damage, phase, weight);
    }

    @Override
    public int calculateDamageDealt() {
        return 1;
    }
}
