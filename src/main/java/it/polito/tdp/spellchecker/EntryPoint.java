package it.polito.tdp.spellchecker;

import javafx.application.Application;
import static javafx.application.Application.launch;

import it.polito.tdp.spellchecker.model.Dizionario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
    	//MODEL:
    	Dizionario model = new Dizionario(); 
    	
    	//LOADER e CONTROLLER:
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
    	Parent root = loader.load();
        
    	FXMLController controller = loader.getController();
    	
    	//INVOCAZIONE DEL METODO setModel() DELLA CLASSE FXMLControlller:
    	controller.setModel(model); 
    	
    	
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
