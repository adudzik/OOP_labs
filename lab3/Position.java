package agh.cs.lab3;

public class Position {
	public final int x;
	public final int y;
	
	public Position(int x, int y){
		this.x=x;
		this.y=y;
	}

	@Override
	public int hashCode() {
		int hash = 13;
		hash += this.x * 31;
		hash += this.y * 17;
		return hash;
	}
	
	@Override
	public String toString(){
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public boolean smaller(Position that){     
		return (this.x <= that.x && this.y <= that.y);
	}
	
	public boolean larger(Position that){     
		return (this.x >= that.x && this.y >= that.y);
	}
	
	public Position add(Position that){
		Position result = new Position(that.x+this.x, that.y+this.y);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
