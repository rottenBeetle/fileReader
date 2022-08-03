package com.rottenbeetle.testtask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label mainLabel;

    @FXML
    private Button getTextFromFile;

    @FXML
    private TextArea textArea;

    @FXML
    private Button chooseFile;

    @FXML
    private Label fileName;

    private File file;



    @FXML
    void initialize() {

        textArea.setEditable(false);

        chooseFile.setOnAction(event -> {
            //Выбор файла
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TXT", "*.txt")
            );
            chooser.setTitle("Выбрать файл");
            file = chooser.showOpenDialog(new Stage());
            //Получение название файла
            if (file!=null){
                fileName.setText(file.getName());
            }
        });

        getTextFromFile.setOnAction(event -> {
            if (file!=null){
                StringBuilder text = new StringBuilder();
                //Поток чтения файла
                try (FileReader reader = new FileReader(file)) {
                    // Читаем посимвольно
                    int c;
                    while ((c = reader.read()) != -1) {
                        text.append((char) c);
                    }
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
                textArea.setText(String.valueOf(text));

            }else {
                //Если файл не выбран - ошибка!
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Выберите файл");

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }
        });

    }
}
