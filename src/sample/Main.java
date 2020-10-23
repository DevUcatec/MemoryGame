package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;
import model.Player;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Memory Game");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Player player = new Player("Jordy");
        Game game = new Game(player);
        System.out.println("-------Numbers-------");
        game.getDashboard().printN();
        System.out.println("------Dashboard------");
        game.getDashboard().printD();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
