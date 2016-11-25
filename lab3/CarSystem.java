package agh.cs.lab3;

import agh.cs.lab5.HayStack;
import agh.cs.lab5.UnboundedMap;

import java.util.ArrayList;
import java.util.List;

public class CarSystem {

	public static void main(String[] args) {

		try {
			MoveDirection[] movdir = new OptionParser().parse(args);

			List<HayStack> stackList = new ArrayList<>();

			stackList.add(new HayStack(new Position(-4, -4)));
			stackList.add(new HayStack(new Position(7, 7)));
			stackList.add(new HayStack(new Position(3, 6)));
			stackList.add(new HayStack(new Position(2, 0)));

			UnboundedMap map = new UnboundedMap(stackList);
			map.add(new Car(map));
			map.add(new Car(map, 3, 4));
			map.run(movdir);
			System.out.println(map);

		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
		}
	}
}
