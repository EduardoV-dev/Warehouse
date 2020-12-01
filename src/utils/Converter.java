package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.DB.consultasSelect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Converter {

    public static ObservableList<String> resultSetToObservableList(ResultSet rs, int columna) throws SQLException {
        ObservableList<String> lista = FXCollections.observableArrayList();
        //creando y llenando la lista
        while (rs.next()) {
            lista.add(rs.getString(columna).trim());
        }
        return lista;
    }
}
