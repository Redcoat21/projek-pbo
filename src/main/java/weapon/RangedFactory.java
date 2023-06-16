package weapon;

public class RangedFactory implements WeaponFactory<RangedType>{
    @Override
    public Weapon createWeapon(RangedType type, int phase) {
        switch (type){
            case WOOD_BOW -> {
                return new Ranged(Rarity.COMMON, "Wooden Bow", 5, phase, 1.5f, 5);
            }
            case IRON_BOW -> {
                return new Ranged(Rarity.EPIC, "Iron Bow", 10, phase, 2, 8);
            }
        }
        throw new IllegalArgumentException("Invalid ranged type!");
    }
}
