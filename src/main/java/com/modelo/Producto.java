package com.modelo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.*;

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

    public Producto(String cod_barra){
        this.cod_barra = new SimpleStringProperty(cod_barra);
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM producto where estado = true");
            while (resultSet.next())
                productos.add(new Producto(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(6),
                        resultSet.getFloat(3),
                        resultSet.getFloat(4),
                        resultSet.getInt(5)
                ));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public int eliminarProducto(Connection conexion){
        String query = "delete from producto where cod_barra = ?";

        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1,cod_barra.get());
            return statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    */
    public int eliminarProducto(Connection conexion){
        String query = "update producto set estado = ? where cod_barra = ?";

        try{
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setBoolean(1,false);
            statement.setString(2,cod_barra.get());
            return statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public int insertarProducto(Connection conexion){

        String query = "insert into " +
                "producto(cod_barra,nombre,precio_compra,precio_venta,existencia,observaciones,estado)" +
                " values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1,cod_barra.get());
            statement.setString(2,nombre.get());
            statement.setFloat(3,precio_compra.get());
            statement.setFloat(4,precio_venta.get());
            statement.setInt(5,existencia.get());
            statement.setString(6,observaciones.get());
            statement.setBoolean(7,true);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int editarProducto(Connection conexion){

        String query = "update producto set nombre = ?, precio_compra = ?,precio_venta = ?," +
                "existencia = ?,observaciones = ? where cod_barra = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1,nombre.get());
            statement.setFloat(2,precio_compra.get());
            statement.setFloat(3,precio_venta.get());
            statement.setInt(4,existencia.get());
            statement.setString(5,observaciones.get());
            statement.setString(6,cod_barra.get());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


}
