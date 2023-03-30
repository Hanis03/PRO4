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
    private Circle circle1 = new Circle(50, 50, 30, Color.RED);
    private Circle circle2 = new Circle(100, 100, 30, Color.BLUE);
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root,400,400);
        stage.setScene(scene);
        stage.show();

        root.getChildren().addAll(this.circle1,this.circle2);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A){
                    circle1.setCenterX(circle1.getCenterX()-2);
                }
                if (keyEvent.getCode() == KeyCode.S){
                    circle1.setCenterY(circle1.getCenterY()+2);
                }
                if (keyEvent.getCode() == KeyCode.W){
                    circle1.setCenterY(circle1.getCenterY()-2);
                }
                if (keyEvent.getCode() == KeyCode.D){
                    circle1.setCenterX(circle1.getCenterX()+2);
                }
                if (isCollided()){
                    circle1.setFill(Color.GREEN);
                } else {
                    circle1.setFill(Color.RED);
                }
            }
        });

    }
    public boolean isCollided(){
        double getDeltaYsquare = Math.pow(this.circle2.getCenterY()-this.circle1.getCenterY(), 2);
        double getDeltaXsquare = Math.pow(this.circle2.getCenterX()-this.circle1.getCenterX(),2);
        double rSquare = Math.pow(this.circle1.getRadius()+this.circle2.getRadius(),2);
        System.out.format("getDeltaXsquare %f, getDeltaYsquare %f, rSquare %f \n", getDeltaXsquare, getDeltaYsquare,rSquare);
        return getDeltaXsquare+getDeltaYsquare < rSquare;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
