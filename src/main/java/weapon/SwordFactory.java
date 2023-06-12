package weapon;
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
