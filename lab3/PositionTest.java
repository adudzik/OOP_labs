package agh.cs.lab3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testToSting() {
		assertEquals("(1, 2)",new Position(1,2).toString());
	}
	
	@Test
	public void testSmaller(){
		assertTrue(new Position(1,2).smaller(new Position(2,4)));
		assertFalse(new Position(1,2).smaller(new Position(1,1)));
	}
	
	@Test
	public void testLarger(){
		assertTrue(new Position(2,4).larger(new Position(1,2)));
		assertFalse(new Position(1,2).larger(new Position(1,5)));
	}
	
	@Test
	public void testAdd(){
		assertEquals(new Position(3, 6), new Position(1, 1).add(new Position(2, 5)));
		assertNotEquals(new Position(2, 4), new Position(1, 1).add(new Position(2, 5)));
	}

}
