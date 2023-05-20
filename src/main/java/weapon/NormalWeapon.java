package weapon;

public abstract class NormalWeapon implements Weapon {
    private String weaponName;
    private int attackSpeed;
    // Base damage of the weapon, not the damage dealt.
    private final int damage;
    // Phase is calculated using Waves / 3
    private int phase;
    private int level;
    // Bonus act as a bonus damage based on the level of the previous phase when switching phase.
    private int bonus;
    private final Rarity weaponRarity;
    // Determine whether the weapon have lifesteal or not.
    private boolean lifesteal;
    /**
     * Constructor for the Weapon's class child class.
     * @param weaponRarity The rarity of the weapon
     * @param damage The base damage of the weapon
     * @param phase What phase is the weapon acquired on.
     */

    public NormalWeapon(Rarity weaponRarity, String weaponName, int damage, int phase, boolean lifesteal) {
        this.weaponName = weaponName;
        this.damage = damage;
        this.phase = phase;
        this.bonus = 0;
        this.level = 1;
        this.weaponRarity = weaponRarity;
        this.lifesteal = lifesteal;
    }

    /**
     * Calculate the damage based on different factors such as level, and base damage.
     * Each weapon types have its own implementation on how to calculate the damage.
     *
     * @return the calculated damage.
     */
    public abstract int calculateDamageDealt();

    /**
     * Increase the level of the weapon.
     * @param increaseBy The amount of level to be added.
     */
    public void increaseLevel(int increaseBy){
        this.level += increaseBy;
    }

    /**
     * Switch phase for 9 waves each.
     * Increase phase by 1 and increase the bonus level by the current level.
     * Reset the level back to 0.
     */
    public void switchPhase(){
        this.phase++;
        this.bonus += this.level;
        this.level = 0;
    }

    /**
     * Accessor for the base damage of the weapon.
     * @return The base damage of the weapon.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Accessor for the phase of the weapon.
     * @return The current phase the weapon on.
     */
    public int getPhase() {
        return phase;
    }

    /**
     * Mutator for the phase of the weapon.
     * @param phase The phase that the weapon will be set on.
     */
    public void setPhase(int phase) {
        if(phase > 0) { this.phase = phase; }
        else { throw new IllegalArgumentException("Phase cant be negative!"); }
    }

    /**
     * Accessor for the level of the weapon.
     * @return The level of the weapon.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Mutator for the level of the weapon.
     * @param level The level to be set to the weapon. Note that the parameter can be negative.
     */
    public void setLevel(int level) {
        int temp = level + this.level;
        //Set level to 0 if its negative.
        this.level = Math.max(temp, 0);
    }

    /**
     * Accessor for the bonus of the weapon.
     * @return The bonus the weapon have.
     */
    public int getBonus() {
        return bonus;
    }
    /**
     * Accessor for the weapon's rarity.
     * @return The weapon's rarity. Weapon's rarity can only be one of the following : Common, Epic, Unique, Legendary
     */
    public Rarity getWeaponRarity() {
        return weaponRarity;
    }
}
