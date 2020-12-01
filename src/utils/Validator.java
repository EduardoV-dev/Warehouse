package utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Validator {

    public static boolean validarTextFields(TextField[] tfs) {
        //pintando los textfields vacios
        for (TextField tf : tfs) {
            if (tf.getText().isEmpty()) {
                tf.setStyle("-fx-background-color:#EFB3B8;-fx-background-radius:5px;");
            } else {
                tf.setStyle("-fx-background-color:#fff;-fx-background-radius:5px;");
            }
        }
        //realizando validacion para retornar el booleano
        for (TextField tf : tfs) {
            if (tf.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarComboBoxs(ComboBox[] cbs) {
        //pintando los comboboxs vacios
        for (ComboBox cb : cbs) {
            if (cb.getValue() == null) {
                cb.setStyle("-fx-background-color:#EFB3B8;-fx-background-radius:5px;");
            } else {
                cb.setStyle("-fx-background-color:#fff;-fx-background-radius:5px;");
            }
        }
        //realizando validacion para retornar el booleano
        for (ComboBox cb : cbs) {
            if (cb.getValue() == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarTextAreas(TextArea[] tas) {
        //pintando los comboboxs vacios
        for (TextArea ta : tas) {
            if (ta.getText().isEmpty()) {
                ta.setStyle("-fx-background-color:#EFB3B8;-fx-background-radius:5px;");
            } else {
                ta.setStyle("-fx-background-color:#fff;-fx-background-radius:5px;");
            }
        }
        //realizando validacion para retornar el booleano
        for (TextArea ta : tas) {
            if (ta.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
