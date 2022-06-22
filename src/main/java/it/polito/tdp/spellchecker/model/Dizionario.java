package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dizionario {

	//Ogni istanza della classe Dizionario corrisponde ad un possibile dizionario
	//di riferimento per il controllo ortografico. 

	
	//ATTRIBUTI: 
	private List<String> dizionario; //iniseme di parole dizionario
	private String lingua;			 //lingua dizionario

	
	//NO COSTRUTTORE:
	//L'inizializzazione del dizionario avviene 
	//direttamente nel metodo loadDictionary.	
	
	
	//METODI: 
	
	//Metodo per caricare in memoria il dizionario della lingua desiderata,
	//partendo dai file a disposizione English.txt e Italian.txt:
	public boolean loadDictionary(String language) {
		
		//inizializzazione dizionario
		dizionario = new LinkedList<String>(); 
		lingua = language;
		
		//popolamento dizionario
		try {

			FileReader f = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader b = new BufferedReader(f);
		
			String word;

			while ((word=b.readLine())!= null) {
				dizionario.add(word.toLowerCase());
			}

			//Collections.sort(dizionario);
		
			return true; 
			
			
		} catch (IOException e) {
			System.err.println("Errore nella lettura del file");
			return false; 
		}
		
	}	
	
	
	//Metodo per l'esecuzione del controllo ortografico:
	public List<Parola> spellCeckTest(List<String> inputTextList){
		
		ArrayList<Parola> verifiedTextList = new ArrayList<Parola>();
		
		for (String s : inputTextList) {

			Parola pTemp = null;
			pTemp.setParola(s); 
			
			if (dizionario.contains(s.toLowerCase()))
				pTemp.setOrtografia(true);
			 
			else 
				pTemp.setOrtografia(false);
			
			verifiedTextList.add(pTemp);
		}

		return verifiedTextList;
		
	}

	
	
}
