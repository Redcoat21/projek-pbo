package interface_package;

public interface CollisionObserver<T> {
    void notifyCollision(T entities);
}
