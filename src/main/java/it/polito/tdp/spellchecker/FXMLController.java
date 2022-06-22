package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dizionario;
import it.polito.tdp.spellchecker.model.Parola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;



public class FXMLController {

	//ATTRIBUTO DI RIFERIMENTO AL MODEL:
	private Dizionario modelDizionario;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private Button btnCancella;

    @FXML
    private Button btnCorreggi;

    @FXML
    private Label lblErrori;

    @FXML
    private Label lblEsecuzione;

    @FXML
    private TextArea txtCorretto;

    @FXML
    private TextArea txtDaCorreggere;

    
    
    @FXML
    void doActivation(ActionEvent event) {
    	
    	//NB:
    	//Nella soluzione tale metodo viene utilizzato per abilitare e 
    	//disabilitare caselle di testo e bottoni a seconda di se sia
    	//stata o meno scelta la lingua.
    	
    	//IMPORTANTE?!! UTILE?!!
    	//COSI' IL PROGRAMMA GIRA PERFETTAMENTE MA NON E' POSSIBILE 
    	//ELIMINARE DEL TUTTO IL METODO, ALTRIMENTI NON GIRA PIU'.
    }
    
    
    @FXML
    void doCancella(ActionEvent event) {
    	
    	boxLingua.setValue("");
    	
    	txtDaCorreggere.clear();
		txtCorretto.clear();
	
		lblErrori.setText("Numero di errori effettuati: -");
		lblEsecuzione.setText("Tempo di esercuzione: -");
    }

    
    @FXML
    void doCorreggi(ActionEvent event) {
    
    	//Caricare il dizionario corrispondente alla lingua selezionata:
    	
    	if (!modelDizionario.loadDictionary(boxLingua.getValue())) {
			txtDaCorreggere.setText("ERRORE nel caricamento del dizionario!");
			return;
		}


    	//Trasformare il testo inserito in modo che sia analizzabile,
    	//il testo inserito deve essere trasformato in un elenco di parole:
    	
    	String inputText = txtDaCorreggere.getText();		//stringa contente tutto il testo
    	List<String> inputTextList = new LinkedList<>();	//lista delle singole parole del testo
    	
    	if (inputText.isEmpty()) {
			txtDaCorreggere.setText("ERRORE: Inserire un testo da correggere!");
			return;
		}

    	inputText = inputText.toLowerCase();
		inputText = inputText.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
		inputText = inputText.replaceAll("\n", " ");
		
		StringTokenizer st = new StringTokenizer(inputText, " ");
		while (st.hasMoreTokens()) {
			inputTextList.add(st.nextToken());
		}

		
		//Contare il tempo necessario all'esecuzione 
		//del controllo ortografico:
		
		long start = System.nanoTime();
		
		List<Parola> verifiedTextList = modelDizionario.spellCeckTest(inputTextList);
		
		long end = System.nanoTime();


		//Individuare gli errori commessi:
		
		String wrongWord = "";
		int numErrori = 0;
	
		for (Parola p : verifiedTextList) 
			if (!p.isCorretta()) {
				numErrori++;
				wrongWord += p.getParola()+"\n"; 
			}
		

		//Stampare i risultati: 
		
		txtCorretto.setText(wrongWord);
		
		lblEsecuzione.setText("Tempo di esercuzione: " + (end - start) / 1E9 + " sec");
		lblErrori.setText("Numero di errori effettuati: "+numErrori);


    }

    
    
    //METODO setModel():
    public void setModel(Dizionario model) {
		
    	//PER ASSOCIARE IL MODEL ALL'ATTRIBUTO DI RIFERIMENTO:
    	this.modelDizionario = model;
		
    	//Per inizializzare il contenuto della comboBox:
		boxLingua.getItems().addAll("English","Italian");
	}
    
    
    
    @FXML
    void initialize() {
        assert btnCancella != null : "fx:id=\"btnCancella\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorreggi != null : "fx:id=\"btnCorreggi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretto != null : "fx:id=\"txtCorretto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDaCorreggere != null : "fx:id=\"txtInserito\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
