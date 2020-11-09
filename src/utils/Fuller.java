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

    public static void llenarComboBox(ComboBox<String> combo, ObservableList<String> paises) {

        if (null != combo) {
            combo.setItems(paises);
        }
    }

    public static void llenarTableView(TableView tableview, ResultSet rs) throws SQLException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
            tableview.getColumns().addAll(col);
            System.out.println("Column [" + i + "] ");
        }

        while (rs.next()) {
            //Iterar filas
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterar columnas
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added " + row);
            data.add(row);

            //Añadiendo al tableview los datos
            tableview.setItems(data);
        }
    }
}
