package com.modelo.cliente;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

public class Cliente extends RecursiveTreeObject <Cliente> {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty apellido_paterno;
    private StringProperty apellido_materno;
    private IntegerProperty edad;
    private StringProperty sexo;
    private StringProperty telefono;
    private StringProperty correo;
    private StringProperty nCalle;
    private StringProperty nCasa;
    private StringProperty codigoPostal;
    private StringProperty estado;
    private StringProperty ciudad;
    private StringProperty municipio;
    private StringProperty asentamiento;
    private BooleanProperty isHabilitado;

    public Cliente(Integer id, String nombre, String apellido_paterno, String apellido_materno, Integer edad, String sexo, String telefono, String correo, String nCalle, String nCasa, String codigoPostal, String estado, String ciudad, String municipio, String asentamiento, Boolean isHabilitado) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido_paterno = new SimpleStringProperty(apellido_paterno);
        this.apellido_materno = new SimpleStringProperty(apellido_materno);
        this.edad = new SimpleIntegerProperty(edad);
        this.sexo = new SimpleStringProperty(sexo);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        this.nCalle = new SimpleStringProperty(nCalle);
        this.nCalle = new SimpleStringProperty(nCasa);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.asentamiento = new SimpleStringProperty(asentamiento);
        this.municipio = new SimpleStringProperty(municipio);
        this.estado = new SimpleStringProperty(estado);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.sexo = new SimpleStringProperty(sexo);
        this.isHabilitado = new SimpleBooleanProperty(isHabilitado);
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

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido_paterno() {
        return apellido_paterno.get();
    }

    public StringProperty apellido_paternoProperty() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno.set(apellido_paterno);
    }

    public String getApellido_materno() {
        return apellido_materno.get();
    }

    public StringProperty apellido_maternoProperty() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno.set(apellido_materno);
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

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
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

    public String getnCalle() {
        return nCalle.get();
    }

    public StringProperty nCalleProperty() {
        return nCalle;
    }

    public void setnCalle(String nCalle) {
        this.nCalle.set(nCalle);
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

    public String getCodigoPostal() {
        return codigoPostal.get();
    }

    public StringProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
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

    public boolean isIsHabilitado() {
        return isHabilitado.get();
    }

    public BooleanProperty isHabilitadoProperty() {
        return isHabilitado;
    }

    public void setIsHabilitado(boolean isHabilitado) {
        this.isHabilitado.set(isHabilitado);
    }
}
