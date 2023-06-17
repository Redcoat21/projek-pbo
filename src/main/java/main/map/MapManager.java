package main.map;

import entities.Entity;
import interface_package.Observer;
import interface_package.Subject;

import java.util.LinkedList;

/**
 * Class to handle entity's position in the map.
 */
public class MapManager implements Subject {
    private LinkedList<Observer> listOfObserver;
    private LinkedList<Entity> listOfEntities;
    private Map map;

    public MapManager(Map map) {
        this.listOfObserver = new LinkedList<>();
        this.listOfEntities = new LinkedList<>();
        this.map = map;
    }

    /**
     * Add entity to the list of Entity's.
     * @param entity The entity that will be added.
     */
    public void addEntity(Entity entity) {
        this.listOfEntities.add(entity);
    }

    @Override
    public void addObserver(Observer o) {
        this.listOfObserver.add(o);
    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers() {

    }
}
