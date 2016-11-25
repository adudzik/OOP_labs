package agh.cs.lab3;

public enum MapDirection {
	North,
    East,
    South,
    West;
	
	public String toString(){
		switch(this){
		case North : return "P�noc";
		case South : return "Po�udnie";
		case West  : return "Zach�d";
		case East  : return "Wschod";
		default    : return " ";
		}
	}
	
	public MapDirection next(){
		return MapDirection.values()[(this.ordinal()+1)%4];
	}
	
	public MapDirection previous(){
		return MapDirection.values()[(this.ordinal()+3)%4];
	}

}
