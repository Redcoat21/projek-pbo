package weapon;

class Ranged extends Weapon {
    private int speed;
    public Ranged(Rarity weaponRarity, String weaponName, int damage, int phase, float weight, int speed) {
        super(weaponRarity, weaponName, damage, phase, weight);
        this.speed = speed;
    }

    @Override
    public int calculateDamageDealt() {
        return getDamage() + (getLevel()*2) + (2*getPhase()) + getBonus();
    }

    public int getSpeed(){
        return speed;
    }
}

/**
 * Class with the factory pattern to create a new sword object.
 */
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
