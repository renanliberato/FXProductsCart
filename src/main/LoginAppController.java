package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by renan on 01/09/16.
 */
public class LoginAppController {
    @FXML private TextField usernameField;

    @FXML private PasswordField passwordField;

    @FXML private Button loginButton, exitButton;

    @FXML protected void loginAction() {
        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
            try {
                new VitrineApp().start(new Stage());
                LoginApp.getStage().close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "Login e/ou senha errados",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML protected void exitAction() {
        System.exit(0);
    }
}
