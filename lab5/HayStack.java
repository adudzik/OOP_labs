package agh.cs.lab5;


import agh.cs.lab3.Position;

public class HayStack {
    private Position stackPosition;

    public HayStack (Position position){
        this.stackPosition = position;
    }

    public Position getPosition(){
        return this.stackPosition;
    }

    @Override
    public String toString(){
        return "S";
    }
}
