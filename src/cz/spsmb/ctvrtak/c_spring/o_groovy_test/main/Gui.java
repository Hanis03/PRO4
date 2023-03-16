package cz.spsmb.ctvrtak.c_spring.o_groovy_test.main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;

public class Gui extends Application {
    public static final int N_FREE_TRIES = 3;
    //private static ClassPathXmlApplicationContext context;
    private static ApplicationContext context;
    private static boolean isCheat;

    static{
        Path file = Paths.get(MainTest.FILENAME_CODE_PATH);

        try {
            BasicFileAttributes bfa = Files.readAttributes(file, BasicFileAttributes.class);
            if (bfa.isRegularFile() && Instant.now().minusSeconds(bfa.lastModifiedTime().toInstant().getEpochSecond()).getEpochSecond() < 300) {
                System.out.println(Instant.now().minusSeconds(bfa.lastModifiedTime().toInstant().getEpochSecond()).getEpochSecond());
                //Gui.isCheat = true;
            }
        } catch (IOException ex) {

        }
        //Gui.context = new ClassPathXmlApplicationContext("context.xml");
        Gui.context = new GenericGroovyApplicationContext("context.groovy");
    }

    public static ApplicationContext getContext() {
        return context;
    }

    private TestCollection testCollection = context.getBean("testCollection", TestCollection.class);
    private MainTest test = testCollection.getRandomTest();

    private int freeTries = Gui.N_FREE_TRIES;
    private int[] markLimits={210, 140, 70 };
    private int seconds = 300;

    private Scene scene, sawScene;
    private BorderPane root;
    private Timeline timeline = new Timeline();
    private TextArea taCode;
    private Label lTimer = new Label(Integer.toString(seconds));
    private Label lMark = new Label("1");
    private Button bSubmit = new Button("submit");
    private Label lFreeTries = new Label();
    private TextFlow tfwEntry;
    private Label lOutput = new Label();
    private VBox rightVBox = new VBox(lMark, lTimer, bSubmit, new Label("Zbývá volných "), lFreeTries);
    private VBox leftVBox = new VBox();
    @Override
    public void start(Stage stage) throws Exception {

        WelcomeScreen root2 = Gui.context.getBean(Gui.isCheat? "cheatingWarningScreen":"welcomeScreen", WelcomeScreen.class);
        root2.initScreen();
        sawScene = new Scene(root2);

        root = new BorderPane();
        root.setMinSize(800,600);
        root.setBottom(this.lOutput);
        root.setRight(this.rightVBox);
        root.setLeft(this.leftVBox);

        scene = new Scene(root);
        scene.getStylesheets().add("gst.css");
        stage.setScene(scene);
        stage.show();
        Stage stage2 = new Stage();
        stage2.setScene(sawScene);
        stage2.showAndWait();
        this.init_app();
        if(!Gui.isCheat) {
            MainTest.createGroovyTemplateFile(this.testCollection.getInitGroovyCode());
            this.addHandlers();
        }
    }
    private void init_app(){
        lMark.setStyle("-fx-font-size:40");
        lOutput.setMinSize(500,150);
        this.refreshFreeTries();
        System.out.println(test.getEntry());
        if(!Gui.isCheat){
            tfwEntry = new TextFlow(
                    new Text(test.getEntry()),
                    new Text("\nvstup (in):\n"), new Text(test.getInp()),
                    new Text("\npředp. výstup:\n"), new Text(test.getOut())
            );
            taCode = new MyTextArea(this.testCollection.getInitGroovyCode());

            tfwEntry.setMinSize(500,150);
            taCode.setMinSize(500, 400);
            root.setTop(this.tfwEntry);
            root.setCenter(this.taCode);
        }

    }
    private void addHandlers(){
        this.bSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Gui.this.lOutput.setText("");
                //Gui.this.timeline.stop();
                if(Gui.this.freeTries-- < 0){
                    Gui.this.seconds-=10;
                }
                Gui.this.refreshFreeTries();
                try {
                    MainTest.createGroovyTemplateFile(taCode.getText());
                    //context = new ClassPathXmlApplicationContext("context.xml");
                    //test = context.getBean("test", MainTest.class);
                    Thread.sleep(800);
                    Gui.this.lOutput.setText(test.check());
                    if(Gui.this.test.isValid()){
                        Gui.this.stopTest();
                    }
                } catch (Exception e) {
                    Gui.this.lOutput.setText(e.getMessage());
                }
                //Gui.this.timeline.play();

            }
        });
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Gui.this.seconds-- < 0){
                    Gui.this.lMark.setText("5");
                    Gui.this.stopTest();
                }
                Gui.this.lTimer.setText(Integer.toString(Gui.this.seconds));
                Gui.this.lMark.setText(Integer.toString(Gui.this.getMark()));
            }
        }));
        timeline.setCycleCount(Gui.this.seconds);
        timeline.play();
    }
    private void stopTest() {
        Gui.this.lMark.setStyle("-fx-font-size:120;");
        Gui.this.lMark.setTextFill(Color.RED);
        Gui.this.timeline.stop();
        //Gui.this.bSubmit.setOnAction(null);
    }
    private int getMark(){
        for(int i=0; i < this.markLimits.length; i++){
            if(this.markLimits[i] < this.seconds){
                return i+1;
            }
        }
        return this.seconds>0 ? 4 : 5;
    }
    private void refreshFreeTries(){
        this.lFreeTries.setText(String.format("pokusú: %d", freeTries));
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
