package com.controladores.empleados;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AgregarEmpleado extends Stage {

    public AgregarEmpleado() {
        var panePrincipal = new VBox();

        //Childs of panePrincipal
        var hBoxTitulo = new HBox();
        var hBoxCodigoPostal = new HBox();
        var hBoxBntAgregar = new HBox();
        var lblAgregar = new Label("Agregar un empleado");
        var lblCodPostal = new Label("Codigo Postal:");
        var btnAgregar = new JFXButton("Agregar");
        var gridPane = new GridPane();
            //Childs of gridPane
        var lblNombre = new Label("Nombre:");
        var lblApellido = new Label("Apellido:");
        var lblEdad = new Label("Edad:");
        var lblSexo = new Label("Sexo:");
        var lblTelefono = new Label("Telefono:");
        var lblCorreo = new Label("Correo:");
        var lblCalle = new Label("Calle:");
        var lblNoCasa = new Label("No de casa:");
        var txtNombre = new TextField();
        var txtApellido = new TextField();
        var txtEdad = new TextField();
        var txtTelefono = new TextField();
        var txtCorreo = new TextField();
        var txtCalle = new TextField();
        var txtNoCasa = new TextField();
        var hBoxSexo = new HBox();
        var rbHombre = new JFXRadioButton("Hombre");
        var rbMujer = new JFXRadioButton("Mujer");
        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();

        //Add childs
            //Column 1
        gridPane.add(lblNombre,0,0);
        gridPane.add(lblApellido,0,1);
        gridPane.add(lblEdad,0,2);
        gridPane.add(lblSexo,0,3);
        gridPane.add(lblTelefono,0,4);
        gridPane.add(lblCorreo,0,5);
        gridPane.add(lblCalle,0,6);
        gridPane.add(lblNoCasa,0,7);
            //Column 2
        gridPane.add(txtNombre,1,0);
        gridPane.add(txtApellido,1,1);
        gridPane.add(txtEdad,1,2);
        gridPane.add(hBoxSexo,1,3);
        gridPane.add(txtTelefono,1,4);
        gridPane.add(txtCorreo,1,5);
        gridPane.add(txtCalle,1,6);
        gridPane.add(txtNoCasa,1,7);

        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //HBox Sexo
        hBoxSexo.getChildren().addAll(rbHombre, rbMujer);
        HBox.setMargin(rbHombre, new Insets(0,10,0,0));

        //Add styles
        btnAgregar.getStyleClass().add("btnRaisedBlue");
        gridPane.getStyleClass().add("white");

        //Hboxes de ayuda
        hBoxTitulo.getChildren().add(lblAgregar);
        hBoxCodigoPostal.getChildren().add(lblCodPostal);
        hBoxBntAgregar.getChildren().add(btnAgregar);

        hBoxTitulo.setPadding(new Insets(0,0,0,10));
        hBoxCodigoPostal.setPadding(new Insets(0,0,0,10));
        hBoxBntAgregar.setAlignment(Pos.CENTER_RIGHT);

        //Estilos de los diversos Box
        hBoxTitulo.getStyleClass().add("white");
        hBoxCodigoPostal.getStyleClass().add("white");

        //Add childs to pane Principal
        panePrincipal.getChildren().addAll(hBoxTitulo, gridPane, hBoxCodigoPostal, hBoxBntAgregar);

        //Properties of panePrincipal
        panePrincipal.setPadding(new Insets(10));
        VBox.setMargin(hBoxTitulo, new Insets(0,0,10,0));
        VBox.setMargin(gridPane, new Insets(0,0,10,0));
        VBox.setMargin(hBoxCodigoPostal, new Insets(0,0,25,0));

        //Acciones de los botones
        btnAgregar.setOnAction(this::noFunciona);

        setResizable(false);
        Scene scene = new Scene(panePrincipal, 410,420);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/agregar_empleado.css").toExternalForm());
        show();
    }

    private void noFunciona(ActionEvent e) {
        TrayNotification trayNotification = new TrayNotification();
        trayNotification.setTitle("Verifique");
        trayNotification.setMessage("Esta opcion no esta disponible");
        trayNotification.setNotificationType(NotificationType.NOTICE);
        trayNotification.showAndDismiss(Duration.millis(3000));
    }

}
