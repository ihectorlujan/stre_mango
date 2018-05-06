package com.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proveedor {

    private StringProperty id;
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

    public Proveedor(String id, String razonSocial, String phone, String correo, String calle, String noCasa, Integer codigoPostal, String estado, String ciudad, String municipio, String asentamiento, String tipoAsentamiento, String isHabilitado) {
        this.id = new SimpleStringProperty(id);
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
        this.isHabilitado = new SimpleStringProperty(isHabilitado);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
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
}