package agh.cs.lab5;

import agh.cs.lab3.Car;
import agh.cs.lab3.Position;
import agh.cs.lab4.MapVisualizer;

import java.util.ArrayList;
import java.util.List;

public class UnboundedMap extends AbstractWorldMap {

    List<HayStack> hayStacks = new ArrayList<>();

    public UnboundedMap (List<HayStack> list){
        this.hayStacks = list;
    }

    public String toString(){
        int east = Integer.MAX_VALUE, west = Integer.MIN_VALUE, north = Integer.MIN_VALUE, south = Integer.MAX_VALUE;

        for(Car car : this.cars.values()){
            int carX = car.getPosition().x, carY = car.getPosition().y;

            if(carX < east)
                east = carX;
            if(carX > west)
                west = carX;
            if(carY < south)
                south = carY;
            if(carY > north)
                north = carY;
        }

        for(HayStack stack : this.hayStacks){
            int stackX = stack.getPosition().x, stackY = stack.getPosition().y;

            if(stackX < east)
                east = stackX;
            if(stackX > west)
                west = stackX;
            if(stackY < south)
                south = stackY;
            if(stackY > north)
                north = stackY;
        }

        MapVisualizer p = new MapVisualizer();
        return p.dump(this, new Position(east, south), new Position(west, north));
    }


    @Override
    public Object objectAt(Position position) {
        for(Car car : this.cars.values()){
            if(car.getPosition().equals(position))
                return car;
        }

        for(HayStack stack : this.hayStacks ){
            if(stack.getPosition().equals(position))
                return stack;
        }

        return null;
    }
}
