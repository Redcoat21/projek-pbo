package weapon;

public interface WeaponFactory<T extends Enum<T>> {
    Weapon createWeapon(T type, int phase);
}
