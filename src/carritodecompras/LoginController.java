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
import javafx.scene.control.Alert;
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
            // Crear una nueva ventana e invocar el método openVentanaTabla
            Stage stage = new Stage();
            openVentanaTabla(stage, event);
        }else if ((usuario.getText() == null ? ("Unicord") != null : !usuario.getText().equals("Unicord")) && contra.getText()!=("2023")){
            // Mostrar un cuadro de diálogo de alerta si los datos de inicio de sesión son incorrectos
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Datos Erroneos");
            alerta.setHeaderText("Vuelva a ingresar los datos");
            alerta.setContentText("Los datos ingresados no son validos");
            alerta.showAndWait();
        }
    }

    public void openVentanaTabla(Stage stage, ActionEvent event) throws IOException {

       // Crear un nuevo cargador de FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaTabla.fxml"));

        // Cargar el archivo VentanaTabla.fxml y asignarlo como la raíz de la ventana
        Parent root = loader.load();

        // Obtener el controlador de la ventana cargada
        VentanaTablaController controller = loader.getController();

        // Crear una nueva escena con la raíz cargada
        Scene scene = new Scene(root);

        // Establecer la nueva escena como la escena de la ventana
        stage.setScene(scene);

        // Establecer el estilo de la ventana como "sin decoración"
        stage.initStyle(StageStyle.UNDECORATED);

        // Permitir que la ventana se pueda arrastrar para moverla por la pantalla
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

        // Mostrar la ventana
        stage.show();
    }
    private double xOffset;
    private double yOffset;
}
