package MainHelpWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindowGUI.fxml"));
        primaryStage.setTitle("Main");
        primaryStage.setScene(new Scene(root, 778, 592));
        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Flag_of_Japan.svg/135px-Flag_of_Japan.svg.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
