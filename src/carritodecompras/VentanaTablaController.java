/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritodecompras;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author jesus
 */
public class VentanaTablaController implements Initializable {
//ACTUALIZA LA HIJOEPUTA TABLA O SINO EL PROGRAMA NO CORRE!!!!!
//CLICK DERECHO EN VENTANATABLA.FXML Y MAKE CONTROLLER

    nodo cab;
    private ObservableList<nodo> nodoo;
    @FXML
    private MenuItem BotonBuscar;
    @FXML
    private Button btnSalida;
    @FXML
    private Button btminimizar;
    @FXML
    private Button btAgregarAlInicio;
    @FXML
    private RadioMenuItem Muni;

    public VentanaTablaController() {
        cab = null;
    }
    @FXML
    private TableView<nodo> tablaauto;
    @FXML
    private TableColumn<nodo, String> columnamodelo;
    @FXML
    private TableColumn<nodo, String> columnamarca;
    @FXML
    private TableColumn<nodo, String> columnamatricula;
    @FXML
    private TableColumn<nodo, Float> columnaprecio;
    @FXML
    private TableColumn<nodo, Integer> columnaunidades;
    @FXML
    private TextField txtmod;
    @FXML
    private TextField txtmar;
    @FXML
    private TextField txtmatri;
    @FXML
    private TextField txtpreci;
    @FXML
    private TextField txtunid;
    @FXML
    private Button btaña;
    @FXML
    private Button btelimi;

