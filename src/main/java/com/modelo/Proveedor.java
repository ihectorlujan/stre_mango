package com.modelo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.empleado.Empleado;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Proveedor extends RecursiveTreeObject {

    private IntegerProperty id;
    private StringProperty razonSocial;
    private StringProperty phone;
    private StringProperty correo;
    private StringProperty calle;
    private StringProperty noCasa;
    private IntegerProperty codigoPostal;
    private StringProperty estado;
    private StringProperty ciudad;
    private StringProperty municipio;
    private StringProperty asentamiento;
    private StringProperty tipoAsentamiento;
    private StringProperty isHabilitado;

    public Proveedor(int id, String razonSocial, String phone, String correo, String calle, String noCasa, Integer codigoPostal, String estado, String ciudad, String municipio, String asentamiento, String tipoAsentamiento) {
        this.id = new SimpleIntegerProperty(id);
        this.razonSocial = new SimpleStringProperty(razonSocial);
        this.phone = new SimpleStringProperty(phone);
        this.correo = new SimpleStringProperty(correo);
        this.calle = new SimpleStringProperty(calle);
        this.noCasa = new SimpleStringProperty(noCasa);
        this.codigoPostal = new SimpleIntegerProperty(codigoPostal);
        this.estado = new SimpleStringProperty(estado);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.municipio = new SimpleStringProperty(municipio);
        this.asentamiento = new SimpleStringProperty(asentamiento);
        this.tipoAsentamiento = new SimpleStringProperty(tipoAsentamiento);
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

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getRazonSocial() {
        return razonSocial.get();
    }

    public StringProperty razonSocialProperty() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial.set(razonSocial);
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

    public String getCalle() {
        return calle.get();
    }

    public StringProperty calleProperty() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public String getNoCasa() {
        return noCasa.get();
    }

    public StringProperty noCasaProperty() {
        return noCasa;
    }

    public void setNoCasa(String noCasa) {
        this.noCasa.set(noCasa);
    }

    public int getCodigoPostal() {
        return codigoPostal.get();
    }

    public IntegerProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal.set(codigoPostal);
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

    public String getMunicipio() {
        return municipio.get();
    }

    public StringProperty municipioProperty() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio.set(municipio);
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

    public String getTipoAsentamiento() {
        return tipoAsentamiento.get();
    }

    public StringProperty tipoAsentamientoProperty() {
        return tipoAsentamiento;
    }

    public void setTipoAsentamiento(String tipoAsentamiento) {
        this.tipoAsentamiento.set(tipoAsentamiento);
    }

    public String getIsHabilitado() {
        return isHabilitado.get();
    }

    public StringProperty isHabilitadoProperty() {
        return isHabilitado;
    }

    public void setIsHabilitado(String isHabilitado) {
        this.isHabilitado.set(isHabilitado);
    }


    public static ObservableList<Proveedor> llenarProveedores(Connection connection) {
        try {
            ObservableList<Proveedor> list = FXCollections.observableArrayList();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_proveedor()");

            while(resultSet.next())
                //resultSet.getString("razon_social");
            //System.out.println(resultSet.getString("razon_social"));
                list.add(new Proveedor(
                                resultSet.getInt("id"),
                                resultSet.getString("razon_social"),
                                resultSet.getString("telefono"),
                                resultSet.getString("correo"),
                                resultSet.getString("nom_calle"),
                                resultSet.getString("num_casa"),
                                resultSet.getInt("d_codigo"),
                                resultSet.getString("d_estado"),
                                resultSet.getString("d_ciudad"),
                                resultSet.getString("d_mnpio"),
                                resultSet.getString("d_asenta"),
                                resultSet.getString("d_tipo_asenta")));

            return list;
        }catch (SQLException a){
            a.printStackTrace();
            return null;
        }
    }






}