package weapon;

/**
 * Strong weapon that attack in crescent motion.
 */
public class Sword extends Weapon {
    public Sword(Rarity weaponRarity, String weaponName, int damage, int phase, float weight) {
        super(weaponRarity, weaponName, damage, phase, weight);
    }

    @Override
    public int calculateDamageDealt() {
        return getDamage() + (getLevel()*3) + (2*getPhase()) + (getBonus()*2+1) ;
    }
}

