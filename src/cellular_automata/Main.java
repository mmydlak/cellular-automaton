package cellular_automata;

import cellular_automata.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("./view/main-window.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Cellular Automata");
        primaryStage.setScene(new Scene(root, 815, 515));
        primaryStage.setResizable(false);
//        primaryStage.setMinWidth(400);
//        primaryStage.setMinHeight(400);

        Controller controller = fxmlLoader.getController();
        controller.bindToMainApp(this);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
