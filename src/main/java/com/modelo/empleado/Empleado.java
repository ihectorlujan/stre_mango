package com.modelo.empleado;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.validators.Messages;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tray.notification.NotificationType;
import java.sql.*;
import java.util.Objects;

public class Empleado extends RecursiveTreeObject<Empleado> {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty primerApellido;
    private StringProperty segundoApellido;
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

    public Empleado(int id, String nombre, String primerApellido, String apellidoMaterno, Integer edad, String sexo, String telefono, String correo, String nombreCalle, String nCasa, String codigoPostal, String estado, String ciudad, String municipio, String asentamiento, String tipoAsentamiento) {
        this.nombre = new SimpleStringProperty(nombre);
        this.edad = new SimpleIntegerProperty(edad);
        this.segundoApellido = new SimpleStringProperty(apellidoMaterno);
        this.primerApellido = new SimpleStringProperty(primerApellido);
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
        this.id = new SimpleIntegerProperty(id);
    }

    public Empleado(int id, String nombre, String primerApellido, String segundoApellido, Integer edad, String sexo, String telefono, String correo, String nombreCalle, String nCasa) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.edad = new SimpleIntegerProperty(edad);
        this.primerApellido = new SimpleStringProperty(primerApellido);
        this.segundoApellido = new SimpleStringProperty(segundoApellido);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        this.nombreCalle = new SimpleStringProperty(nombreCalle);
        this.nCasa = new SimpleStringProperty(nCasa);
        this.sexo = new SimpleStringProperty(sexo);
    }

    public Empleado(String nombre, String primerApellido, String apellidoMaterno, Integer edad, String sexo, String telefono, String correo, String nombreCalle, String nCasa) {
        this.nombre = new SimpleStringProperty(nombre);
        this.edad = new SimpleIntegerProperty(edad);
        this.segundoApellido = new SimpleStringProperty(apellidoMaterno);
        this.primerApellido = new SimpleStringProperty(primerApellido);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        this.nombreCalle = new SimpleStringProperty(nombreCalle);
        this.nCasa = new SimpleStringProperty(nCasa);
        this.sexo = new SimpleStringProperty(sexo);
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

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public StringProperty primerApellidoProperty() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido.set(primerApellido);
    }

    public String getSegundoApellido() {
        return segundoApellido.get();
    }

    public StringProperty segundoApellidoProperty() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido.set(segundoApellido);
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

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre=" + nombre +
                '}';
    }

    public static ObservableList<Empleado> llenarEmpleados(Connection connection) {
        try {
            ObservableList<Empleado> list = FXCollections.observableArrayList();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_empleados()");

            while(resultSet.next())
                list.add(new Empleado(
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
                        resultSet.getString("codigo"),
                        resultSet.getString("c_estado"),
                        resultSet.getString("ciudad"),
                        resultSet.getString("municipio"),
                        resultSet.getString("asentamiento"),
                        resultSet.getString("tipo_asentamiento")
                        )
                );

            return list;
        }catch (SQLException a){
            a.printStackTrace();
            return null;
        }
    }

    public Empleado eliminarEmpleado(Connection connection) {

        var query = "UPDATE empleado SET estado = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setInt(2,id.get());

            if (statement.executeUpdate() == 1)
                return new Empleado(id.get());

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Empleado addEmpleado(Connection connection, Empleado empleado, String cp, String asenta) {
        try {
            var queryCodigoPostal = "SELECT id FROM codigo_postal WHERE d_codigo = '" + cp + "' and d_asenta = '" + asenta +"'";
            var idCodiPostal = 0;
            var message = new Messages();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryCodigoPostal);

            if (resultSet.next())
                idCodiPostal = resultSet.getInt(1);
            else
                message.setMessage("Codigo postal incorrecto","Verifique el formato del codigo postal",NotificationType.ERROR);

            if (idCodiPostal != 0) {
                var employee_added = "SELECT * FROM get_employee_added('" +
                        empleado.getNombre() +
                        "','"+ empleado.getPrimerApellido() +"'" +
                        ",'"+ empleado.getSegundoApellido() +"'" +
                        ","+ empleado.getEdad() +"" +
                        ",'"+ empleado.getTelefono() +"'" +
                        ",'"+ empleado.getCorreo() +"'" +
                        ",'"+ empleado.getSexo() +"'" +
                        ",'"+ empleado.getNombreCalle() +"'" +
                        ",'"+ empleado.getnCasa() +"'" +
                        ",'true',"+ idCodiPostal +")";

                var statementP = connection.createStatement();
                var resultSet1 = statementP.executeQuery(employee_added);

                if (resultSet1.next())
                    return new Empleado(
                            resultSet1.getInt("id"),
                            resultSet1.getString("nombre"),
                            resultSet1.getString("primer_apellido"),
                            resultSet1.getString("segundo_apellido"),
                            resultSet1.getInt("edad"),
                            resultSet1.getString("sexo"),
                            resultSet1.getString("telefono"),
                            resultSet1.getString("correo"),
                            resultSet1.getString("nom_calle"),
                            resultSet1.getString("num_casa"),
                            resultSet1.getString("codigo"),
                            resultSet1.getString("c_estado"),
                            resultSet1.getString("ciudad"),
                            resultSet1.getString("municipio"),
                            resultSet1.getString("asentamiento"),
                            resultSet1.getString("tipo_asentamiento")
                    );
            }

            return null;
        } catch (SQLException ignored) {ignored.printStackTrace();}

        return null;
    }

    public static Empleado updateEmpleado(Connection connection, Empleado empleado, String cp, String asenta) {
        var queryCodigoPostal = "SELECT id FROM codigo_postal WHERE d_codigo = '" + cp + "' and d_asenta = '" + asenta +"'";
        var message = new Messages();
        var idCodiPostal = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryCodigoPostal);
            if (resultSet.next())
                idCodiPostal = resultSet.getInt(1);
            else
                message.setMessage("Codigo postal incorrecto", "Verifique el formato del codigo postal", NotificationType.ERROR);


            if (idCodiPostal != 0) {

                var employee_added = "SELECT * FROM get_employee_edited(" +
                        "" + empleado.getId() + "," +
                        "'" + empleado.getNombre() +
                        "','" + empleado.getPrimerApellido() + "'" +
                        ",'" + empleado.getSegundoApellido() + "'" +
                        "," + empleado.getEdad() + "" +
                        ",'" + empleado.getTelefono() + "'" +
                        ",'" + empleado.getCorreo() + "'" +
                        ",'" + empleado.getSexo() + "'" +
                        ",'" + empleado.getNombreCalle() + "'" +
                        ",'" + empleado.getnCasa() + "'" +
                        ",'true'," + idCodiPostal + ")";

                var statement1 = connection.createStatement();
                var resultSet1 = statement1.executeQuery(employee_added);

                if (resultSet1.next())
                    return new Empleado(
                            resultSet1.getInt("id"),
                            resultSet1.getString("nombre"),
                            resultSet1.getString("primer_apellido"),
                            resultSet1.getString("segundo_apellido"),
                            resultSet1.getInt("edad"),
                            resultSet1.getString("sexo"),
                            resultSet1.getString("telefono"),
                            resultSet1.getString("correo"),
                            resultSet1.getString("nom_calle"),
                            resultSet1.getString("num_casa"),
                            resultSet1.getString("codigo"),
                            resultSet1.getString("c_estado"),
                            resultSet1.getString("ciudad"),
                            resultSet1.getString("municipio"),
                            resultSet1.getString("asentamiento"),
                            resultSet1.getString("tipo_asentamiento")
                    );
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;

    }
}
