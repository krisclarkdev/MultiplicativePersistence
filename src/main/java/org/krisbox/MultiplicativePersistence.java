package org.krisbox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigInteger;

public class MultiplicativePersistence extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final TextField startingNumber = new TextField();
        startingNumber.setPromptText("Enter starting number");
        startingNumber.setPrefColumnCount(10);
        startingNumber.getText();
        GridPane.setConstraints(startingNumber, 0, 0);
        grid.getChildren().add(startingNumber);

        final TextField endingNumber = new TextField();
        endingNumber.setPromptText("Enter ending number");
        GridPane.setConstraints(endingNumber, 0, 1);
        grid.getChildren().add(endingNumber);

        final TextField targetSteps = new TextField();
        targetSteps.setPrefColumnCount(15);
        targetSteps.setPromptText("Enter target steps");
        GridPane.setConstraints(targetSteps, 0, 2);
        grid.getChildren().add(targetSteps);

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        Button clear = new Button("Clear");
        GridPane.setConstraints(clear, 1, 1);
        grid.getChildren().add(clear);

        ObservableList<String> resultsListValues = FXCollections.<String>observableArrayList();


        ListView<String> resultsList = new ListView<>(resultsListValues);
        resultsList.setOrientation(Orientation.VERTICAL);
        resultsList.setPrefSize(120, 100);

        GridPane.setConstraints(resultsList, 0,3);
        grid.getChildren().add(resultsList);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BigInteger i = new BigInteger(startingNumber.getText());
                BigInteger j = new BigInteger(endingNumber.getText());

                while(i.compareTo(j) == -1) {
                    i = i.add(new BigInteger("1"));

                    new TestNumber().startTest(i, Integer.parseInt(targetSteps.getText()), resultsList);
                }
            }
        });

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultsList.getItems().clear();
            }
        });

        Menu menuFile = new Menu("File");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuFile);

        Menu menuHelp = new Menu("Help");
        menuBar.getMenus().add(menuHelp);

        MenuItem menuExit = new MenuItem("Exit");
        MenuItem menuAbout = new MenuItem("About");

        menuFile.getItems().add(menuExit);
        menuHelp.getItems().add(menuAbout);

        menuAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");

                alert.setHeaderText("About MultiplicativePersistence\nv1.0");
                alert.setContentText("Kristopher Clark \u00a92019\nGNU GPLv3\nhttps://www.krisbox.org" );
                alert.showAndWait();
            }
        });

        menuExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });

        VBox root = new VBox(menuBar);

        Scene scene = new Scene(root, 300, 250);
        root.getChildren().add(grid);


        primaryStage.setTitle("MultiplicativePersistence");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*public static void main(String[] args) {

    }*/
}