package weapon;

public class Ranged extends Weapon {
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
