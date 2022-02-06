package com.leonardoholanda.minefield.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Field {

	public int lines;
	public int columns;
	public int mines;
	
	private final List<SquareMeter> sqms = new ArrayList<>();

	public Field(int lines, int columns, int mines) { 
		this.lines = lines;
		this.columns = columns;
		this.mines = mines;
		
		generateFields();
		meetNeighbors();
		randomMines();
		
	}

	private void meetNeighbors() { 
		for (SquareMeter sqm1: sqms) {
			for (SquareMeter sqm2: sqms) {
				sqm1.addNeighbor(sqm2);
			}
		}
	}

	private void randomMines() { 
		int minesArmed = 0;
		Predicate<SquareMeter> mined = sqm -> sqm.hasMine();
		
		do {
			minesArmed = (int) sqms.stream().filter(mined).count();
			int random = (int)(Math.random() * sqms.size());
			sqms.get(random).mine();
		} while (minesArmed < this.mines);
	}

	private void generateFields() { 
		for(int line=0;line<lines;line++) {
			for(int column=0;column<columns;column++) {
				sqms.add(new SquareMeter(line,column));
			}
		}
	} 
	
	public boolean goalReached() {
		return sqms.stream().allMatch(sqm -> sqm.goalReached());
	}
	
	public void reset() {
		sqms.stream().forEach(sqm -> sqm.reset());
		this.randomMines();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		int i = 0; 
		for (int l = 0; l < lines; l++) {
			for (int c = 0; c < columns; c++) {
				sb.append(" ");
				sb.append(sqms.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public void open(int line, int column) {
		sqms.parallelStream()
			.filter(sqm -> sqm.getLine() == line)
			.filter(sqm -> sqm.getColumn() == column)
			.findFirst()
			.ifPresent(sqm -> sqm.open());
	}
	
	public void mark(int line, int column) {
		sqms.parallelStream()
			.filter(sqm -> sqm.getLine() == line)
			.filter(sqm -> sqm.getColumn() == column)
			.findFirst()
			.ifPresent(sqm -> sqm.changeMark());
	}
}
