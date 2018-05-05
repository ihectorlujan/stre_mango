package com.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends AnchorPane {
    private JFXButton btnSignIn = new JFXButton("Sign In");
    private JFXButton btnForgetPassword = new JFXButton("Olvide mi contrasena");
    private JFXButton btnRestore = new JFXButton("Restaurar");
    private JFXTextField textField = new JFXTextField();
    private JFXPasswordField txtPassword = new JFXPasswordField();
    private Label label = new Label("Chino Jr");
    private Label lblSign = new Label("Sign In");
    private Label lblTip = new Label("Ingrese su usuario y contrasena");
    private AnchorPane apane = new AnchorPane();
    private AnchorPane forgetPasswordPane = new AnchorPane();
    private Text icoBack;

    public Login(Stage primaryStage) {
        getChildren().addAll(apane, label);
        getStyleClass().add("anchorPane");
        paneProperties(apane);
        panePropertiesForgetPassword(forgetPasswordPane);

        btnSignInProperties(btnSignIn);
        btnForgetProperties(btnForgetPassword);

        lblSignProperties(lblSign);
        lblTipProperties(lblTip);
        lblChinoJrProperties(label);

        txtUserProperties(textField);
        txtPasswordProperties(txtPassword);

        // Eventos
        btnSignIn.setOnAction(e -> {
            primaryStage.close();
            new DashBoard();
        });

        btnForgetPassword.setOnAction(e -> {
            getChildren().remove(0);
            getChildren().add(0,forgetPasswordPane);
        });

        btnRestore.setOnAction(e -> {
            //Implementar funcionalidad
            getChildren().remove(0);
            getChildren().add(0, apane);
        });

        icoBack.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY) {
                getChildren().remove(0);
                getChildren().add(0, apane);
            }
        });

        //Hoja de estilos
        getStylesheets().add(getClass().getResource("/estilos/login.css").toExternalForm());
    }

    private void paneProperties(AnchorPane apane) {
        apane.setTranslateX(341);
        apane.setTranslateY(22);
        apane.setPrefHeight(434);
        apane.setPrefWidth(331);
        apane.setStyle("-fx-background-color: white");
        apane.getChildren().addAll(lblSign,lblTip,btnSignIn, btnForgetPassword, textField, txtPassword);
    }

    private void panePropertiesForgetPassword(AnchorPane apane) {
        icoBack = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT);
        var label = new Label("Ingrese su correo");
        var textField = new JFXTextField();

        icoBack.setTranslateX(32);
        icoBack.setTranslateY(50);
        icoBack.getStyleClass().add("iconBack");
        btnSignInProperties(btnRestore);
        lblTipProperties(label);
        txtUserProperties(textField);
        textField.setPromptText("Correo");
        apane.setTranslateX(341);
        apane.setTranslateY(22);
        apane.setPrefHeight(434);
        apane.setPrefWidth(331);
        apane.setStyle("-fx-background-color: white");
        apane.getChildren().addAll(label, textField, btnRestore, icoBack);
    }

    private void btnSignInProperties(JFXButton btnSignIn) {
        btnSignIn.setTextFill(Color.WHITE);
        btnSignIn.setPrefWidth(164);
        btnSignIn.setPrefHeight(30);
        btnSignIn.setTranslateX(79);
        btnSignIn.setTranslateY(313);
        btnSignIn.getStyleClass().add("signin");
    }

    private void btnForgetProperties(JFXButton btnForgetPassword) {
        btnForgetPassword.setPrefWidth(164);
        btnForgetPassword.setPrefHeight(30);
        btnForgetPassword.setTranslateX(80);
        btnForgetPassword.setTranslateY(374);
        btnForgetPassword.getStyleClass().add("forget");
    }

    private void lblSignProperties(Label label) {
        label.setFont(new Font("System",31));
        label.setTranslateX(35);
        label.setTranslateY(39);
    }

    private void lblTipProperties(Label label) {
        label.setFont(new Font("System", 13));
        label.setTranslateY(101);
        label.setTranslateX(35);
    }

    private void txtUserProperties(JFXTextField txtUser) {
        txtUser.setPromptText("Usuario");
        txtUser.setLabelFloat(true);
        txtUser.setTranslateX(32);
        txtUser.setTranslateY(184);
        txtUser.setPrefHeight(30);
        txtUser.setPrefWidth(262);
        txtUser.setFocusColor(Color.web("#2b9fd1"));
        txtUser.setUnFocusColor(Color.web("#0d1b2a"));
    }

    private void txtPasswordProperties(JFXPasswordField textField) {
        textField.setPromptText("Contrasena");
        textField.setLabelFloat(true);
        textField.setTranslateX(32);
        textField.setTranslateY(242);
        textField.setPrefHeight(30);
        textField.setPrefWidth(262);
        textField.setFocusColor(Color.web("#2b9fd1"));
        textField.setUnFocusColor(Color.web("#0d1b2a"));
    }

    private void lblChinoJrProperties(Label chino) {
        chino.setRotate(-23.6);
        chino.setTranslateX(75);
        chino.setTranslateY(181);
        chino.getStyleClass().add("chino");
    }
}
