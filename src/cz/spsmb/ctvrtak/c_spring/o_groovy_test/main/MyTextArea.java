package cz.spsmb.ctvrtak.c_spring.o_groovy_test.main;


import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class MyTextArea extends TextArea {
    static final String noPaste="M14.5 11.5L9.5 16.5M9.5 11.5L14.5 16.5M15 4.27928V3H9V4.27928M15 4.27928V6H9V4.27928M15 4.27928C18.3745 5.0462 19.5 7.5037 19.5 13C19.5 19.8824 17.7353 22 12 22C6.26471 22 4.5 19.8824 4.5 13C4.5 7.5037 5.62549 5.0462 9 4.27928";
    public MyTextArea(String s) {
        super(s);
    }

    /*@Override
    public void paste() {
        BorderPane bp = (BorderPane) this.getParent();
        VBox n = (VBox) bp.getLeft();
        SVGPath svg = new SVGPath();
        svg.setContent(MyTextArea.noPaste);
        svg.setStroke(Color.RED);
        svg.setFill(Color.WHITE);
        svg.setScaleX(2);
        svg.setScaleY(2);
        n.getChildren().add(svg);
        //System.out.println("NELZE");
        //super.paste();
    }*/
}
