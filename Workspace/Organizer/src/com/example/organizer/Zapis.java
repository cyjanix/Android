package com.example.organizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Zapis {
	File file;
	List<String> listaZadan = null;

	public Zapis(File file) {
		this.file = file;
	}

	public void ZapisPliku(StringBuffer zadanie) {
		PrintWriter zapis = null;
		try {
			zapis = new PrintWriter(file.toString());
		} catch (FileNotFoundException e) {
			// TODO
		}
		zapis.println(zadanie);
		zapis.close();
	}
}
