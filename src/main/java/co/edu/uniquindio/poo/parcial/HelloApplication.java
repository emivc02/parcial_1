package co.edu.uniquindio.poo.parcial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carga la vista desde la carpeta view donde la movimos por el MVC
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/co/edu/uniquindio/poo/parcial/view/smartgym-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("SmartGym - Sistema de Gestion");
        stage.setScene(scene);
        stage.show();
    }
}
