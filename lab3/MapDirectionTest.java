package agh.cs.lab3;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapDirectionTest {

	@Test
	public void testNext() {
		assertEquals(MapDirection.East, MapDirection.North.next());
		assertEquals(MapDirection.West, MapDirection.South.next());
		assertEquals(MapDirection.North, MapDirection.West.next());
		assertEquals(MapDirection.South, MapDirection.East.next());
	}
	
	@Test
	public void testPrevious() {
		assertEquals(MapDirection.East, MapDirection.South.previous());
		assertEquals(MapDirection.West, MapDirection.North.previous());
		assertEquals(MapDirection.North, MapDirection.East.previous());
		assertEquals(MapDirection.South, MapDirection.West.previous());
	}

}
