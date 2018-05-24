package com.modelo.cliente;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.empleado.Empleado;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cliente extends RecursiveTreeObject <Cliente> {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty primer_apellido;
    private StringProperty segundo_apellido;
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
    private StringProperty tipo_asentamiento;
    private BooleanProperty isHabilitado;

    public Cliente(Integer id, String nombre, String primer_apellido, String segundo_apellido, Integer edad, String sexo, String telefono, String correo, String nCalle, String nCasa, String codigoPostal, String estado, String ciudad, String municipio, String asentamiento, Boolean isHabilitado, String tipo_asentamiento) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.primer_apellido = new SimpleStringProperty(primer_apellido);
        this.segundo_apellido = new SimpleStringProperty(segundo_apellido);
        this.edad = new SimpleIntegerProperty(edad);
        this.sexo = new SimpleStringProperty(sexo);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        this.nCalle = new SimpleStringProperty(nCalle);
        this.nCasa = new SimpleStringProperty(nCasa);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.asentamiento = new SimpleStringProperty(asentamiento);
        this.municipio = new SimpleStringProperty(municipio);
        this.estado = new SimpleStringProperty(estado);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.sexo = new SimpleStringProperty(sexo);
        this.isHabilitado = new SimpleBooleanProperty(isHabilitado);
        this.tipo_asentamiento = new SimpleStringProperty(tipo_asentamiento);
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

    public String getPrimer_apellido() {
        return primer_apellido.get();
    }

    public StringProperty primer_apellidoProperty() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido.set(primer_apellido);
    }

    public String getSegundo_apellido() {
        return segundo_apellido.get();
    }

    public StringProperty segundo_apellidoProperty() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido.set(segundo_apellido);
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

    public String getTipo_asentamiento() {
        return tipo_asentamiento.get();
    }

    public StringProperty tipo_asentamientoProperty() {
        return tipo_asentamiento;
    }

    public void setTipo_asentamiento(String tipo_asentamiento) {
        this.tipo_asentamiento.set(tipo_asentamiento);
    }

    public static ObservableList<Cliente> llenarClientes(Connection connection){
        try{
            ObservableList<Cliente> lista = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from get_clientes()");
            while (resultSet.next()){
                lista.add(new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("primer_apellido"),
                        resultSet.getString("segundo_apellido"),
                        resultSet.getInt("edad"),
                        resultSet.getString("sexo"),
                        resultSet.getString("telefono"),
                        resultSet.getString("correo"),
                        resultSet.getString("nom_calle"),
                        resultSet.getString("num_casa"),
                        resultSet.getString("d_codigo"),
                        resultSet.getString("d_estado"),
                        resultSet.getString("d_ciudad"),
                        resultSet.getString("d_mnpio"),
                        resultSet.getString("d_asenta"),
                        resultSet.getBoolean("estado"),
                        resultSet.getString("d_tipo_asenta")
                ));
            }
            return lista;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Cliente updateCliente(Connection connection, Cliente cliente, int idCodigoPostal) {
        try {
            var cliente_agregado = "SELECT * FROM get_employee_edited(" +
                    "" + cliente.getId() + "," +
                    "'" + cliente.getNombre() +
                    "','" + cliente.getPrimer_apellido() + "'" +
                    ",'" + cliente.getSegundo_apellido() + "'" +
                    "," + cliente.getEdad() + "" +
                    ",'" + cliente.getTelefono() + "'" +
                    ",'" + cliente.getCorreo() + "'" +
                    ",'" + cliente.getSexo() + "'" +
                    ",'" + cliente.getnCalle() + "'" +
                    ",'" + cliente.getnCasa() + "'" +
                    "," + idCodigoPostal + ",";
            System.out.println(cliente_agregado);

            var statement1 = connection.createStatement();
            var resultSet1 = statement1.executeQuery(cliente_agregado);

//            if (resultSet1.next())
//                return new Cliente(
//                        resultSet1.getInt("id"),
//                        resultSet1.getString("nombre"),
//                        resultSet1.getString("primer_apellido"),
//                        resultSet1.getString("segundo_apellido"),
//                        resultSet1.getInt("edad"),
//                        resultSet1.getString("sexo"),
//                        resultSet1.getString("telefono"),
//                        resultSet1.getString("correo"),
//                        resultSet1.getString("nom_calle"),
//                        resultSet1.getString("num_casa"),
//                        resultSet1.getString("codigo"),
//                        resultSet1.getString("c_estado"),
//                        resultSet1.getString("ciudad"),
//                        resultSet1.getString("municipio"),
//                        resultSet1.getString("asentamiento"),
//                        resultSet1.getString("tipo_asentamiento"),
//                        resultSet1.getString("usuario"),
//                        resultSet1.getString("password"),
//                        resultSet1.getString("tipo")
//                );
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
