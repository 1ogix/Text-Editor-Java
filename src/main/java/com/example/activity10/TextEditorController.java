package com.example.activity10;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditorController {

    @FXML
    private TextArea textArea;

    private File currentFile;

    public void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            currentFile = file;
            try (FileReader reader = new FileReader(file)) {
                textArea.clear();
                int data;
                while ((data = reader.read()) != -1) {
                    textArea.appendText(String.valueOf((char) data));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("untitled.txt");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            currentFile = file;
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
                showAlert("File Saved", "The file has been saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleUpdate() {
        if (currentFile != null) {
            try (FileWriter writer = new FileWriter(currentFile)) {
                writer.write(textArea.getText());
                showAlert("File Updated", "The file has been updated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}