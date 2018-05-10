package com.validators;

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
}
