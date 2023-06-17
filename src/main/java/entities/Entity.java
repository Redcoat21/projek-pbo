package entities;

import animation.Animation;
import interface_package.Animatable;
import interface_package.Combatant;
import interface_package.Moveable;
import interface_package.Renderable;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Base class for any object that : Have sprites, can attack and can move.
 */
public abstract class Entity implements Animatable, Combatant, Moveable, Renderable {
    private float movingSpeed;
    private float attackSpeed;
    private final Animation walkingSprites;
    private final Animation idleSprites;
    private final Animation attackingSprites;
    private PVector position;
    private final PVector size;
    private Direction facingToward;
    private Direction direction;

    /**
     * Constructor for the Entity class and its child.
     * @param x The x position of the entity in the map.
     * @param y The y position of the entity in the map.
     * @param width The width of the entity.
     * @param height The height of the entity.
     */

    public Entity(float x, float y, int width, int height) {
        this.position = new PVector(x, y);
        this.size = new PVector(width, height);
        this.attackingSprites = this.idleSprites = this.walkingSprites = new Animation(30);
        this.direction = Direction.NONE;
        this.facingToward = Direction.RIGHT;
    }
    /**
     * Get the current position (x,y) of the entity in PVector.
     * @return Vector2 position of the entity.
     */
    public PVector getPosition() {
        return this.position;
    }

    /**
     * Get the size of the entity in PVector.
     * @return PVector format of the size (width, height).
     */
    public PVector getSize() {
        return this.size;
    }

    /**
     * Get the sprites list of the entity.
     * @return Spriteslist in {@link Animation}.
     */
    public Animation getSprites(String forWhich) {
        if(forWhich.equalsIgnoreCase("walk")) {
            return this.walkingSprites;
        } else if(forWhich.equalsIgnoreCase("idle")) {
            return this.idleSprites;
        } else if(forWhich.equalsIgnoreCase("attack")) {
            return this.attackingSprites;
        }
        throw new IllegalArgumentException("Invalid animation types!");
    }

    /**
     * Set the entity's position on the given (x,y) value.
     * @param x The new x position of the entity.
     * @param y The new y position of the entity.
     */
    public void setTo(float x, float y) {
        this.position.set(x, y);
    }

    /**
     * Set the entity's position on the given (x,y) value but with a PVector parameter.
     * @param position The new (x,y) value of the entity.
     */
    public void setTo(PVector position) {
        this.position.set(position.x, position.y);
    }

    /**
     * Get the current direction that the entity is moving toward.
     * @return The direction that the entity is moving toward.
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Get the current direction that the entity is facing to.
     * @return The direction that the entity is facing to.
     */
    public Direction getFacingDirection() {
        return this.facingToward;
    }

    /**
     * Determine whether the entity is moving or not.
     * @return True if the entity is moving, false otherwise.
     */
    public boolean isMoving() {
        return this.direction != Direction.NONE;
    }

    /**
     * Determine whether the entity is idling or not.
     * @return True if the entity is idling, false otherwise.
     */
    public boolean isIdling() {
        return this.direction == Direction.NONE;
    }

    /**
     * Get the entity's moving speed.
     * @return The entity's moving speed.
     */
    public float getMovingSpeed() {
        return movingSpeed;
    }

    /**
     * Get the entity's attack speed.
     * @return The entity's attack speed.
     */
    public float getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Determine whether the entity is attacking or not.
     * @return True if the entity is attacking, false otherwise.
     */
    public abstract boolean isAttacking();

    @Override
    public void addSprite(String forWhichAnimation, Direction animationFor, PImage sprite) {
        if(forWhichAnimation.equalsIgnoreCase("walk")) {
            this.walkingSprites.addSprite(animationFor, sprite, this.size);
        } else if(forWhichAnimation.equalsIgnoreCase("idle")) {
            this.idleSprites.addSprite(animationFor, sprite, this.size);
        } else if(forWhichAnimation.equalsIgnoreCase("attack")) {
            this.attackingSprites.addSprite(animationFor, sprite, this.size);
        } else {
            throw new IllegalArgumentException("Invalid animation type");
        }
    }

    @Override
    public final void move(Direction movingToward) {
        this.position = this.position.add(movingToward.getDirection());
        this.facingToward = movingToward;
    }
}
