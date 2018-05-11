package com.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection conection;
    private final String URL = "jdbc:postgresql://35.188.15.154/refaccionaria?currentSchema=i_iteracion";
    private final String USER = "chino_jr";
    private final String PASSWORD = "chinoOaxaca";

    public void establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException ignored){}
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            conection.close();
        }catch (Exception ignored){}
    }


    public Connection getConection() {
        return conection;
    }

    public void setConection (Connection conection) {
        this.conection = conection;
    }

}
