package agh.cs.lab5;

import agh.cs.lab3.Car;
import agh.cs.lab3.MoveDirection;
import agh.cs.lab3.Position;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab6.IPositionChangeObserver;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected List<Car> carsList = new ArrayList<>();
    protected Map<Position, Car> cars = new HashMap<>();

    public boolean canMoveTo(Position position) {
        return !this.isOccupied(position);
    }

    public boolean add(Car car) {
        if (!this.canMoveTo(car.getPosition()))
            throw new IllegalArgumentException(car.getPosition() + " is occupied");
        this.cars.put(car.getPosition(), car);
        this.cars.get(car.getPosition()).addListener(this);
        return true;
    }


    public void run(MoveDirection[] directions) {
        for (int i = 0; i < directions.length;) {
            int k = i;
            for (Car car : this.cars.values()) {
                car.move(directions[k]);
                k++;
            }
            i += this.cars.size();
        }
    }


    public boolean isOccupied(Position position) {
        if (this.objectAt(position) != null)
            return true;
        return false;
    }


    public Object objectAt(Position position) {
        return this.cars.get(position);
    }

    public void positionChanged(Position old, Position newPosition){
        Car car = this.cars.remove(old);
        this.cars.put(newPosition, car);
    }
}
