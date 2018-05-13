package com.validators;

import com.jfoenix.controls.JFXComboBox;
import com.modelo.Conexion;
import javafx.scene.control.TextField;
import jdk.jfr.Description;
import tray.notification.NotificationType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateFields {
    private Conexion conexion = new Conexion();
    private Messages message = new Messages();

    public boolean validateAge(JFXComboBox<Integer> age) {
        return age.getValue() > 16;
    }

    public boolean validateEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        return matcher.find();
    }

    @Description("Needs connections to database")
    public int idCpDatabase(String cp) {
        var cod = cp.split(" ");

        var asenta = "";
        for (int i = 1; i < cod.length; i++) {
            if (i != cod.length - 1)
                asenta += cod[i] + " ";
            else
                asenta += cod[i];
        }

        var queryCodigoPostal = "SELECT id FROM codigo_postal WHERE d_codigo = '" + cod[0] + "' and d_asenta = '" + asenta + "'";
        var idCodiPostal = 0;

        try {
            conexion.establecerConexion();
            Statement statement = conexion.getConection().createStatement();
            ResultSet resultSet = statement.executeQuery(queryCodigoPostal);
            if (resultSet.next())
                idCodiPostal = resultSet.getInt(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }

        return idCodiPostal;
    }

    @Description("Returns true if phone number have 10 digits")
    public boolean validatePhone(TextField... telefono) {
        var digitos = 0;
        for (int i = 0; i < telefono.length; i++)
            digitos += telefono[i].getText().length();

        return digitos == 10;
    }

    @Description("Returns false if one field don't have text")
    public boolean validateFields(TextField... campos) {
        var count = campos.length;
        for (int i = 0; i < campos.length; i++) {
            if (campos[i].getText().equals("")) {
                count++;
            }
        }

        return count == campos.length;
    }
}