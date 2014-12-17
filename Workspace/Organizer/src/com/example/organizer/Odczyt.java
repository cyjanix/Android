package com.example.organizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Odczyt {

	private File file;
	private List<String> listaZadan = null;

	public Odczyt(File file) {
		this.file = file;
	}

	public List<String> odczytPliku() {
		
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO
		}
		while (in.nextLine() != null) {
			String dana = new String();
			dana = in.nextLine();
			listaZadan.add(dana);
		}
		in.close();
		return listaZadan;
	}
}