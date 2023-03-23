package cz.spsmb.ctvrtak.d_maturitni_okruhy.w_2d_hry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/***
 * Vytvořte dva kruhy, kde s jedním budete pohybovat pomocí kláves WASD.
 * V případě kolize obou kruhů změňte barvu jednoho z nich.
 */
public class AZadani extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root,400,400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
