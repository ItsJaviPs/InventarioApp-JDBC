package cesur.dam.inventarioappg1;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppG1 extends Application {
    @Override
    public void start(Stage stage) {
        // Le damos la ventana al navegador y mostramos la primera escena
        NavigationController.setStage(stage);
        NavigationController.goToLogin();
    }
}
