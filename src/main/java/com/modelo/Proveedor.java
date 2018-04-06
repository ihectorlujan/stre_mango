package com.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proveedor {

    private StringProperty id,email,phone,razon,comentario;
    public Proveedor(String razon,String id,String comentario,String correo,String phone){

        this.razon=new SimpleStringProperty(razon);
        this.id=new SimpleStringProperty(id);
        this.phone= new SimpleStringProperty(phone);
        this.email=new SimpleStringProperty(correo);
        this.comentario= new SimpleStringProperty(comentario);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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

    public String getRazon() {
        return razon.get();
    }

    public StringProperty razonProperty() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon.set(razon);
    }

    public String getComentario() {
        return comentario.get();
    }

    public StringProperty comentarioProperty() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario.set(comentario);
    }
}