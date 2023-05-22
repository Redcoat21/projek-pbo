package weapon;

/**
 * Interface for all the factory method.
 * @param <T> The type of the factory, e.g. Sword, Spear, Ranged.
 */
public interface WeaponFactory<T extends Enum<T>> {

    /**
     * Factory method to create a weapon.
     * @param type The type of the weapon e.g. Sword, Spear, Ranged.
     * @param phase The phase that the weapon is created.
     * @return Weapon based on the given type.
     */
    Weapon createWeapon(T type, int phase);
}
