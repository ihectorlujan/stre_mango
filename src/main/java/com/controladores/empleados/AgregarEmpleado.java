package com.controladores.empleados;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.modelo.Conexion;
import com.modelo.cod_postal.CodigoPostal;
import com.modelo.empleado.Empleado;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.Connection;

public class AgregarEmpleado extends Stage {
    private Conexion conexion = new Conexion();
    private ObservableList<CodigoPostal> list = FXCollections.observableArrayList();

    private TextField txtNombre = new TextField();
    private TextField txtApellidoPaterno = new TextField();
    private TextField txtApellidoMaterno = new TextField();
    private TextField txtTelefono = new TextField();
    private TextField txtCorreo = new TextField();
    private TextField txtCalle = new TextField();
    private TextField txtNoCasa = new TextField();
    private TextField txtCodigoPostal = new TextField();

    public AgregarEmpleado() {
        var panePrincipal = new VBox();

        //Childs of panePrincipal
        var hBoxTitulo = new HBox();
        var hBoxBntAgregar = new HBox();
        var lblAgregar = new Label("Datos del empleado");
        var btnAgregar = new JFXButton("Agregar");
        var hBoxDatosDomiciliarios = new HBox();
        var hBoxCodigoPostal = new HBox();
        var gridPane = new GridPane();
        var gridPaneDomicilio = new GridPane();
            //Childs of gridPane
        var lblNombre = new Label("Nombre:");
        var lblApellidoPaterno = new Label("Apellido Paterno:");
        var lblApellidoMaterno = new Label("Apellido Materno:");
        var lblEdad = new Label("Edad:");
        var lblSexo = new Label("Sexo:");
        var lblTelefono = new Label("Telefono:");
        var lblCorreo = new Label("Correo:");
        var lblDatosDomiciliarios = new Label("Datos domiciliarios");
        var hBoxSexo = new HBox();
        var hBoxEdad = new HBox();
        var rbHombre = new JFXRadioButton("Hombre");
        var rbMujer = new JFXRadioButton("Mujer");
        var cmbEdad = new JFXComboBox<Integer>();
        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();
            //Grid pane datos domicilio
        var lblCalle = new Label("Calle:");
        var lblNoCasa = new Label("No de casa:");
        var lblCodPostal = new Label("C.P:");

        //Llenar comboBox
        llenarComboEdad(cmbEdad);

        //Toggle group
        var toggleGroup = new ToggleGroup();
        rbHombre.setToggleGroup(toggleGroup);
        rbMujer.setToggleGroup(toggleGroup);

        //Add childs gridPane
            //Column 1
        gridPane.add(lblNombre,0,0);
        gridPane.add(lblApellidoPaterno,0,1);
        gridPane.add(lblApellidoMaterno,0,2);
        gridPane.add(hBoxEdad, 0,3);
        gridPane.add(lblTelefono,0,4);
        gridPane.add(lblCorreo,0,5);

            //Column 2
        gridPane.add(txtNombre,1,0);
        gridPane.add(txtApellidoPaterno,1,1);
        gridPane.add(txtApellidoMaterno,1,2);
        gridPane.add(hBoxSexo,1,3);
        gridPane.add(txtTelefono,1,4);
        gridPane.add(txtCorreo,1,5);

        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //GridPane Datos domicilio
        gridPaneDomicilio.add(lblCalle, 0,0);
        gridPaneDomicilio.add(lblNoCasa, 0,1);

        gridPaneDomicilio.add(txtCalle,1,0);
        gridPaneDomicilio.add(txtNoCasa,1,1);

        gridPaneDomicilio.getColumnConstraints().addAll(column1, column2);
        gridPaneDomicilio.setPadding(new Insets(10));
        gridPaneDomicilio.getStyleClass().add("white");
        gridPaneDomicilio.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPaneDomicilio.setHgap(20);
        gridPaneDomicilio.setVgap(5);

        //HBox Sexo
        hBoxSexo.getChildren().addAll(lblSexo,rbHombre, rbMujer);
        hBoxSexo.setPadding(new Insets(5,0,0,0));
        HBox.setMargin(lblSexo, new Insets(0,10,0,0));
        HBox.setMargin(rbHombre, new Insets(0,10,0,0));

        //HBox Edad
        hBoxEdad.getChildren().addAll(lblEdad, cmbEdad);
        hBoxEdad.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(lblEdad, new Insets(0,10,0,0));

        //HBox codigo postal
        hBoxCodigoPostal.getChildren().addAll(lblCodPostal, txtCodigoPostal);
        hBoxCodigoPostal.getStyleClass().add("white");
        hBoxCodigoPostal.setAlignment(Pos.CENTER);
        hBoxCodigoPostal.setPadding(new Insets(0,10,5,10));
        HBox.setHgrow(txtCodigoPostal, Priority.ALWAYS);
        HBox.setMargin(lblCodPostal, new Insets(0,10,0,0));

        //Add styles
        btnAgregar.getStyleClass().add("btnRaisedBlue");
        gridPane.getStyleClass().add("white");

        //HBox de datos domiciliarios
        hBoxDatosDomiciliarios.getChildren().add(lblDatosDomiciliarios);
        hBoxDatosDomiciliarios.setPadding(new Insets(5));
        hBoxDatosDomiciliarios.getStyleClass().add("white");

        //Hboxes de ayuda
        hBoxTitulo.getChildren().add(lblAgregar);
        hBoxTitulo.setPadding(new Insets(5));
        hBoxBntAgregar.getChildren().add(btnAgregar);

        hBoxBntAgregar.setAlignment(Pos.CENTER_RIGHT);

        //Estilos de los diversos Box
        hBoxTitulo.getStyleClass().add("white");

        //Add childs to pane Principal
        panePrincipal.getChildren().addAll(hBoxTitulo, gridPane,hBoxDatosDomiciliarios, gridPaneDomicilio, hBoxCodigoPostal, hBoxBntAgregar);

        //Properties of panePrincipal
        panePrincipal.setPadding(new Insets(10));
        VBox.setMargin(hBoxTitulo, new Insets(0,0,5,0));
        VBox.setMargin(gridPane, new Insets(0,0,5,0));
        VBox.setMargin(hBoxDatosDomiciliarios, new Insets(0,0,5,0));
        VBox.setMargin(hBoxCodigoPostal, new Insets(0,0,10,0));

        //Acciones de los botones
        btnAgregar.setOnAction(e -> {
            txtCodigoPostal.getText();
            if (checkAgregar(gridPane, gridPaneDomicilio, txtCodigoPostal )){
                var sexo = rbMujer.isSelected()?"mujer":"hombre";
                var empleado = new Empleado(txtNombre.getText(), txtApellidoPaterno.getText(), txtApellidoMaterno.getText(), cmbEdad.getValue(),sexo, txtTelefono.getText(), txtCorreo.getText(),txtCalle.getText(), txtNoCasa.getText());
                agregarEmpleado(empleado, txtCodigoPostal.getText());
                this.close();
            }
        });

        //Eventos de los textFields

        //Metodo para completar
            //Llenar la lista
        conexion.establecerConexion();
        CodigoPostal.getCodigosPostales(conexion.getConection(), list);
        conexion.cerrarConexion();
        TextFields.bindAutoCompletion(txtCodigoPostal, list);

        setResizable(false);
        Scene scene = new Scene(panePrincipal, 410,465);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/agregar_empleado.css").toExternalForm());
        show();
    }

