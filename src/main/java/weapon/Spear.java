package weapon;

/**
 * Spear weapon have increased range but can only attack in thrusting motion.
 */

public class Spear extends Weapon {
    public Spear(Rarity weaponRarity, String weaponName, int damage, int phase) {
        super(weaponRarity, weaponName, damage, phase);
    }

    @Override
    public int calculateDamageDealt() {
        return 0;
    }
}
