
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private Button btnStati;
    
    @FXML
    private ComboBox<String> cmbStati;
    
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	String input = txtAnno.getText();
    	int num = 0;
    	try {
    	num = Integer.parseInt(input);
    	if(num < 1816) {
    		txtResult.setText("I numeri minori di 1816 non sono accettati");
    	}else if(num > 2016) {
    		txtResult.setText("I numeri maggiori di 2016 non sono accettati");
    	}else {
    		int archi =  model.buildGraph(num);
    		HashMap<String, Integer> valori = model.calcolaConfini();
    		for(String s: valori.keySet()) {
    			txtResult.appendText(s + ": " + valori.get(s) + "\n");
    		}
    		txtResult.appendText("Il grafo è composto da " + archi + " componenti connesse");
    	}
    	}catch(NumberFormatException e ) {
    		txtResult.setText("Numero inserito in modo scorretto");
    	}
    }
    @FXML
    void Calcola(ActionEvent event) {
    	txtResult.clear();
    	String input = txtAnno.getText();
    	String stato = cmbStati.getValue().toString();
    	int num = 0;
    	try {
    	num = Integer.parseInt(input);
    	if(num < 1816) {
    		txtResult.setText("I numeri minori di 1816 non sono accettati");
    	}else if(num > 2016) {
    		txtResult.setText("I numeri maggiori di 2016 non sono accettati");
    	}else {
    		model.buildGraph(num);
    		ArrayList<Country> valori = model.connessi(stato);
    		for(Country c: valori) {
    			txtResult.appendText(c.getNome() + "\n");
    		}
    		txtResult.appendText("Trovate " + valori.size() + " componenti connesse");
    	}
    	}catch(NumberFormatException e ) {
    		txtResult.setText("Numero inserito in modo scorretto");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
        cmbStati.getItems().addAll(model.getAbb());

    }
}
