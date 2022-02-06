package com.leonardoholanda.minefield.exception;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leonardoholanda.minefield.model.SquareMeter;

public class SquareMeterTest {

	private SquareMeter sqm;
	
	@BeforeEach
	void initSquareMeter() {
		 sqm = new SquareMeter(3,3);
	}
	
	@Test
	void testNeighborIsRealDistance1Left() {
		SquareMeter neighbor = new SquareMeter(3, 2);
		boolean result = sqm.addNeighbor(neighbor); 
		assertTrue(result);
	}
	
	@Test
	void testNeighborIsRealDistance1Right() {
		SquareMeter neighbor = new SquareMeter(3, 4);
		boolean result = sqm.addNeighbor(neighbor); 
		assertTrue(result);
	}
	
	@Test
	void testNeighborIsRealDistance1Top() {
		SquareMeter neighbor = new SquareMeter(2, 3);
		boolean result = sqm.addNeighbor(neighbor); 
		assertTrue(result);
	}
	
	@Test
	void testNeighborIsRealDistance1Bottom() {
		SquareMeter neighbor = new SquareMeter(4, 3);
		boolean result = sqm.addNeighbor(neighbor); 
		assertTrue(result);
	}
	
	@Test
	void testNeighborIsRealDistance2() {
		SquareMeter neighbor = new SquareMeter(2, 2);
		boolean result = sqm.addNeighbor(neighbor); 
		assertTrue(result);
	}
	
	@Test
	void testNotNeighbor() {
		SquareMeter neighbor = new SquareMeter(1, 1);
		boolean result = sqm.addNeighbor(neighbor); 
		assertFalse(result);
	}
	
	@Test
	void testStandarValueForMark() {
		assertFalse(sqm.isMarked());
	}
	
	@Test
	void testChangeMark() {
		sqm.changeMark();
		assertTrue(sqm.isMarked());
	}
	
	@Test
	void testChangeMarkTwice() {
		sqm.changeMark();
		sqm.changeMark();
		assertFalse(sqm.isMarked());
	}
	
	@Test
	void testOpenNoMineNoMark() {
		assertTrue(sqm.open());
	}
	
	@Test
	void testOpenNoMineMarked() {
		sqm.changeMark();
		sqm.mine();
		assertFalse(sqm.open());
	}
	
	@Test
	void testOpenMineNoMark() { 
		sqm.mine();
		assertThrows(ExplosionException.class, () -> {
			sqm.open();
		});
	}
	
	@Test
	void testOpenMineMarked() {
		sqm.changeMark();
		sqm.mine();
		assertFalse(sqm.open());
	}
	
	@Test
	void testOpenWithNeighbors() {
		SquareMeter sqm2 = new SquareMeter(2,2);
		SquareMeter sqm3 = new SquareMeter(1,1);
		SquareMeter sqm4 = new SquareMeter(1,2);

		sqm2.addNeighbor(sqm3);
		sqm2.addNeighbor(sqm4);
		sqm.addNeighbor(sqm2);
		
		sqm4.mine();
		
		sqm.open();
			
		assertTrue(sqm2.isOpen() && !sqm3.isOpen());
	}
}
