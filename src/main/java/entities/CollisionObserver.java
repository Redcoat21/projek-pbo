package entities;

public interface CollisionObserver<T> {
    void notifyCollision(T entities);
}
