package utils;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Fuller {

    public static void llenarComboBox(ComboBox<String> combo, ObservableList<String> paises) {

        if (null != combo) {
            combo.setItems(paises);
        }
    }
}
