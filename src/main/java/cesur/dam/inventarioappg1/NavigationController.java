package cesur.dam.inventarioappg1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador de navegacion: es el unico que sabe como cambiar la escena activa.
 * Los demas controladores solo llaman a goToLogin(), goToMain() o goToCreate().
 */
public class NavigationController {

    private static final String VIEWS = "/cesur/dam/inventarioappg1/views/";

    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void goToLogin() {
        show("login-view.fxml", "Inventario App G1 - Login");
    }

    public static void goToMain() {
        show("main-view.fxml", "Inventario App G1 - Listado");
    }

    public static void goToCreate() {
        show("create-view.fxml", "Inventario App G1 - Nuevo registro");
    }

    private static void show(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(VIEWS + fxml));
            Scene scene = new Scene(loader.load());
            stage.setTitle(title);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la vista " + fxml, e);
        }
    }
}
