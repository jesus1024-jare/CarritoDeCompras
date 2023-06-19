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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author jesus
 */
public class VentanaTablaController implements Initializable {
//ACTUALIZA LA TABLA O SINO EL PROGRAMA NO CORRE!!!!!
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
    private Button btnMaximizar;

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

    // Se crea una lista observable de tipo "nodo" utilizando FXCollections.observableArrayList()
    // La lista observable permite realizar un seguimiento automático de los cambios en la lista
    // y notificar a los componentes gráficos que dependen de ella sobre dichos cambios.
    private ObservableList<nodo> nodos = FXCollections.observableArrayList();

    //ArrayList es una implementación básica de una lista dinámica, 
    //ObservableArrayList es una implementación específica de JavaFX que agrega la funcionalidad de notificación de 
    //cambios para su uso en la interfaz gráfica.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Se crea un objeto File con la ruta del archivo "C:/Prueba/prueba.txt"
        File file = new File("C:/Prueba/prueba.txt");

        try {
            // Se crea un objeto Scanner para leer el contenido del archivo
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // Se lee una línea del archivo y se divide en partes utilizando la coma como separador
                String[] line = scanner.nextLine().split(",");
                // Se crea un objeto "nodo" utilizando los valores obtenidos de la línea
                nodo car = new nodo(line[0], line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]));
                // Se agrega el objeto "car" a la lista "nodos"
                nodos.add(car);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Si ocurre una excepción FileNotFoundException, se imprime un mensaje de error
            System.out.println("El archivo no se pudo encontrar.");
        }

        // Configuración de las propiedades de las columnas de la tabla
        // Se utiliza PropertyValueFactory para asignar el valor de los atributos de "nodo" a las celdas de la tabla
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

        // Hacer que la lista "nodos" sea circular
        int size = nodos.size();
        for (int i = 0; i < size; i++) {
            int prevIndex = i == 0 ? size - 1 : i - 1;
            int nextIndex = i == size - 1 ? 0 : i + 1;
            // Se establecen las referencias al nodo anterior y siguiente en cada nodo de la lista
            nodos.get(i).setAnt(nodos.get(prevIndex));
            nodos.get(i).setSig(nodos.get(nextIndex));
        }

        // Se asigna la lista "nodos" como origen de datos de la tabla "tablaauto"
        tablaauto.setItems(nodos);

    }

    @FXML
    private void btnagregar(ActionEvent event) {
        // Verificar si el campo de texto "txtmod" no está vacío
        if (!"".equals(txtmod.getText().trim())) {
            String matricula = txtmatri.getText().trim();
            boolean duplicada = false;

            // Comprobar si la matrícula ya existe en la lista
            for (nodo temp : nodos) {
                if (temp.getMatricula().equals(matricula)) {
                    duplicada = true;
                    break;
                }
            }

            if (duplicada) {
                // Mostrar mensaje de matrícula duplicada
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Matrícula duplicada");
                alert.setContentText("La matrícula ya existe en la lista.");
                // Limpiar campos de texto
                txtmod.setText("");
                txtmar.setText("");
                txtmatri.setText("");
                txtpreci.setText("");
                txtunid.setText("");
                alert.showAndWait();
            } else {
                // Crear un nuevo nodo con los datos ingresados en los campos de texto
                nodo nuevo = new nodo(txtmod.getText().trim(), txtmar.getText().trim(), matricula, Float.parseFloat(txtpreci.getText().trim()),
                        Integer.parseInt(txtunid.getText().trim()));

                // Mostrar mensaje de proceso exitoso
                Alert alertas = new Alert(AlertType.INFORMATION);
                alertas.setTitle("Proceso Exitoso");
                alertas.setContentText("Nodo agregado al final de la lista");
                alertas.showAndWait();

                if (nodos.isEmpty()) {
                    // Si la lista está vacía, hacer que el nuevo nodo sea su propio nodo anterior y siguiente
                    nuevo.setAnt(nuevo);
                    nuevo.setSig(nuevo);
                } else {
                    // Obtener el último nodo de la lista existente
                    nodo ultimo = nodos.get(nodos.size() - 1);
                    // Establecer las referencias del nuevo nodo y el último nodo
                    nuevo.setAnt(ultimo);
                    nuevo.setSig(nodos.get(0));
                    ultimo.setSig(nuevo);
                    nodos.get(0).setAnt(nuevo);
                }

                // Agregar el nuevo nodo a la lista
                nodos.add(nuevo);
                // Actualizar la tabla y limpiar los campos de texto
                tablaauto.setItems(nodos);
                tablaauto.refresh();
                txtmod.setText("");
                txtmar.setText("");
                txtmatri.setText("");
                txtpreci.setText("");
                txtunid.setText("");
            }
        } else {
            // Mostrar mensaje de advertencia sobre campos vacíos
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Mensaje de información");
            alerta.setTitle("Diálogo de advertencia");
            alerta.setContentText("Es necesario escribir todos los datos");
            // Limpiar campos de texto
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
        // Verificar si el campo de texto "txtmod" no está vacío
        if (!"".equals(txtmod.getText().trim())) {
            // Crear un nuevo nodo con los datos ingresados en los campos de texto
            nodo nuevo = new nodo(txtmod.getText().trim(), txtmar.getText().trim(), txtmatri.getText().trim(),
                    Float.parseFloat(txtpreci.getText().trim()), Integer.parseInt(txtunid.getText().trim()));

            // Mostrar mensaje de proceso exitoso
            Alert alertas = new Alert(AlertType.INFORMATION);
            alertas.setTitle("Proceso Exitoso");
            alertas.setContentText("Nodo agregado al inicio de la lista");
            alertas.showAndWait();

            if (nodos.isEmpty()) {
                // Si la lista está vacía, hacer que el nuevo nodo apunte a sí mismo
                nuevo.setSig(nuevo);
            } else {
                // Establecer las referencias del nuevo nodo y el primer nodo actual
                nuevo.setSig(nodos.get(0));
                // Establecer la referencia del último nodo actual al nuevo nodo
                nodos.get(nodos.size() - 1).setSig(nuevo);
            }

            // Agregar el nuevo nodo al inicio de la lista
            nodos.add(0, nuevo);
            // Actualizar la tabla y limpiar los campos de texto
            tablaauto.setItems(nodos);
            tablaauto.refresh();
            txtmod.setText("");
            txtmar.setText("");
            txtmatri.setText("");
            txtpreci.setText("");
            txtunid.setText("");
        } else {
            // Mostrar mensaje de advertencia sobre campos vacíos
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Mensaje de información");
            alerta.setTitle("Diálogo de advertencia");
            alerta.setContentText("Es necesario escribir todos los datos");
            // Actualizar la tabla y limpiar los campos de texto
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
        // Crear un cuadro de diálogo de entrada de texto para solicitar la matrícula al usuario
        TextInputDialog dialogo = new TextInputDialog("");
        dialogo.setTitle("Buscar automóvil");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese la matrícula:");

        // Mostrar el cuadro de diálogo y obtener la matrícula ingresada por el usuario
        Optional<String> matricula = dialogo.showAndWait();

        if (matricula.isPresent()) {
            if (nodos.isEmpty()) {
                // Mostrar mensaje de advertencia si la lista está vacía
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText("Lista vacía");
                alerta.setContentText("No hay automóviles en la lista.");
                alerta.showAndWait();
                return;
            }

            // Buscar la matrícula en la lista de nodos
            nodo actual = nodos.get(0);
            do {
                if (actual.getMatricula().equals(matricula.get())) {
                    // Mostrar información del automóvil si se encuentra la matrícula
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText("Información del automóvil");
                    alerta.setTitle("Automóvil encontrado");
                    alerta.setContentText("Modelo: " + actual.getModelo()
                            + "\nMarca: " + actual.getMarca()
                            + "\nPrecio: $" + actual.getPrecio() + " dolares"
                            + "\nUnidades: " + actual.getUnidades());
                    alerta.showAndWait();
                    return; // Salir del método si se encuentra la matrícula
                }
                actual = actual.getSig();
            } while (actual != nodos.get(0)); // Continuar mientras no se regrese al primer nodo

            // Mostrar mensaje de advertencia si no se encuentra la matrícula
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
            // Mostrar mensaje de advertencia si no se ha seleccionado ningún automóvil
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
        } else if (!cantidad.get().matches("^[1-9]\\d*$")) {
            // La entrada no es un número entero positivo
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Entrada inválida");
            alerta.setContentText("La cantidad de unidades debe ser un número entero positivo.");
            alerta.showAndWait();
            return;
        }

        int cantidadComprar = Integer.parseInt(cantidad.get());

        // Verificar si hay suficientes unidades disponibles para la compra
        if (cantidadComprar > auto.getUnidades()) {
            // Mostrar mensaje de advertencia si la cantidad de unidades no está disponible
            Alert ale = new Alert(Alert.AlertType.INFORMATION);
            ale.setHeaderText("Información");
            ale.setContentText("La cantidad de unidades no está disponible");
            ale.showAndWait();
            return;
        }

        if (auto.getUnidades() > 0) {
            // Actualizar la cantidad de unidades y los enlaces en la lista
            auto.setUnidades(auto.getUnidades() - cantidadComprar);
            nodo siguiente = auto.getSig();
            nodo anterior = auto.getAnt();
            siguiente.setAnt(anterior);
            anterior.setSig(siguiente);

            // Actualizar la tabla de autos
            tablaauto.setItems(null);
            tablaauto.layout();
            tablaauto.setItems(FXCollections.observableList(nodos));

            // Mostrar información de la compra y confirmar el pago
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("¿Deseas comprar el auto " + auto.getModelo() + "?");
            alerta.setContentText("El precio del auto es: " + auto.getPrecio() + "\nEl total a pagar es: " + (auto.getPrecio() * cantidadComprar));
            Alert notify = new Alert(Alert.AlertType.INFORMATION);
            notify.setTitle("¡Proceso Exitoso!");
            notify.setHeaderText("Cargando información...");
            notify.setContentText("Pago Realizado Correctamente!");
            alerta.showAndWait();
            notify.show();

            if (auto.getUnidades() <= 0) {
                // Si no quedan unidades disponibles, eliminar el automóvil de la lista
                auto.setUnidades(auto.getUnidades() - cantidadComprar);
                siguiente.setAnt(anterior);
                anterior.setSig(siguiente);
                nodos.remove(auto);

                // Actualizar la tabla de autos
                tablaauto.setItems(null);
                tablaauto.layout();
                tablaauto.setItems(FXCollections.observableList(nodos));
                // Mostrar mensaje de información si no quedan unidades disponibles
                Alert ale = new Alert(Alert.AlertType.INFORMATION);
                ale.setHeaderText("Información");
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
        // Crear una instancia de Alert con tipo INFORMATION
        Alert alert = new Alert(AlertType.INFORMATION);
        // Establecer el título de la ventana de alerta
        alert.setTitle("Listado de autos");
        // Establecer el encabezado de la ventana de alerta
        alert.setHeaderText("Listado completo de autos disponibles");
        // Crear un StringBuilder para construir la cadena de texto
        StringBuilder stringBuilder = new StringBuilder();
        // Obtener la lista de autos desde tablaauto
        ObservableList<nodo> autos = tablaauto.getItems();
        // Obtener el primer elemento de la lista y asignarlo a nodoActual
        nodo nodoActual = autos.get(0);
        // Verificar si la lista no está vacía
        if (!autos.isEmpty()) {
            // Recorrer la lista circular de autos
            do {
                // Construir la cadena de texto con los atributos del auto y agregarla al StringBuilder
                stringBuilder.append(nodoActual.getModelo())
                        .append("   ")
                        .append(nodoActual.getMarca())
                        .append("   ")
                        .append(nodoActual.getMatricula())
                        .append("   ")
                        .append(nodoActual.getPrecio())
                        .append("   ")
                        .append(nodoActual.getUnidades())
                        .append("\n");
                // Avanzar al siguiente nodo en la lista circular
                nodoActual = nodoActual.getSig();
            } while (nodoActual != autos.get(0));
            // Establecer el contenido de la ventana de alerta con la cadena de texto construida    
            alert.setContentText(stringBuilder.toString());
        } else {
            // Si la lista está vacía, establecer un mensaje indicando que no hay autos disponibles
            alert.setContentText("No hay autos disponibles.");
        }
        // Mostrar la ventana de alerta y esperar a que el usuario la cierre
        alert.showAndWait();
        //3123033608
    }

    @FXML
    private void Unidades(ActionEvent event) {
        // Mostrar diálogo para ingresar la matrícula
        TextInputDialog dialogo = new TextInputDialog("");
        dialogo.setTitle("Buscar automóvil");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese la matrícula:");

        Optional<String> matricula = dialogo.showAndWait();

        if (matricula.isPresent()) {
            if (nodos.isEmpty()) {
                // Mostrar mensaje de advertencia si la lista está vacía
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
                    dialogo2.setContentText("Ingrese el nuevo número de unidades:");

                    Optional<String> unidades = dialogo2.showAndWait();

                    // Verificar si se ingresó un número de unidades válido
                    if (unidades.isPresent()) {
                        int nuevasUnidades = Integer.parseInt(unidades.get());
                        actual.setUnidades(nuevasUnidades);
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setHeaderText("Proceso Exitoso!");
                        alerta.setTitle("Cargando información...");
                        alerta.setContentText("Unidades registradas correctamente");
                        alerta.showAndWait();

                        // Actualizar la tabla de automóviles
                        tablaauto.setItems(null);
                        tablaauto.layout();
                        tablaauto.setItems(FXCollections.observableList(nodos));
                    }
                    return; // Salir del método si se encuentra la matrícula
                }
                actual = actual.getSig();
            } while (actual != nodos.get(0)); // Continuar mientras no se regrese al primer nodo

            // Mostrar mensaje de advertencia si no se encuentra la matrícula
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Matrícula no encontrada");
            alerta.setContentText("No se encontró ningún automóvil con la matrícula ingresada.");
        }
    }

}
