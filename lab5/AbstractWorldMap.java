package agh.cs.lab5;

import agh.cs.lab3.Car;
import agh.cs.lab3.MoveDirection;
import agh.cs.lab3.Position;
import agh.cs.lab4.IWorldMap;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap {

    //List<Car> cars = new ArrayList<>();
    Map<Position, Car> cars = new HashMap<>();

    public boolean canMoveTo(Position position) {
        return !this.isOccupied(position);
    }

    public boolean add(Car car) {
        if(!this.canMoveTo(car.getPosition()))
            throw new IllegalArgumentException(car.getPosition() + " is occupied");
        return this.cars.add(car);
    }


    public void run(MoveDirection[] directions) {
        for(int i=0; i < directions.length; i++){
            cars.get(i % cars.size()).move(directions[i]);
        }
    }


    public boolean isOccupied(Position position) {
        if(this.objectAt(position) != null)
            return true;
        return false;
    }


    public Object objectAt(Position position) {
        for(Car car : this.cars){
            if(car.getPosition().equals(position))
                return car;
        }
        return null;
    }
}
