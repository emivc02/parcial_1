package co.edu.uniquindio.poo.parcial;

import co.edu.uniquindio.poo.parcial.model.gimnasio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DatosIniciales.cargar();

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("view/MainView.fxml"));
        Scene scene = new Scene(loader.load(), 1320, 760);
        scene.getStylesheets().add(HelloApplication.class.getResource("view/estilos.css").toExternalForm());

        stage.setTitle("SmartGym - Sistema de gestión");
        stage.setMinWidth(1000);
        stage.setMinHeight(640);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

