package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.NavigationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    public void login(ActionEvent actionEvent) {
        NavigationController.goToMain();
    }
}
