package com.leonardoholanda.minefield.model;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		
		int lines, columns, mines;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Number of lines: ");
		lines = sc.nextInt();
		System.out.println("Number of Columns: ");
		columns = sc.nextInt();
		System.out.println("Number of Mines: ");
		mines = sc.nextInt();
		
		Field mineField = new Field(lines, columns, mines);
		
		mineField.open(3, 3);
		mineField.mark(2, 5);
		mineField.mark(2, 7);
		
		
		System.out.println(mineField);
		
	}
}
