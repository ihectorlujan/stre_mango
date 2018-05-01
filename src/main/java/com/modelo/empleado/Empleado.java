package com.modelo.empleado;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Empleado extends RecursiveTreeObject {
    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty edad;
    private StringProperty telefono;
    private StringProperty correo;
    private StringProperty nombreCalle;
    private StringProperty nCalle;
    private StringProperty codigoPostal;
    private StringProperty asentamiento;
    private StringProperty municipio;
    private StringProperty estado;
    private StringProperty ciudad;

    public Empleado(StringProperty nombre, StringProperty apellido, IntegerProperty edad, StringProperty telefono, StringProperty correo, StringProperty nombreCalle, StringProperty nCalle, StringProperty codigoPostal, StringProperty asentamiento, StringProperty municipio, StringProperty estado, StringProperty ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.nombreCalle = nombreCalle;
        this.nCalle = nCalle;
        this.codigoPostal = codigoPostal;
        this.asentamiento = asentamiento;
        this.municipio = municipio;
        this.estado = estado;
        this.ciudad = ciudad;
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

    public String getnCalle() {
        return nCalle.get();
    }

    public StringProperty nCalleProperty() {
        return nCalle;
    }

    public void setnCalle(String nCalle) {
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
}
