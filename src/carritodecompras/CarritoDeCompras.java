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
    Stage loginStage = new Stage();
    loginStage.initStyle(StageStyle.UNDECORATED);
    loginStage.setResizable(false);
    loginStage.setTitle("Login");
    Parent loginRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene loginScene = new Scene(loginRoot);
    loginStage.setScene(loginScene);
    loginStage.show();
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
