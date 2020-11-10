package utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class Cleaner {
    public static void vaciarTextFields(TextField[] tfs) {
        for (TextField tf : tfs) {
            tf.setText("");
        }
    }

    public static void vaciarComboBox(ComboBox[] cbs) {
        for (ComboBox cb : cbs) {
            cb.setValue(null);
        }
    }
}
