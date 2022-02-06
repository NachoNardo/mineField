package com.leonardoholanda.minefield.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import com.leonardoholanda.minefield.exception.ExitException;
import com.leonardoholanda.minefield.exception.ExplosionException;
import com.leonardoholanda.minefield.model.Field;

public class MineFieldConsole {

	private Field mineField;
	private Scanner sc = new Scanner(System.in);
	
	public MineFieldConsole (Field mineField) {
		this.mineField = mineField; 
		
		executeGame();
	}

	private void executeGame() { 
		try {
			boolean contin = true;
			
			while(contin) {
				
				gameCicle();
				
				System.out.println("Another match? [y] - Yes [n] - No");
				String answer = sc.next();
				
				if("n".equalsIgnoreCase(answer)) {
					contin = false;
				} else {
					mineField.reset();
				}
			}
		} catch (ExitException e) {
			System.out.println("See you next time!!!");
		} finally {
			sc.close();
		}
	}

	private void gameCicle() {
		
		try {
			
			while(!mineField.goalReached()) {
				System.out.println(mineField);
				String inserted = captureInsertedValue("Enter (line,column): ");
				
				Iterator<Integer> lc = Arrays.stream(inserted.split(","))
					.map(e -> Integer.parseInt(e.trim()))
					.iterator();
				
				inserted = captureInsertedValue("1 - Open 2 - (Un)mark: ");
				
				if("1".equals(inserted)) {
					mineField.open(lc.next(), lc.next());
				} else if ("2".equals(inserted)) {
					mineField.mark(lc.next(), lc.next());
				}
				
				
			}
			System.out.println(mineField);
			System.out.println("Congratulations! You Won!");
		} catch (ExplosionException e) {
			System.out.println(mineField);
			System.out.println("Sorry... You lose...");
		}
		
	}
	
	
	public String captureInsertedValue (String text) {
		System.out.println(text);
		String inserted = sc.nextLine();
		
		if("exit".equalsIgnoreCase(inserted)) {
			throw new ExitException();
		}
		
		return inserted;
	}
}
