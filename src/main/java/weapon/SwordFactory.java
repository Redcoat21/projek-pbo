package weapon;

/**
 * Strong weapon that attack in crescent motion.
 */
class Sword extends Weapon {
    public Sword(Rarity weaponRarity, String weaponName, int damage, int phase, float weight) {
        super(weaponRarity, weaponName, damage, phase, weight);
    }

    @Override
    public int calculateDamageDealt() {
        return getDamage() + (getLevel()*3) + (2*getPhase()) + (getBonus()*2+1) ;
    }
}

/**
 * Class with the factory pattern to create a new sword object.
 */
public class SwordFactory implements WeaponFactory<SwordType> {
    @Override
    public Weapon createWeapon(SwordType type, int phase) {
        switch(type) {
            case IRON_SWORD -> {
                return new Sword(Rarity.COMMON, "Iron Sword", 10, phase, 1);
            }
            case GOLDEN_SWORD -> {
                return new Sword(Rarity.EPIC, "Golden Sword", 20, phase, 1.2F);
            }
            case GREATSWORD -> {
                return new Sword(Rarity.UNIQUE, "Great Sword", 30, phase, 2);
            }
        }
        throw new IllegalArgumentException("Invalid sword type!");
    }
}