    public AgregarEmpleado(Empleado empleado) {
        var panePrincipal = new VBox();

        //Childs of panePrincipal
        var hBoxTitulo = new HBox();
        var hBoxBntAgregar = new HBox();
        var lblAgregar = new Label("Datos del empleado");
        var btnAgregar = new JFXButton("Agregar");
        var hBoxDatosDomiciliarios = new HBox();
        var hBoxCodigoPostal = new HBox();
        var gridPane = new GridPane();
        var gridPaneDomicilio = new GridPane();
        //Childs of gridPane
        var lblNombre = new Label("Nombre:");
        var lblApellidoPaterno = new Label("Apellido Paterno:");
        var lblApellidoMaterno = new Label("Apellido Materno:");
        var lblEdad = new Label("Edad:");
        var lblSexo = new Label("Sexo:");
        var lblTelefono = new Label("Telefono:");
        var lblCorreo = new Label("Correo:");
        var lblDatosDomiciliarios = new Label("Datos domiciliarios");
        var hBoxSexo = new HBox();
        var hBoxEdad = new HBox();
        var rbHombre = new JFXRadioButton("Hombre");
        var rbMujer = new JFXRadioButton("Mujer");
        var cmbEdad = new JFXComboBox<Integer>();
        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();
        //Grid pane datos domicilio
        var lblCalle = new Label("Calle:");
        var lblNoCasa = new Label("No de casa:");
        var lblCodPostal = new Label("C.P:");

        //Llenar comboBox
        llenarComboEdad(cmbEdad);

        //Toggle group
        var toggleGroup = new ToggleGroup();
        rbHombre.setToggleGroup(toggleGroup);
        rbMujer.setToggleGroup(toggleGroup);

        //Add childs gridPane
        //Column 1
        gridPane.add(lblNombre,0,0);
        gridPane.add(lblApellidoPaterno,0,1);
        gridPane.add(lblApellidoMaterno,0,2);
        gridPane.add(hBoxEdad, 0,3);
        gridPane.add(lblTelefono,0,4);
        gridPane.add(lblCorreo,0,5);

        //Column 2
        gridPane.add(txtNombre,1,0);
        gridPane.add(txtApellidoPaterno,1,1);
        gridPane.add(txtApellidoMaterno,1,2);
        gridPane.add(hBoxSexo,1,3);
        gridPane.add(txtTelefono,1,4);
        gridPane.add(txtCorreo,1,5);

        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //GridPane Datos domicilio
        gridPaneDomicilio.add(lblCalle, 0,0);
        gridPaneDomicilio.add(lblNoCasa, 0,1);

        gridPaneDomicilio.add(txtCalle,1,0);
        gridPaneDomicilio.add(txtNoCasa,1,1);

        gridPaneDomicilio.getColumnConstraints().addAll(column1, column2);
        gridPaneDomicilio.setPadding(new Insets(10));
        gridPaneDomicilio.getStyleClass().add("white");
        gridPaneDomicilio.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPaneDomicilio.setHgap(20);
        gridPaneDomicilio.setVgap(5);

        //HBox Sexo
        hBoxSexo.getChildren().addAll(lblSexo,rbHombre, rbMujer);
        hBoxSexo.setPadding(new Insets(5,0,0,0));
        HBox.setMargin(lblSexo, new Insets(0,10,0,0));
        HBox.setMargin(rbHombre, new Insets(0,10,0,0));

        //HBox Edad
        hBoxEdad.getChildren().addAll(lblEdad, cmbEdad);
        hBoxEdad.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(lblEdad, new Insets(0,10,0,0));

        //HBox codigo postal
        hBoxCodigoPostal.getChildren().addAll(lblCodPostal, txtCodigoPostal);
        hBoxCodigoPostal.getStyleClass().add("white");
        hBoxCodigoPostal.setAlignment(Pos.CENTER);
        hBoxCodigoPostal.setPadding(new Insets(0,10,5,10));
        HBox.setHgrow(txtCodigoPostal, Priority.ALWAYS);
        HBox.setMargin(lblCodPostal, new Insets(0,10,0,0));

        //Add styles
        btnAgregar.getStyleClass().add("btnRaisedBlue");
        gridPane.getStyleClass().add("white");

        //HBox de datos domiciliarios
        hBoxDatosDomiciliarios.getChildren().add(lblDatosDomiciliarios);
        hBoxDatosDomiciliarios.setPadding(new Insets(5));
        hBoxDatosDomiciliarios.getStyleClass().add("white");

        //Hboxes de ayuda
        hBoxTitulo.getChildren().add(lblAgregar);
        hBoxTitulo.setPadding(new Insets(5));
        hBoxBntAgregar.getChildren().add(btnAgregar);

        hBoxBntAgregar.setAlignment(Pos.CENTER_RIGHT);

        //Estilos de los diversos Box
        hBoxTitulo.getStyleClass().add("white");

        //Add childs to pane Principal
        panePrincipal.getChildren().addAll(hBoxTitulo, gridPane,hBoxDatosDomiciliarios, gridPaneDomicilio, hBoxCodigoPostal, hBoxBntAgregar);

        //Properties of panePrincipal
        panePrincipal.setPadding(new Insets(10));
        VBox.setMargin(hBoxTitulo, new Insets(0,0,5,0));
        VBox.setMargin(gridPane, new Insets(0,0,5,0));
        VBox.setMargin(hBoxDatosDomiciliarios, new Insets(0,0,5,0));
        VBox.setMargin(hBoxCodigoPostal, new Insets(0,0,10,0));

        //Acciones de los botones
        btnAgregar.setOnAction(e -> {
            txtCodigoPostal.getText();
            if (checkAgregar(gridPane, gridPaneDomicilio, txtCodigoPostal )){
                var sexo = rbMujer.isSelected()?"mujer":"hombre";
                var emp = new Empleado(empleado.getId(),txtNombre.getText(), txtApellidoPaterno.getText(), txtApellidoMaterno.getText(), cmbEdad.getValue(),sexo, txtTelefono.getText(), txtCorreo.getText(),txtCalle.getText(), txtNoCasa.getText());
                updateEmpleado(emp, txtCodigoPostal.getText());
                this.close();
            }
        });

        //Eventos de los textFields

        //Metodo para completar
        //Llenar la lista
        conexion.establecerConexion();
        CodigoPostal.getCodigosPostales(conexion.getConection(), list);
        conexion.cerrarConexion();
        TextFields.bindAutoCompletion(txtCodigoPostal, list);

        setResizable(false);
        Scene scene = new Scene(panePrincipal, 410,465);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/agregar_empleado.css").toExternalForm());
        show();

        //Establecer campos
        txtNombre.setText(empleado.getNombre());
        txtApellidoPaterno.setText(empleado.getApellidoPaterno());
        txtApellidoMaterno.setText(empleado.getApellidoMaterno());
        txtCorreo.setText(empleado.getCorreo());
        txtTelefono.setText(empleado.getTelefono());
        txtCalle.setText(empleado.getNombreCalle());
        txtNoCasa.setText(empleado.getnCasa());
        if (empleado.getSexo().equals("hombre"))
            rbHombre.setSelected(true);
        else
            rbMujer.setSelected(true);
        cmbEdad.setValue(empleado.getEdad());
        txtCodigoPostal.setText(empleado.getCodigoPostal() + " " + empleado.getAsentamiento());
    }

