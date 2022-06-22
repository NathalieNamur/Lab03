package it.polito.tdp.spellchecker.model;

public class Parola {
	
	//Ogni istanza della classe Parola rappresenta una parola del testo, 
	//di cui si vuole controllare l'ortografia.
	

	//ATTRIBUTI:
	private String parola; 		//termine
	private boolean ortografia;	//corretta/sbagliata
	

	//COSTRUTTORE:
	public Parola(String parola, boolean ortografia) {
		
		this.parola = parola;
		this.ortografia = ortografia;
	}
	
	
	//METODI:
	
	public String getParola() {
		return parola;
	}


	public void setParola(String s) {
		this.parola = s; 
	}
	
	public void setOrtografia(boolean o) {
		this.ortografia = o;
	}
	
	
	public boolean isCorretta() {
		return ortografia;
	}


	
}
