package com.validators;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class SimpleValidator {

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
