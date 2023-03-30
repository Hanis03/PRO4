package cz.spsmb.ctvrtak.d_maturitni_okruhy.w_2d_hry;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.lang.model.element.ModuleElement;

/***
 * Vytvořte dva obdelníky, kde s jedním budete pohybovat pomocí kláves WASD.
 * V případě kolize obou obdelníků změňte barvu jednoho z nich.
 */
public class CObdelnikovaKolize extends Application {
    private Rectangle obd1 = new Rectangle(10,20,Color.BLUE);
    private Rectangle obd2 = new Rectangle(18,25, Color.PURPLE);


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane,500,500);
        stage.setScene(scene);
        stage.show();

        pane.getChildren().addAll(this.obd1, this.obd2);
        this.obd1.setLayoutX(200);
        this.obd1.setLayoutY(300);
        this.obd2.setLayoutX(10);
        this.obd2.setLayoutY(50);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A){
                    CObdelnikovaKolize.this.obd1.setLayoutX(CObdelnikovaKolize.this.obd1.getLayoutX() - 2);
                }
                if (keyEvent.getCode() == KeyCode.D){
                    CObdelnikovaKolize.this.obd1.setLayoutX(CObdelnikovaKolize.this.obd1.getLayoutX() + 2);
                }
                if (keyEvent.getCode() == KeyCode.W){
                    CObdelnikovaKolize.this.obd1.setLayoutY(CObdelnikovaKolize.this.obd1.getLayoutY() - 2);
                }
                if (keyEvent.getCode() == KeyCode.S){
                    CObdelnikovaKolize.this.obd1.setLayoutY(CObdelnikovaKolize.this.obd1.getLayoutY() + 2);
                }
                if (isCollided()){
                    CObdelnikovaKolize.this.obd1.setFill(Color.RED);
                } else {
                    CObdelnikovaKolize.this.obd1.setFill(Color.GREEN);
                }
            }
        });
    }

    public boolean isCollided(){

        return  this.obd1.getLayoutX() < this.obd2.getLayoutX() + this.obd2.getWidth() &&
                this.obd2.getLayoutX() < this.obd1.getLayoutX() + this.obd1.getWidth() &&
                this.obd1.getLayoutY() < this.obd2.getLayoutY() + this.obd2.getHeight() &&
                this.obd2.getLayoutY() < this.obd1.getLayoutY() + this.obd1.getHeight();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