    private void llenarComboEdad(JFXComboBox<Integer> combo) {
        for (int i = 16; i < 60; i++)
            combo.getItems().add(i);
    }

    private boolean checkTextField(GridPane pane) {
        var correcText = pane.getChildren().filtered(x -> x instanceof TextField).size();

        var verify = (int) pane.getChildren().stream()
                .filter(x -> x instanceof TextField)
                .filter(x -> !((TextField) x).getText().trim().equals(""))
                .count();

        pane.getChildren().forEach(x -> {
            if (x instanceof TextField) {
                if (((TextField) x).getText().trim().equals(""))
                    x.getStyleClass().add("error");
                else
                    x.getStyleClass().add("verificado");
            }
        });

        return verify == correcText;
    }

    private boolean checkAgregar(GridPane gridPane, GridPane gridPaneDomicilio, TextField codigo) {
        var x = checkTextField(gridPane);
        var y = checkTextField(gridPaneDomicilio);

        if (codigo.getText().trim().equals(""))
            codigo.getStyleClass().add("error");
        else
            codigo.getStyleClass().add("verificado");

        return x && y && !codigo.getText().equals("");
    }

    private void agregarEmpleado(Empleado x, String codigoPostal) {
        var cod = codigoPostal.split(" ");

        var asenta = "";
        for (int i = 1; i < cod.length; i++) {
            if (i != cod.length - 1)
                asenta += cod[i] + " ";
            else
                asenta += cod[i];
        }


        conexion.establecerConexion();
        var success  = Empleado.addEmpleado(conexion.getConection(), x,cod[0], asenta);
        conexion.cerrarConexion();

        if (success == 1) {
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("Empleado Agregado");
            trayNotification.setMessage("El empleado se agrego satisfactoriamente");
            trayNotification.setNotificationType(NotificationType.SUCCESS);
            trayNotification.showAndDismiss(Duration.millis(3000));
        } else {
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("Verifique sus campos");
            trayNotification.setMessage("El empleado no se agrego satisfactoriamente");
            trayNotification.setNotificationType(NotificationType.ERROR);
            trayNotification.showAndDismiss(Duration.millis(3000));
        }
    }

    private void updateEmpleado(Empleado x, String codigoPostal) {
        var cod = codigoPostal.split(" ");

        var asenta = "";
        for (int i = 1; i < cod.length; i++) {
            if (i != cod.length - 1)
                asenta += cod[i] + " ";
            else
                asenta += cod[i];
        }


        conexion.establecerConexion();
        var success  = Empleado.updateEmpleado(conexion.getConection(), x,cod[0], asenta);
        conexion.cerrarConexion();

        if (success == 1) {
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("Empleado Editado");
            trayNotification.setMessage("El empleado se edito satisfactoriamente");
            trayNotification.setNotificationType(NotificationType.SUCCESS);
            trayNotification.showAndDismiss(Duration.millis(3000));
        } else {
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("Verifique sus campos");
            trayNotification.setMessage("El empleado no se edito satisfactoriamente");
            trayNotification.setNotificationType(NotificationType.ERROR);
            trayNotification.showAndDismiss(Duration.millis(3000));
        }
    }

}
