package agh.cs.lab3;

import agh.cs.lab4.IWorldMap;

public class Car {
	private Position position = new Position(2, 2);
	private MapDirection direction = MapDirection.North;
	private IWorldMap mapCar;


	public Car(IWorldMap map) {
		this.mapCar = map;
	}

	public Car(IWorldMap map, int x, int y) {
		this.mapCar = map;
		this.position = new Position(x, y);
	}

	@Override
	public String toString() {
		switch(this.direction){
		case North:
			return "\u21A5";
		case South:
			return "\u21A7";
		case West:
			return "\u21A4";
		case East:
			return "\u21A6";
			
		default:
			return "";
		}
	}

	public Position getPosition(){
		return this.position;
	}

	private void go(int distance) {
		Position newPosition;
		switch (this.direction) {
		case South:
			newPosition = this.position.add(new Position(0, -distance));
			break;

		case North:
			newPosition = this.position.add(new Position(0, distance));
			break;

		case West:
			newPosition = this.position.add(new Position(-distance, 0));
			break;

		case East:
			newPosition = this.position.add(new Position(distance, 0));
			break;

		default:
			return;
		}

		if (this.mapCar.canMoveTo(newPosition))
			this.position = newPosition;
	}

	public void move(MoveDirection carDirection) {
		switch (carDirection) {
		case Forward:
			this.go(1);
			break;

		case Backward:
			this.go(-1);
			break;

		case Left:
			this.direction = this.direction.previous();
			break;

		case Right:
			this.direction = this.direction.next();
			break;

		default:
			return;
		}
	}
}
