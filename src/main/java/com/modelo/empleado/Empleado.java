package com.modelo.empleado;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tray.notification.TrayNotification;

import java.sql.*;

public class Empleado extends RecursiveTreeObject<Empleado> {
    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty edad;
    private StringProperty telefono;
    private StringProperty correo;
    private StringProperty nombreCalle;
    private IntegerProperty nCalle;
    private StringProperty codigoPostal;
    private StringProperty asentamiento;
    private StringProperty municipio;
    private StringProperty estado;
    private StringProperty ciudad;
    private BooleanProperty isDeshabilitado;

    public Empleado(String nombre, String apellido, Integer edad, String telefono, String correo, String nombreCalle, int nCalle, String codigoPostal, String asentamiento, String municipio, String estado, String ciudad, boolean isDeshabilitado) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.edad = new SimpleIntegerProperty(edad);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        this.nombreCalle = new SimpleStringProperty(nombreCalle);
        this.nCalle = new SimpleIntegerProperty(nCalle);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.asentamiento = new SimpleStringProperty(asentamiento);
        this.municipio = new SimpleStringProperty(municipio);
        this.estado = new SimpleStringProperty(estado);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.isDeshabilitado = new SimpleBooleanProperty(isDeshabilitado);
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

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public int getEdad() {
        return edad.get();
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public String getNombreCalle() {
        return nombreCalle.get();
    }

    public StringProperty nombreCalleProperty() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle.set(nombreCalle);
    }

    public int getnCalle() {
        return nCalle.get();
    }

    public IntegerProperty nCalleProperty() {
        return nCalle;
    }

    public void setnCalle(int nCalle) {
        this.nCalle.set(nCalle);
    }

    public String getCodigoPostal() {
        return codigoPostal.get();
    }

    public StringProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal.set(codigoPostal);
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

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public boolean isIsDeshabilitado() {
        return isDeshabilitado.get();
    }

    public BooleanProperty isDeshabilitadoProperty() {
        return isDeshabilitado;
    }

    public void setIsDeshabilitado(boolean isDeshabilitado) {
        this.isDeshabilitado.set(isDeshabilitado);
    }

    public static ObservableList<Empleado> llenarEmpleados(Connection connection) {
        try {
            ObservableList<Empleado> list = FXCollections.observableArrayList();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM getEmpleados()");

            while(resultSet.next())
                list.add(new Empleado(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getBoolean(14)));

            return list.filtered(x -> !x.isDeshabilitado.get());
        }catch (SQLException a){
            a.printStackTrace();
            return null;
        }
    }

    public int eliminarEmpleado(Connection connection) {
        var query = "UPDATE empleado SET deshabilitado = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            return statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
