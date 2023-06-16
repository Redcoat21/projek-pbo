package weapon;

/**
 * Spear weapon have increased range but can only attack in thrusting motion.
 */
class Spear extends Weapon {
    public Spear(Rarity weaponRarity, String weaponName, int damage, int phase, float weight) {
        super(weaponRarity, weaponName, damage, phase, weight);
    }

    @Override
    public int calculateDamageDealt() {
        return getDamage() + (getLevel()*3) + (2*getPhase()) + (getBonus()*2+3);
    }
}

/**
 * Class with the factory pattern to create a new spear object.
 */
public class SpearFactory implements WeaponFactory<SpearType> {
    @Override
    public Weapon createWeapon(SpearType type, int phase) {
        switch(type) {
            case IRON_SPEAR -> {
                return new Spear(Rarity.COMMON, "Iron Spear", 10, phase, 0.8F);
            }
            case GLAIVE -> {
                return new Spear(Rarity.UNIQUE, "Glaive", 13, phase, 1);
            }
            case SPEAR_OF_THE_LORD -> {
                return new Spear(Rarity.LEGENDARY, "Spear of The Lord", 23, phase, 1.1F);
            }
        }
        throw new IllegalArgumentException("Invalid spear type!");
    }
}
