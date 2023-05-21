package weapon;

public class SpearFactory implements WeaponFactory<SpearType> {
    @Override
    public Weapon createWeapon(SpearType type, int phase) {
        switch(type) {
            case IRON_SPEAR -> {
                return new Spear(Rarity.COMMON, "Iron Spear", 10, phase);
            }
            case GLAIVE -> {
                return new Spear(Rarity.UNIQUE, "Glaive", 13, phase);
            }
            case SPEAR_OF_THE_LORD -> {
                return new Spear(Rarity.LEGENDARY, "Spear of The Lord", 23, phase);
            }
        }
        throw new IllegalArgumentException("Invalid spear type!");
    }
}
