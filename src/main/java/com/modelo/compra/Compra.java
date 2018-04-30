package com.modelo.compra;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.*;


public class Compra extends RecursiveTreeObject<Compra> {
    private IntegerProperty id;
    private StringProperty fecha;
    private StringProperty time;
    private IntegerProperty idEmpleado;
    private StringProperty nombreEmpleado;
    private StringProperty apellidoEmpleado;
    private StringProperty proveedor;
    private DoubleProperty monto;

    public Compra(int id, String fecha, String time, int idEmpleado, String nombreEmpleado, String apellidoEmpleado,String proveedor, Double monto) {
        this.id = new SimpleIntegerProperty(id);
        this.fecha = new SimpleStringProperty(fecha);
        this.time = new SimpleStringProperty(time);
        this.idEmpleado = new SimpleIntegerProperty(idEmpleado);
        this.nombreEmpleado = new SimpleStringProperty(nombreEmpleado);
        this.apellidoEmpleado = new SimpleStringProperty(apellidoEmpleado);
        this.proveedor = new SimpleStringProperty(proveedor);
        this.monto = new SimpleDoubleProperty(monto);
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

    public double getMonto() {
        return monto.get();
    }

    public DoubleProperty montoProperty() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto.set(monto);
    }

    @Override
    public String toString() {
        return "ID: " + id.get() + "\t\t"
                + "Fecha: " + fecha.get() + "\t\t"
                + "Monto: $" + monto.get() + "\t\t\t"
                + "Proveedor: " + proveedor.get();
    }

    //
    public static void llenarCompras(Connection connection, ObservableList<Compra> list) {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM getcompras()");

            while(resultSet.next())
                list.add(new Compra(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8)
                ));

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] getMasComprados(Connection connection) {
        var list = new String[5];
        var count = 0;

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM getmascomprados()");
            while(resultSet.next()){
                list[count] = (resultSet.getInt(2) + "  " + resultSet.getString(1));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
