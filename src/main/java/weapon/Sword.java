package weapon;

class Sword extends Weapon {
    public Sword(Rarity weaponRarity, String weaponName, int damage, int phase) {
        super(weaponRarity, weaponName, damage, phase);
    }

    @Override
    public int calculateDamageDealt() {
        return 0;
    }
}
