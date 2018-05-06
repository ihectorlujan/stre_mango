package com.modelo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Producto extends RecursiveTreeObject<Producto> {
    private StringProperty cod_barra,nombre,observaciones;
    private FloatProperty precio_compra,precio_venta;
    private IntegerProperty existencia;

    public Producto(String cod_barra, String nombre, String observaciones, float precio_compra, float precio_venta, int existencia) {
        this.cod_barra = new SimpleStringProperty(cod_barra);
        this.nombre = new SimpleStringProperty(nombre);
        this.observaciones = new SimpleStringProperty(observaciones);
        this.precio_compra = new SimpleFloatProperty(precio_compra);
        this.precio_venta = new SimpleFloatProperty(precio_venta);
        this.existencia = new SimpleIntegerProperty(existencia);
    }

    public String getCod_barra() {
        return cod_barra.get();
    }

    public StringProperty cod_barraProperty() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra.set(cod_barra);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getObservaciones() {
        return observaciones.get();
    }

    public StringProperty observacionesProperty() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }

    public float getPrecio_compra() {
        return precio_compra.get();
    }

    public FloatProperty precio_compraProperty() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra.set(precio_compra);
    }

    public float getPrecio_venta() {
        return precio_venta.get();
    }

    public FloatProperty precio_ventaProperty() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta.set(precio_venta);
    }

    public int getExistencia() {
        return existencia.get();
    }

    public IntegerProperty existenciaProperty() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia.set(existencia);
    }

    public static void llenarProductos(Connection connection, ObservableList<Producto> productos) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM producto");
            while (resultSet.next())
                productos.add(new Producto(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(7),
                        resultSet.getFloat(3),
                        resultSet.getFloat(4),
                        resultSet.getInt(5)
                ));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
