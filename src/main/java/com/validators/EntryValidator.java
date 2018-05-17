package com.validators;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import jdk.jfr.Description;
import tray.notification.NotificationType;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntryValidator {

    public EventHandler<KeyEvent> onlyLetters() {
        return new EventHandler<>() {

            private boolean willConsume = false;

            @Override
            public void handle(KeyEvent event) {

                if (willConsume)
                    event.consume();

                if (!event.getCode().isLetterKey()) {
                    if (event.getEventType() == KeyEvent.KEY_PRESSED)
                        willConsume = true;
                    else if (event.getEventType() == KeyEvent.KEY_RELEASED)
                        willConsume = false;
                }
            }
        };
    }

    public EventHandler<KeyEvent> onlyNumbers() {
        return new EventHandler<>() {

            private boolean willConsume = false;

            @Override
            public void handle(KeyEvent event) {

                if (willConsume)
                    event.consume();

                if (!event.getCode().isDigitKey()) {
                    if (event.getEventType() == KeyEvent.KEY_PRESSED)
                        willConsume = true;
                    else if (event.getEventType() == KeyEvent.KEY_RELEASED)
                        willConsume = false;
                }
            }
        };
    }

}
