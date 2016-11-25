package agh.cs.lab6;

import agh.cs.lab3.Position;

/**
 * Created by Arek on 2016-11-25.
 */
public interface IPositionChangeObserver {
    public void positionChanged(Position old, Position newPosition);
}
