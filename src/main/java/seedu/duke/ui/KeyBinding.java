package seedu.duke.ui;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class KeyBinding {

    /**
     * Set key binding to read from scene.
     *
     * @param scene      whole scene of application.
     * @param userInput  textfield for user input.
     * @param mainWindow fxml controller.
     */
    KeyBinding(Scene scene, TextField userInput, MainWindow mainWindow) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            KeyCode keyCode = e.getCode();
            Object focus = scene.getFocusOwner();
            if (focus.equals(userInput)) {
                mainWindow.handleUserInputKeyEvent(keyCode);
            }
            mainWindow.handleSceneKeyEvent(keyCode);
            e.consume();
        });
    }
}


