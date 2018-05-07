package com.modelo.cod_postal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CodigoPostal {
    private IntegerProperty id;
    private StringProperty codigo;
    private StringProperty asentamiento;
    private StringProperty municipio;

    public CodigoPostal(int id, String codigo, String asentamiento, String municipio) {
        this.codigo = new SimpleStringProperty(codigo);
        this.asentamiento = new SimpleStringProperty(asentamiento);
        this.municipio = new SimpleStringProperty(municipio);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getAsentamiento() {
        return asentamiento.get();
    }

    public StringProperty asentamientoProperty() {
        return asentamiento;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento.set(asentamiento);
    }

    public String getMunicipio() {
        return municipio.get();
    }

    public StringProperty municipioProperty() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio.set(municipio);
    }

    @Override
    public String toString() {
        return codigo.get() + " " + asentamiento.get();
    }

    public static  void getCodigosPostales(Connection connection, ObservableList<CodigoPostal> list) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, d_codigo, d_asenta, d_mnpio FROM codigo_postal");

            while (resultSet.next())
                list.add(new CodigoPostal(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4))
                );

        } catch (SQLException a) {
            a.printStackTrace();
        }
    }
}
