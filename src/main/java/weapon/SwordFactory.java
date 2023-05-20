package weapon;

public class SwordFactory implements WeaponFactory<SwordType> {
    @Override
    public Weapon createWeapon(SwordType type, int phase) {
        switch(type) {
            case IRON_SWORD -> {
                return new Sword(Rarity.COMMON, "Iron Sword", 10, phase);
            }
            case GOLDEN_SWORD -> {
                return new Sword(Rarity.EPIC, "Golden Sword", 20, phase);
            }
            case GREATSWORD -> {
                return new Sword(Rarity.UNIQUE, "Great Sword", 30, phase);
            }
        }
        return null;
    }
}
