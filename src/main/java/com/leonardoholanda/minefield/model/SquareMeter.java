package com.leonardoholanda.minefield.model;

import java.util.ArrayList;
import java.util.List;

import com.leonardoholanda.minefield.exception.ExplosionException;

public class SquareMeter {

	private final int line;
	private final int column;
	
	private boolean isOpen;
	private boolean hasMine;
	private boolean isMarked;
	
	private List<SquareMeter> neighbors = new ArrayList<SquareMeter>();
	
	public void changeMark () {
		if(!isOpen) {
			isMarked = !isMarked;
		}
	}
	
	public boolean open() {
		
		if(!isOpen&&!isMarked) {
			isOpen = true;
			
			if(hasMine) {
				throw new ExplosionException();
			} 
			
			if(safeNeighborhood()) {
				neighbors.forEach(n -> n.open());
			}
			
			return true;
		} else 
			return false;
	}
	
	boolean goalReached() {
		boolean opened = !this.hasMine() && this.isOpen();
		boolean prot = this.hasMine() && this.isMarked();
		return opened || prot;
	}
	
	long minesInTheNeighborhood () {
		return neighbors.stream().filter(n -> n.hasMine()).count();
	}
	
	void reset() {
		this.isOpen = false;
		this.isMarked = false;
		this.hasMine = false;
	}
	
	boolean safeNeighborhood() {
		return neighbors.stream().noneMatch(n -> n.hasMine);
	}
	
	public boolean hasMine() {
		return hasMine;
	}
	
	public void mine() {
		this.hasMine = true;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public SquareMeter(int line, int column) {
		super();
		this.line = line;
		this.column = column; 
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	} 
	
	public boolean addNeighbor (SquareMeter neighbor) {
		boolean diferentLine = this.line != neighbor.getLine();
		boolean diferentColumn = this.column != neighbor.getColumn();
		boolean diagonal = diferentLine && diferentColumn;
		
		int deltaLine = Math.abs(this.line - neighbor.getLine());
		int deltaColumn = Math.abs(this.column - neighbor.getColumn());
		int deltaGeneral = deltaLine + deltaColumn;
		
		if(deltaGeneral == 1 && !diagonal) {
			this.neighbors.add(neighbor);
			return true;
		} else if (deltaGeneral == 2 && diagonal) {
			this.neighbors.add(neighbor);
			return true;
		} else {
			return false;
		} 
	}
	
	public String toString() {
		if (this.isMarked) {
			return "X";
		} else if (this.isOpen() && this.hasMine()) {
			return "*";
		} else if (this.isOpen() && this.minesInTheNeighborhood() > 0){
			return Long.toString(this.minesInTheNeighborhood());
		} else if (this.isOpen) {
			return " ";
		} else {
			return "?";
		}
	}
}
