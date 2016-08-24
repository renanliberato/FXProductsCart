package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class LoginApp extends Application {

    private AnchorPane pane;

    private TextField txLogin;

    private PasswordField txSenha;

    private Button btEntrar, btSair;

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        initComponents();
        initListeners();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();

        initLayout();

        LoginApp.stage = stage;
    }

    private void initComponents() {
        pane = new AnchorPane();
        pane.setPrefSize(400, 300);
        pane.getStylesheets().add("css/pane.css");
        pane.getStylesheets().add("css/button.css");
        pane.getStyleClass().add("pane");
//        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue 0%, silver 100%);");

        txLogin = new TextField();
        txLogin.setPromptText("Digite aqui seu login");

        txSenha = new PasswordField();
        txSenha.setPromptText("Digite aqui sua senha");

        btEntrar = new Button("Entrar");
        btEntrar.getStyleClass().add("button");
        btSair   = new Button("Sair");
        btSair.getStyleClass().add("button");

        pane.getChildren().addAll(txLogin, txSenha, btEntrar, btSair);
    }

    private void initLayout() {
        txLogin.setLayoutX((pane.getWidth() - txLogin.getWidth()) / 2);
        txLogin.setLayoutY(50);
        txSenha.setLayoutX((pane.getWidth() - txSenha.getWidth()) / 2);
        txSenha.setLayoutY(100);
        btEntrar.setLayoutX((pane.getWidth() - btEntrar.getWidth()) / 2);
        btEntrar.setLayoutY(150);
        btSair.setLayoutX((pane.getWidth() - btSair.getWidth()) / 2);
        btSair.setLayoutY(200);
    }

    private void initListeners() {
        btSair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fecharAplicacao();
            }
        });

        btEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logar();
            }
        });
    }

    private void fecharAplicacao() {
        System.exit(0);
    }

    private void logar() {
        if (txLogin.getText().equals("admin") && txSenha.getText().equals("admin")) {
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

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
