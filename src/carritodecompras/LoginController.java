/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritodecompras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginn;
    @FXML
    private Button btsal;

    @FXML
    private TextField usuario;
    @FXML
    private TextField contra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        if (usuario.getText().equals("Unicord") && contra.getText().equals("2023")) {
            // Create a new stage and call the openVentanaTabla method
            Stage stage = new Stage();
            openVentanaTabla(stage, event);
        }
    }

    @FXML
    public void openVentanaTabla(Stage stage, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaTabla.fxml"));
        Parent root = loader.load();
        VentanaTablaController controller = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);


        stage.initStyle(StageStyle.UNDECORATED);


        EventHandler<MouseEvent> mousePressed = ev -> {
            xOffset = ev.getSceneX();
            yOffset = ev.getSceneY();
        };
        EventHandler<MouseEvent> mouseDragged = ev -> {
            stage.setX(ev.getScreenX() - xOffset);
            stage.setY(ev.getScreenY() - yOffset);
        };
        scene.setOnMousePressed(mousePressed);
        scene.setOnMouseDragged(mouseDragged);

        // Show the stage
        stage.show();
    }
    private double xOffset;
    private double yOffset;
}
