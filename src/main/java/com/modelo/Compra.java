package com.modelo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class Compra extends RecursiveTreeObject<Compra> {
    private IntegerProperty id;
    private StringProperty fecha;
    private StringProperty time;
    private BooleanProperty tipo;
    private IntegerProperty empleado;
    private IntegerProperty proveedor;

    public Compra(int id, String fecha, String time, boolean tipo, int empleado, int proveedor) {
        this.id = new SimpleIntegerProperty(id);
        this.fecha = new SimpleStringProperty(fecha);
        this.time = new SimpleStringProperty(time);
        this.tipo = new SimpleBooleanProperty(tipo);
        this.empleado = new SimpleIntegerProperty(empleado);
        this.proveedor = new SimpleIntegerProperty(proveedor);
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

    public boolean isTipo() {
        return tipo.get();
    }

    public BooleanProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo.set(tipo);
    }

    public int getEmpleado() {
        return empleado.get();
    }

    public IntegerProperty empleadoProperty() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado.set(empleado);
    }

    public int getProveedor() {
        return proveedor.get();
    }

    public IntegerProperty proveedorProperty() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor.set(proveedor);
    }


    //
    public static void llenarCompras(Connection connection, ObservableList<Compra> list) {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transaccion ORDER BY fecha DESC");

            while(resultSet.next())
                list.add(new Compra(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6))
                );

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
