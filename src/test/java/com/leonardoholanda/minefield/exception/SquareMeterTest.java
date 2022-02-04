package com.leonardoholanda.minefield.exception;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
}
