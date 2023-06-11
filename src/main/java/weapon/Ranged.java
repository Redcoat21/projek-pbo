package weapon;

public class Ranged extends Weapon {
    public Ranged(Rarity weaponRarity, String weaponName, int damage, int phase, float weight) {
        super(weaponRarity, weaponName, damage, phase, weight);
    }

    @Override
    public int calculateDamageDealt() {
        return 0;
    }
}
