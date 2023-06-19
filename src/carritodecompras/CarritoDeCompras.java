/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritodecompras;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jesus
 */
public class CarritoDeCompras extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Crear una nueva ventana de inicio de sesión
        Stage loginStage = new Stage();

        // Establecer el estilo de la ventana de inicio de sesión como "sin decoración"
        loginStage.initStyle(StageStyle.UNDECORATED);

        // Establecer la ventana de inicio de sesión como no redimensionable
        loginStage.setResizable(false);

        // Establecer el título de la ventana de inicio de sesión
        loginStage.setTitle("Login");

        // Cargar el archivo Login.fxml
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));

        // Crear una nueva escena para la raíz de inicio de sesión
        Scene loginScene = new Scene(loginRoot);

        // Establecer la escena de inicio de sesión como la escena de la ventana de inicio de sesión
        loginStage.setScene(loginScene);

        // Mostrar la ventana de inicio de sesión
        loginStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
