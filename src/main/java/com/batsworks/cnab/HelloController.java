package com.batsworks.cnab;

import com.batsworks.cnab.config.database.DataBaseDriver;
import com.batsworks.cnab.config.repository.Repository;
import com.batsworks.cnab.model.CNAB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private static final String initialDirectory = System.getProperty("user.dir");
    @FXML
    private Label welcomeText;
    @FXML
    private Button button;
    FileChooser chooser = new FileChooser();

    @FXML
    void findCnab() throws Exception {
        File file = chooser.showOpenDialog(new Stage());

        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooser.setInitialDirectory(new File(initialDirectory));
        Repository<CNAB> repository = new Repository<>(CNAB.class);
        System.out.println(repository.findAll().isEmpty());
    }
}