package utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Fuller {

    public static void llenarComboBox(ComboBox<String> combo, ObservableList<String> lista) {
        if (null != combo) {
            combo.setItems(lista);
        }
    }

    public static void llenarTableView(TableView tableview, ResultSet rs) throws SQLException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Quitar contenido de la tabla
        tableview.getColumns().clear();

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
            col.prefWidthProperty().bind(tableview.widthProperty().multiply(1 / rs.getMetaData().getColumnCount()));
            tableview.getColumns().addAll(col);
        }

        while (rs.next()) {
            //Iterar filas
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterar columnas
                row.add(rs.getString(i).trim());
            }
            data.add(row);

            //Añadiendo al tableview los datos
            tableview.setItems(data);
        }
    }
}
