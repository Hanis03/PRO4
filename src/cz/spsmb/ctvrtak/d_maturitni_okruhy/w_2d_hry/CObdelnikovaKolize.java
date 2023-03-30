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


/***
 * Vytvořte dva obdelníky, kde s jedním budete pohybovat pomocí kláves WASD.
 * V případě kolize obou obdelníků změňte barvu jednoho z nich.
 */
public class CObdelnikovaKolize extends Application {

    private Rectangle rectangle1 = new Rectangle(50,30, Color.GREY);
    private Rectangle rectangle2 = new Rectangle(50,20,Color.GREEN);

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root,400,200);
        stage.setScene(scene);
        stage.show();
        root.getChildren().addAll(this.rectangle1,this.rectangle2);
        this.rectangle1.setLayoutX(100);
        this.rectangle2.setLayoutX(150);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode()== KeyCode.LEFT){
                    CObdelnikovaKolize.this.rectangle1.setLayoutX(CObdelnikovaKolize.this.rectangle1.getLayoutX()- 1);
                }
                if (keyEvent.getCode()== KeyCode.RIGHT){
                    CObdelnikovaKolize.this.rectangle1.setLayoutX(CObdelnikovaKolize.this.rectangle1.getLayoutX()+ 1);
                }
                if (keyEvent.getCode()== KeyCode.UP){
                    CObdelnikovaKolize.this.rectangle1.setLayoutY(CObdelnikovaKolize.this.rectangle1.getLayoutY()- 1);
                }
                if (keyEvent.getCode()== KeyCode.DOWN){
                    CObdelnikovaKolize.this.rectangle1.setLayoutY(CObdelnikovaKolize.this.rectangle1.getLayoutY()+ 1);
                }

                if (CObdelnikovaKolize.this.jeKolize()){
                    CObdelnikovaKolize.this.rectangle1.setFill(Color.RED);
                }else{
                    CObdelnikovaKolize.this.rectangle1.setFill(Color.GREY);
                }

            }

        });
    }
    public boolean jeKolize(){
        /*return this.rectangle1.intersects(this.rectangle2.getLayoutX(), this.rectangle2.getLayoutY(),
                this.rectangle2.getWidth(),this.rectangle2.getHeight());*/

        return this.rectangle1.getLayoutX()<this.rectangle2.getLayoutX()+this.rectangle2.getWidth() &&
                this.rectangle2.getLayoutX()<this.rectangle1.getLayoutX()+this.rectangle1.getWidth() &&
                this.rectangle1.getLayoutY()<this.rectangle2.getLayoutY()+this.rectangle2.getHeight() &&
                this.rectangle2.getLayoutY()<this.rectangle1.getLayoutY()+this.rectangle1.getHeight()
                ;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
