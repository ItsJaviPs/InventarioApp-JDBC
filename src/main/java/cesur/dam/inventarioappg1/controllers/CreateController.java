package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.NavigationController;
import cesur.dam.inventarioappg1.dao.InventarioDAO;
import cesur.dam.inventarioappg1.models.Inventario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreateController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField txtComment;

    private final InventarioDAO dao = new InventarioDAO();

    /**
     * Valida el formulario y, si todo es correcto, guarda el registro en la base de datos
     * y vuelve al listado.
     */
    @FXML
    public void save(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        String comment = txtComment.getText().trim();

        // 1. Validaciones
        if (name.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Datos incorrectos", "El nombre es obligatorio.");
            return;
        }
        if (name.length() > 50) {
            showAlert(Alert.AlertType.WARNING, "Datos incorrectos", "El nombre no puede pasar de 50 caracteres.");
            return;
        }
        if (comment.length() > 100) {
            showAlert(Alert.AlertType.WARNING, "Datos incorrectos", "El comentario no puede pasar de 100 caracteres.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(txtQuantity.getText().trim());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Datos incorrectos", "La cantidad debe ser un numero entero.");
            return;
        }
        if (quantity < 0) {
            showAlert(Alert.AlertType.WARNING, "Datos incorrectos", "La cantidad no puede ser negativa.");
            return;
        }

        // 2. Guardar con el DAO
        Inventario item = new Inventario();
        item.setNombre(name);
        item.setCantidad(quantity);
        item.setComentario(comment);

        try {
            dao.insert(item);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error al guardar", e.getMessage());
            return;
        }

        // 3. Volver al listado
        NavigationController.goToMain();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        NavigationController.goToMain();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Guardar");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
