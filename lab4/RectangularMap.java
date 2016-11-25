package agh.cs.lab4;

import agh.cs.lab3.Position;
import agh.cs.lab5.AbstractWorldMap;

public class RectangularMap extends AbstractWorldMap {
	
	private Position mapStart = new Position(0,0);
	private Position mapEnd;

	
	public RectangularMap(int width, int height){
		this.mapEnd = this.mapStart.add(new Position(width, height));
	}

	@Override
	public String toString(){
        MapVisualizer p = new MapVisualizer();
        return p.dump(this, this.mapStart, this.mapEnd);
    }

}
