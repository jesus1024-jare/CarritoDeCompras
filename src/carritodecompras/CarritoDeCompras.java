/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritodecompras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jesus
 */
public class CarritoDeCompras extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VentanaTabla.fxml"));
        
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            // Guardar las coordenadas de la posición actual de la ventana
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // Agregar un evento onMouseDragged al contenido de la ventana
        root.setOnMouseDragged(event -> {
            // Mover la ventana mediante la posición del mouse
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            
        });
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private double xOffset = 0;
    private double yOffset = 0;    
}
