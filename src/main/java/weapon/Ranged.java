package weapon;

class Ranged extends Weapon {
    public Ranged(Rarity weaponRarity, String weaponName, int damage, int phase) {
        super(weaponRarity, weaponName, damage, phase);
    }

    @Override
    public int calculateDamageDealt() {
        return 0;
    }
}
