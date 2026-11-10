package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.NavigationController;
import cesur.dam.inventarioappg1.dao.InventarioDAO;
import cesur.dam.inventarioappg1.models.Inventario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @FXML
    private TableView<Inventario> table;
    @FXML
    private TableColumn<Inventario, String> cId;
    @FXML
    private TableColumn<Inventario, String> cNombre;
    @FXML
    private TableColumn<Inventario, String> cCantidad;
    @FXML
    private TableColumn<Inventario, String> cComentario;
    @FXML
    private TableColumn<Inventario, String> cFecha;

    private final InventarioDAO dao = new InventarioDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Que dato muestra cada columna
        cId.setCellValueFactory(row -> new SimpleStringProperty(String.valueOf(row.getValue().getId())));
        cNombre.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getNombre()));
        cCantidad.setCellValueFactory(row -> new SimpleStringProperty(String.valueOf(row.getValue().getCantidad())));
        cComentario.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getComentario()));
        cFecha.setCellValueFactory(row -> {
            var fecha = row.getValue().getFecha();
            return new SimpleStringProperty(fecha == null ? "--" : fecha.format(FORMATO));
        });

        loadData();
    }

    /** Pide los datos al DAO y los pone en la tabla. */
    private void loadData() {
        try {
            table.setItems(FXCollections.observableArrayList(dao.findAll()));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudieron cargar los datos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void newRecord(ActionEvent actionEvent) {
        NavigationController.goToCreate();
    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        NavigationController.goToLogin();
    }
}
