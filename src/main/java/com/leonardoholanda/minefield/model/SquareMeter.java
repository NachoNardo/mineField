package com.leonardoholanda.minefield.model;

import java.util.ArrayList;
import java.util.List;

public class SquareMeter {

	private final int line;
	private final int column;
	
	private boolean isOpen;
	private boolean hasMine;
	private boolean isMarked;
	
	private List<SquareMeter> neighbors = new ArrayList<SquareMeter>();
	
	public boolean isHasMine() {
		return hasMine;
	}
	
	public void setHasMine(boolean hasMine) {
		this.hasMine = hasMine;
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
}
