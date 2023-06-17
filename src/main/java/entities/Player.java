package entities;

import animation.AnimationLoader;
import animation.AnimationPlayer;
import main.Main;
import processing.core.PApplet;
import weapon.*;

public class Player extends Entity {
    private Weapon playerWeapon;
    private WeaponFactory<SwordType> swordFactory = (swordType, phase) -> {
        switch(swordType) {
            case IRON_SWORD -> new Sword(Rarity.COMMON, "Iron Sword", 10, phase, 1);
            case GOLDEN_SWORD -> new Sword(Rarity.EPIC, "Golden Sword", 20, phase, 1.2F);
            case GREATSWORD -> new Sword(Rarity.UNIQUE, "Great Sword", 30, phase, 2);
        }
        throw new IllegalArgumentException("Invalid sword type!");
    };

    private WeaponFactory<SpearType> spearFactory = (spearType, phase) -> {
        switch(spearType) {
            case IRON_SPEAR -> new Spear(Rarity.COMMON, "Iron Spear", 10, phase, 1);
            case GLAIVE -> new Spear(Rarity.EPIC, "Golden Spear", 20, phase, 1.2F);
            case SPEAR_OF_THE_LORD -> new Spear(Rarity.UNIQUE, "Great Spear", 30, phase, 2);
        }
        throw new IllegalArgumentException("Invalid spear type!");
    };

    private WeaponFactory<RangedType> rangedFactory = (rangedType, phase) -> {
        switch (rangedType){
            case WOOD_BOW -> new Ranged(Rarity.COMMON, "Wooden Bow", 5, phase, 1.5f, 5);
            case IRON_BOW -> new Ranged(Rarity.EPIC, "Iron Bow", 10, phase, 2, 8);
        }
        throw new IllegalArgumentException("Invalid ranged type!");
    };

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        // Starting weapon is iron sword.
        this.playerWeapon = swordFactory.createWeapon(SwordType.IRON_SWORD, 1);
        this.addSprite("idle", Direction.NONE, AnimationLoader.loadImage("assets/Sprites/Skeleton_Archer/atkdown1.png"));
    }

    @Override
    public void render() {
        new AnimationPlayer().play(Direction.NONE, this);
        this.attack(this);
    }

    /**
     * Move method for player object.
     * Note that it doesn't have any parameter, unlike {@link Entity}'s method {@link #move(Direction)}.
     */
    public void move() {
        PApplet mainProgram = Main.getMainProgram();
        Direction movingToward = switch(mainProgram.key) {
            case 'a', 'A' -> Direction.LEFT;
            case 'd', 'D' -> Direction.RIGHT;
            case 's', 'S' -> Direction.DOWN;
            case 'w', 'W' -> Direction.UP;
            default -> Direction.NONE;
        };
        super.move(movingToward);
    }

    @Override
    public boolean isAttacking() {
        return true;
    }

    @Override
    public void attack(Entity target) {
        System.out.println("Attacking!");
    }

    public void changeWeapon(String weaponType, Enum<?> changeInto, int phase) {

        if(weaponType.equalsIgnoreCase("sword")) {
            this.playerWeapon = swordFactory.createWeapon((SwordType) changeInto, phase);
        } else if(weaponType.equalsIgnoreCase("spear")) {
            this.playerWeapon = spearFactory.createWeapon((SpearType) changeInto, phase);
        } else if(weaponType.equalsIgnoreCase("ranged")) {
            this.playerWeapon = rangedFactory.createWeapon((RangedType) changeInto, phase);
        } else {
            throw new IllegalArgumentException("Invalid weapon type!");
        }
    }
}