    private ObservableList<nodo> nodos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File file = new File("C:/Prueba/prueba.txt");

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                nodo car = new nodo(line[0], line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]));
                nodos.add(car);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se pudo encontrar.");
        }

        columnamodelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        columnamodelo.setStyle("-fx-alignment: CENTER-LEFT");
        columnamarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnamarca.setStyle("-fx-alignment: CENTER-LEFT");
        columnamatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        columnamatricula.setStyle("-fx-alignment: CENTER-LEFT");
        columnaprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaprecio.setStyle("-fx-alignment: CENTER-LEFT");
        columnaunidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        columnaunidades.setStyle("-fx-alignment: CENTER-LEFT");

        // Make nodos list circular
        int size = nodos.size();
        for (int i = 0; i < size; i++) {
            int prevIndex = i == 0 ? size - 1 : i - 1;
            int nextIndex = i == size - 1 ? 0 : i + 1;
            nodos.get(i).setAnt(nodos.get(prevIndex));
            nodos.get(i).setSig(nodos.get(nextIndex));
        }

        tablaauto.setItems(nodos);

    }

    @FXML
    private void btnagregar(ActionEvent event) {
        if (!"".equals(txtmod.getText().trim())) {
            String matricula = txtmatri.getText().trim();
            boolean duplicada = false;
            for (nodo temp : nodos) {
                if (temp.getMatricula().equals(matricula)) {
                    duplicada = true;
                    break;
                }
            }
            if (duplicada) {
                // mostrar mensaje de matrícula duplicada
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Matrícula duplicada");
                alert.setContentText("La matrícula ya existe en la lista.");
                txtmod.setText("");
                txtmar.setText("");
                txtmatri.setText("");
                txtpreci.setText("");
                txtunid.setText("");
                alert.showAndWait();
            } else {
                nodo nuevo = new nodo(txtmod.getText().trim(), txtmar.getText().trim(), matricula, Float.parseFloat(txtpreci.getText().trim()),
                        Integer.parseInt(txtunid.getText().trim()));
                Alert alertas = new Alert(AlertType.INFORMATION);
                alertas.setTitle("Proceso Exitoso");
                alertas.setContentText("Nodo agregado al final de la lista");
                alertas.showAndWait();
                if (nodos.isEmpty()) {
                    nuevo.setAnt(nuevo);
                    nuevo.setSig(nuevo);
                } else {
                    nodo ultimo = nodos.get(nodos.size() - 1);
                    nuevo.setAnt(ultimo);
                    nuevo.setSig(nodos.get(0));
                    ultimo.setSig(nuevo);
                    nodos.get(0).setAnt(nuevo);
                }
                nodos.add(nuevo);
                tablaauto.setItems(nodos);
                tablaauto.refresh();
                txtmod.setText("");
                txtmar.setText("");
                txtmatri.setText("");
                txtpreci.setText("");
                txtunid.setText("");
            }
        } else {
            // mostrar mensaje de advertencia sobre campos vacíos
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Mensaje de informacion");
            alerta.setTitle("Dialogo de advertencia");
            alerta.setContentText("Es necesario escribir todos los datos");
            txtmod.setText("");
            txtmar.setText("");
            txtmatri.setText("");
            txtpreci.setText("");
            txtunid.setText("");
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnagregaralinicio(ActionEvent event) {
        if (!"".equals(txtmod.getText().trim())) {
            nodo nuevo = new nodo(txtmod.getText().trim(), txtmar.getText().trim(), txtmatri.getText().trim(),
                    Float.parseFloat(txtpreci.getText().trim()), Integer.parseInt(txtunid.getText().trim()));
            Alert alertas = new Alert(AlertType.INFORMATION);
            alertas.setTitle("Proceso Exitoso");
            alertas.setContentText("Nodo agregado al inicio de la lista");
            alertas.showAndWait();
            if (nodos.isEmpty()) {
                nuevo.setSig(nuevo); // El único nodo apunta a sí mismo
            } else {
                nuevo.setSig(nodos.get(0)); // El nuevo nodo apunta al primer nodo actual
                nodos.get(nodos.size() - 1).setSig(nuevo); // El último nodo actual apunta al nuevo nodo
            }
            nodos.add(0, nuevo);
            tablaauto.setItems(nodos);
            tablaauto.refresh();
            txtmod.setText("");
            txtmar.setText("");
            txtmatri.setText("");
            txtpreci.setText("");
            txtunid.setText("");
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Mensaje de informacion");
            alerta.setTitle("Dialogo de advertencia");
            alerta.setContentText("Es necesario escribir todos los datos");
            tablaauto.setItems(nodos);
            tablaauto.refresh();
            txtmod.setText("");
            txtmar.setText("");
            txtmatri.setText("");
            txtpreci.setText("");
            txtunid.setText("");
            alerta.showAndWait();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void mouseExited(javafx.scene.input.MouseEvent event) {
        Scene scene = btaña.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.DEFAULT);
        }
    }

    @FXML
    private void mouseSale(javafx.scene.input.MouseEvent event) {
        Scene scene = btAgregarAlInicio.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.DEFAULT);
        }
    }

    @FXML
    private void mouseEntra(javafx.scene.input.MouseEvent event) {
        Scene scene = btAgregarAlInicio.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.HAND);
        }
    }

    @FXML
    private void mouseEntered(javafx.scene.input.MouseEvent event) {
        Scene scene = btaña.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.HAND);
        }
    }

    @FXML
    private void mouseSalida(javafx.scene.input.MouseEvent event) {
        Scene scene = btelimi.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.DEFAULT);
        }
    }

    @FXML
    private void mouseEntrar(javafx.scene.input.MouseEvent event) {
        Scene scene = btelimi.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.HAND);
        }
    }

    @FXML
    private void mouseS(javafx.scene.input.MouseEvent event) {
        Scene scene = btnSalida.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.DEFAULT);
        }
    }

    @FXML
    private void mouseE(javafx.scene.input.MouseEvent event) {
        Scene scene = btnSalida.getScene();
        if (scene != null) {
            scene.setCursor(Cursor.HAND);
        }
    }

    @FXML
    private void minimizeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnbuscar(ActionEvent event) {
        TextInputDialog dialogo = new TextInputDialog("");
        dialogo.setTitle("Buscar automóvil");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese la matrícula:");

        Optional<String> matricula = dialogo.showAndWait();
        if (matricula.isPresent()) {
            if (nodos.isEmpty()) {
                // mostrar mensaje de advertencia si la lista está vacía
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText("Lista vacía");
                alerta.setContentText("No hay automóviles en la lista.");
                alerta.showAndWait();
                return;
            }
            nodo actual = nodos.get(0);
            do {
                if (actual.getMatricula().equals(matricula.get())) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText("Información del automóvil");
                    alerta.setTitle("Automóvil encontrado");
                    alerta.setContentText("Modelo: " + actual.getModelo()
                            + "\nMarca: " + actual.getMarca()
                            + "\nPrecio: $" + actual.getPrecio() + " dolares"
                            + "\nUnidades: " + actual.getUnidades());
                    alerta.showAndWait();
                    return; // salir del método si se encuentra la matrícula
                }
                actual = actual.getSig();
            } while (actual != nodos.get(0)); // seguir mientras no se regrese al primer nodo
            // mostrar mensaje de advertencia si no se encuentra la matrícula
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Matrícula no encontrada");
            alerta.setContentText("No se encontró ningún automóvil con la matrícula ingresada.");
            alerta.showAndWait();
        }
    }

    @FXML
    public void compra(ActionEvent event) {
        // Obtener el automóvil seleccionado en la tabla
        nodo auto = tablaauto.getSelectionModel().getSelectedItem();
        // Verificar que se ha seleccionado un automóvil
        if (auto == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("No se ha seleccionado ningún automóvil");
            alerta.setContentText("Seleccione un automóvil de la tabla para comprar.");
            alerta.showAndWait();
            return;
        }
        // Crear un diálogo para obtener la cantidad de unidades a comprar
        TextInputDialog dialogo = new TextInputDialog("");
        dialogo.setTitle("Cantidad a comprar");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese la cantidad a comprar:");
        Optional<String> cantidad = dialogo.showAndWait();
        // Verificar que la entrada del usuario para la cantidad de unidades sea un número entero positivo
        if (!cantidad.isPresent()) {
            return; // El usuario ha cerrado el diálogo 
        } else if (!cantidad.get().matches("^[1-9]\\d*$")) { // La entrada no es un número entero positivo
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Entrada inválida");
            alerta.setContentText("La cantidad de unidades debe ser un número entero positivo.");
            alerta.showAndWait();
            return;
        }
        int cantidadComprar = Integer.parseInt(cantidad.get());
        // Verificar si hay suficientes unidades disponibles para la compra

        if (cantidadComprar > auto.getUnidades()) {
            Alert ale = new Alert(Alert.AlertType.INFORMATION);
            ale.setHeaderText("Informacion");
            ale.setContentText("La cantidad de unidades no está disponible");
            ale.showAndWait();
            return;
        }
        if (auto.getUnidades() > 0) {
            auto.setUnidades(auto.getUnidades() - cantidadComprar);
            nodo siguiente = auto.getSig();
            nodo anterior = auto.getAnt();
            siguiente.setAnt(anterior);
            anterior.setSig(siguiente);
            tablaauto.setItems(null);
            tablaauto.layout();
            tablaauto.setItems(FXCollections.observableList(nodos));
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Deseas comprar el auto " + auto.getModelo() + "?");
            alerta.setContentText("El precio del auto es: " + auto.getPrecio() + "\nEl total a pagar es: " + (auto.getPrecio() * cantidadComprar));
            Alert notify = new Alert(Alert.AlertType.INFORMATION);
            notify.setTitle("Proceso Exitoso!");
            notify.setHeaderText("Cargando informacion...");
            notify.setContentText("Pago Realizado Correctamente!");
            alerta.showAndWait();
            notify.show();
            if (auto.getUnidades() <= 0) {
                auto.setUnidades(auto.getUnidades() - cantidadComprar);
                siguiente.setAnt(anterior);
                anterior.setSig(siguiente);
                nodos.remove(auto);
                tablaauto.setItems(null);
                tablaauto.layout();
                tablaauto.setItems(FXCollections.observableList(nodos));
                Alert ale = new Alert(Alert.AlertType.INFORMATION);
                ale.setHeaderText("Informacion");
                ale.setContentText("Ya no quedan unidades disponibles");
                ale.showAndWait();
                return;
            }
        }
    }

    private nodo buscarNodoAnterior(nodo nodoActual) {
        nodo actual = cab;
        do {
            if (actual.getSig() == nodoActual) {
                return actual;
            }
            actual = actual.getSig();
        } while (actual != cab);
        return null; // El nodo actual no se encontró en la lista
    }

    @FXML
    public void Mostrar(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje de Alerta");
        alert.setHeaderText("Listado    ");
        alert.setContentText("");

        alert.showAndWait();
    }

    @FXML
    private void Unidades(ActionEvent event) {
        TextInputDialog dialogo = new TextInputDialog("");
        dialogo.setTitle("Buscar automóvil");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese la matrícula:");

        Optional<String> matricula = dialogo.showAndWait();
        if (matricula.isPresent()) {
            if (nodos.isEmpty()) {
                // mostrar mensaje de advertencia si la lista está vacía
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText("Lista vacía");
                alerta.setContentText("No hay automóviles en la lista.");
                alerta.showAndWait();
                return;
            }
            nodo actual = nodos.get(0);
            do {
                if (actual.getMatricula().equals(matricula.get())) {
                    // Mostrar diálogo para ingresar el número de unidades
                    TextInputDialog dialogo2 = new TextInputDialog("");
                    dialogo2.setHeaderText("Automóvil encontrado");
                    dialogo2.setContentText("Ingrese numero de unidades nueva:");

                    Optional<String> unidades = dialogo2.showAndWait();

                    // Si se ingresó un número de unidades, actualizar el objeto y mostrar mensaje
                    if (unidades.isPresent()) {
                        int nuevasUnidades = Integer.parseInt(unidades.get());
                        actual.setUnidades(nuevasUnidades);
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setHeaderText("Proceso Exitoso!");
                        alerta.setTitle("Cargando informacion...");
                        alerta.setContentText("Unidades registradas correctamente");
                        alerta.showAndWait();
                        tablaauto.setItems(null);
                        tablaauto.layout();
                        tablaauto.setItems(FXCollections.observableList(nodos));
                    }
                    return; // salir del método si se encuentra la matrícula
                }
                actual = actual.getSig();
            } while (actual != nodos.get(0)); // seguir mientras no se regrese al primer nodo
            // mostrar mensaje de advertencia si no se encuentra la matrícula
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Matrícula no encontrada");
            alerta.setContentText("No se encontró ningún automóvil con lamatrícula ingresada.");
        }
    }
}
