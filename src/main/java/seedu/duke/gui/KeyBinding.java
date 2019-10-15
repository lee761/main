package seedu.duke.gui;

import javafx.scene.Scene;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import seedu.duke.CommandParser;

import java.util.function.UnaryOperator;

public class KeyBinding {

    /**
     * Set key binding to read from scene.
     *
     * @param scene whole scene of application.
     * @param userInput textfield for user input.
     * @param sendButton send button.
     * @param mainWindow fxml controller.
     */
    public KeyBinding(Scene scene, TextField userInput, Button sendButton, MainWindow mainWindow) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            String type = e.getEventType().getName();
            KeyCode keyCode = e.getCode();
            Object focus = scene.getFocusOwner();

            // print key pressed info to terminal for debugging purpose.
            String keyInfo =
                    focus + " " + type + ": Key Code=" + keyCode.getName() + ", Text=" + e.getText() + "\n";
            System.out.println(keyInfo);
            if (focus.equals(userInput)) {
                mainWindow.handleUserInputKeyEvent(keyCode);
            }
            mainWindow.handleSceneKeyEvent(keyCode);
            e.consume();
        });
    }
}

