package com.leonardoholanda.minefield.model;

import com.leonardoholanda.minefield.view.MineFieldConsole;

public class Application {

	public static void main(String[] args) {
		
		int lines = 5, columns = 5, mines = 5; 
		
		Field mineField = new Field(lines, columns, mines);  
		MineFieldConsole mfc = new MineFieldConsole(mineField);
	}
}
