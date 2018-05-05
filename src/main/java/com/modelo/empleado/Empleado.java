package com.modelo.empleado;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;

public class Empleado extends RecursiveTreeObject<Empleado> {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty apellidoPaterno;
    private StringProperty apellidoMaterno;
    private IntegerProperty edad;
    private StringProperty sexo;
    private StringProperty telefono;
    private StringProperty correo;
    private StringProperty nombreCalle;
    private StringProperty nCasa;
    private StringProperty codigoPostal;
    private StringProperty estado;
    private StringProperty ciudad;
    private StringProperty municipio;
    private StringProperty asentamiento;
    private StringProperty tipoAsentamiento;
    private BooleanProperty isHabilitado;

    public Empleado(int id, String nombre, String apellidoPaterno, String apellidoMaterno, Integer edad, String telefono, String correo,String sexo, String nombreCalle, String nCasa, String codigoPostal, String asentamiento, String municipio, String estado, String ciudad, String tipoAsentamiento, boolean isHabilitado) {
        this.nombre = new SimpleStringProperty(nombre);
        this.edad = new SimpleIntegerProperty(edad);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.tipoAsentamiento = new SimpleStringProperty(tipoAsentamiento);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        this.nombreCalle = new SimpleStringProperty(nombreCalle);
        this.nCasa = new SimpleStringProperty(nCasa);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.asentamiento = new SimpleStringProperty(asentamiento);
        this.municipio = new SimpleStringProperty(municipio);
        this.estado = new SimpleStringProperty(estado);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.sexo = new SimpleStringProperty(sexo);
        this.isHabilitado = new SimpleBooleanProperty(isHabilitado);
        this.id = new SimpleIntegerProperty(id);
    }

    public Empleado(int id){
        this.id = new SimpleIntegerProperty(id);
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

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public String getnCasa() {
        return nCasa.get();
    }

    public StringProperty nCasaProperty() {
        return nCasa;
    }

    public void setnCasa(String nCasa) {
        this.nCasa.set(nCasa);
    }

    public boolean isIsHabilitado() {
        return isHabilitado.get();
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

    public boolean getIsHabilitado() {
        return isHabilitado.get();
    }

    public BooleanProperty isHabilitadoProperty() {
        return isHabilitado;
    }

    public void setIsHabilitado(boolean isHabilitado) {
        this.isHabilitado.set(isHabilitado);
    }

    public String getApellidoPaterno() {
        return apellidoPaterno.get();
    }

    public StringProperty apellidoPaternoProperty() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno.set(apellidoPaterno);
    }

    public String getApellidoMaterno() {
        return apellidoMaterno.get();
    }

    public StringProperty apellidoMaternoProperty() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno.set(apellidoMaterno);
    }

    public String getTipoAsentamiento() {
        return tipoAsentamiento.get();
    }

    public StringProperty tipoAsentamientoProperty() {
        return tipoAsentamiento;
    }

    public void setTipoAsentamiento(String tipoAsentamiento) {
        this.tipoAsentamiento.set(tipoAsentamiento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    public static ObservableList<Empleado> llenarEmpleados(Connection connection) {
        try {
            ObservableList<Empleado> list = FXCollections.observableArrayList();
            ObservableList<Empleado> list1 = FXCollections.observableArrayList();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM getempleados()");

//            while(resultSet.next())
//                list.add(new Empleado(resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getInt(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6),
//                        resultSet.getString(7),
//                        resultSet.getString(8),
//                        resultSet.getString(9),
//                        resultSet.getString(10),
//                        resultSet.getString(11),
//                        resultSet.getString(12),
//                        resultSet.getString(13),
//                        resultSet.getBoolean(14),
//                        resultSet.getInt(15)
//                ));

            list1.addAll(list.filtered(x -> x.isHabilitado.get()));

            return list1;
        }catch (SQLException a){
            a.printStackTrace();
            return null;
        }
    }

    public int eliminarEmpleado(Connection connection) {
        var query = "UPDATE empleado SET estado = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setInt(2,id.get());
            return statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
