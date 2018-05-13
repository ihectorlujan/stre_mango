package com.validators;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Messages {

    public void setMessage(String title, String message, NotificationType type) {
        TrayNotification trayNotification = new TrayNotification();
        trayNotification.setTitle(title);
        trayNotification.setMessage(message);
        trayNotification.setNotificationType(type);
        trayNotification.setAnimationType(AnimationType.POPUP);
        trayNotification.showAndDismiss(Duration.millis(3000));
    }

    public void setMessageAlert(Stage stage, String title, String body) {
        JFXAlert alert = new JFXAlert(stage);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label(title));
        layout.setBody(new Label(body));
        JFXButton closeButton = new JFXButton("Aceptar");
        closeButton.getStyleClass().add("btnRaisedBlue");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }
}
