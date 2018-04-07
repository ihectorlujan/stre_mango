package com.modelo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Compra extends RecursiveTreeObject<Compra> {
    private IntegerProperty id;
    private StringProperty fecha;
    private StringProperty time;
    private IntegerProperty idEmpleado;
    private StringProperty nombreEmpleado;
    private StringProperty apellidoEmpleado;
    private StringProperty proveedor;

    public Compra(int id, String fecha, String time, int idEmpleado, String nombreEmpleado, String apellidoEmpleado,String proveedor) {
        this.id = new SimpleIntegerProperty(id);
        this.fecha = new SimpleStringProperty(fecha);
        this.time = new SimpleStringProperty(time);
        this.idEmpleado = new SimpleIntegerProperty(idEmpleado);
        this.nombreEmpleado = new SimpleStringProperty(nombreEmpleado);
        this.apellidoEmpleado = new SimpleStringProperty(apellidoEmpleado);
        this.proveedor = new SimpleStringProperty(proveedor);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public int getIdEmpleado() {
        return idEmpleado.get();
    }

    public String getNombreEmpleado() {
        return nombreEmpleado.get();
    }

    public StringProperty nombreEmpleadoProperty() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado.set(nombreEmpleado);
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado.get();
    }

    public StringProperty apellidoEmpleadoProperty() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado.set(apellidoEmpleado);
    }

    public IntegerProperty idEmpleadoProperty() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado.set(idEmpleado);
    }

    public String getProveedor() {
        return proveedor.get();
    }

    public StringProperty proveedorProperty() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor.set(proveedor);
    }

    //
    public static void llenarCompras(Connection connection, ObservableList<Compra> list) {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT t.id, t.fecha, t.hora, t.id_empleado,\n" +
                    "        e.nombre, e.apellido, \n" +
                    "        p.razon_social\n" +
                    "FROM transaccion AS t \n" +
                    "        INNER JOIN empleado AS e ON e.id = t.id_empleado \n" +
                    "        INNER JOIN proveedor as p ON t.id_proveedor = p.id\n" +
                    "WHERE t.tipo = true ORDER BY t.fecha DESC"
            );

            while(resultSet.next())
                list.add(new Compra(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7))
                );

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
