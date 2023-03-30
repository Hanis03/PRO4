package cz.spsmb.ctvrtak.d_maturitni_okruhy.w_2d_hry;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/***
 * Vytvořte dva kruhy, kde s jedním budete pohybovat pomocí kláves WASD.
 * V případě kolize obou kruhů změňte barvu jednoho z nich.
 */
public class AZadani extends Application {
    private Circle c1 = new Circle(50,100, 20, Color.GRAY);
    private Circle c2 = new Circle(150,100, 20, Color.GREEN);
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane(this.c1, this.c2);
        Scene scene = new Scene(root,400,400);
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("Key pressed");
                if(keyEvent.getCode() == KeyCode.D){
                    c1.setCenterX(c1.getCenterX()+1);
                }
                if(keyEvent.getCode() == KeyCode.A){
                    c1.setCenterX(c1.getCenterX()-1);
                }
                if(keyEvent.getCode() == KeyCode.W){
                    c1.setCenterY(c1.getCenterY()-1);
                }
                if(keyEvent.getCode() == KeyCode.S){
                    c1.setCenterY(c1.getCenterY()+1);
                }

                if(isCollided()){
                    c1.setFill(Color.RED);
                } else {
                    c1.setFill(Color.GRAY);
                }
            }
        });
    }

    public boolean isCollided() {
        double deltaXsquare = Math.pow(this.c2.getCenterX()-this.c1.getCenterX(),2);
        double deltaYsquare = Math.pow(this.c2.getCenterY()-this.c1.getCenterY(),2);
        double rSquare = Math.pow(this.c1.getRadius()+this.c2.getRadius(),2);
        System.out.format("deltaXsquare: %f, deltaYsquare: %f,  rSquare: %f \n",
                deltaXsquare,
                deltaYsquare,
                rSquare);
        return deltaXsquare+deltaYsquare < rSquare;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
