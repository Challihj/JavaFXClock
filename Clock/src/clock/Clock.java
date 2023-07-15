package clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Clock extends Application {

    Text time = new Text();
    // Add functionality for switching between 12 and 24 hr format
    Button btn24 = new Button("24 Hour Clock");
    Button btn12 = new Button("12 Hour Clock");

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(clock(), 640, 300);
        scene.setFill(Color.WHITE);
        stage.setMaxHeight(300);
        stage.setMaxWidth(640);
        stage.setMinHeight(300);
        stage.setMinWidth(640);
        stage.setScene(scene);
        stage.setTitle("Digital Clock");
        stage.show();
    }

    private GridPane clock() {
        HBox hbox = new HBox();
        setTime();
        GridPane grid = new GridPane();
        grid.add(time, 0, 0);
        grid.add(hbox, 0, 1);
        hbox.getChildren().addAll(btn12, btn24);
        hbox.setSpacing(400);
        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    private void setTime() {
        getTime();
        time.setFont(Font.font("Digital-7 Mono", 150));
        time.setFill(Color.GREY);
        time.setTextAlignment(TextAlignment.CENTER);
    }

    private void getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setText(LocalTime.now().format(dtf));
            }

            private void setText(String format) {
                time.setText(format);
            }
        }),
                new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}