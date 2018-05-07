package com.controladores.productos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.modelo.Conexion;
import com.modelo.Producto;
import javafx.collections.ObservableList;
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

public class EditarProducto extends Stage {
    Conexion conexion = new Conexion();
    private Producto productoEditar;
    private ObservableList<Producto> listP;
    private int index;

    private TextField txtCod_barras;
    private TextField txtNombre;
    private TextField txtObservaciones;
    private TextField txtPrecioCompra;
    private TextField txtPrecioVenta;
    private TextField txtExistencia;
    public EditarProducto(ObservableList<Producto> listP,Producto producto,int index) {
        this.productoEditar = producto;
        this.listP = listP;
        this.index = index;
        var panePrincipal = new VBox();

        //Childs of panePrincipal
        var hBoxTitulo = new HBox();
        var hBoxCodigoPostal = new HBox();
        var hBoxBtnAceptar = new HBox();
        var lblEditar = new Label("Editar producto");



        var btnAceptar = new JFXButton("Aceptar");

        var gridPane = new GridPane();
        //Childs of gridPane
        var lblCod_barras = new Label("Codigo");
        var lblNombre = new Label("Nombre:");
        var lblObservaciones = new Label("Observaciones:");
        var lblPrecioCompra = new Label("Precio de Compra:");
        var lblPrecioVenta = new Label("Precio de venta:");
        var lblExistencia = new Label("Existencia:");
        txtCod_barras = new TextField();
        txtNombre = new TextField();
        txtObservaciones = new TextField();
        txtPrecioCompra = new TextField();
        txtPrecioVenta = new TextField();
        txtExistencia = new TextField();

        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();

        //Add childs
        //Column 1
        gridPane.add(lblCod_barras,0,0);
        gridPane.add(lblNombre,0,1);
        gridPane.add(lblPrecioCompra,0,2);
        gridPane.add(lblPrecioVenta,0,3);
        gridPane.add(lblExistencia,0,4);
        gridPane.add(lblObservaciones,0,5);
        //Column 2
        gridPane.add(txtCod_barras,1,0);
        gridPane.add(txtNombre,1,1);
        gridPane.add(txtPrecioCompra,1,2);
        gridPane.add(txtPrecioVenta,1,3);
        gridPane.add(txtExistencia,1,4);
        gridPane.add(txtObservaciones,1,5);

        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(5);

        //Add styles
        btnAceptar.getStyleClass().add("btnRaisedGreenDark");
        gridPane.getStyleClass().add("white");

        //Hboxes de ayuda
        hBoxTitulo.getChildren().add(lblEditar);
        //hBoxCodigoPostal.getChildren().add(lblCodPostal);
        hBoxBtnAceptar.getChildren().add(btnAceptar);

        hBoxTitulo.setPadding(new Insets(0,0,0,10));
        hBoxCodigoPostal.setPadding(new Insets(0,0,0,10));
        hBoxBtnAceptar.setAlignment(Pos.CENTER_RIGHT);

        //Estilos de los diversos Box
        hBoxTitulo.getStyleClass().add("white");
        hBoxCodigoPostal.getStyleClass().add("white");

        //Add childs to pane Principal
        panePrincipal.getChildren().addAll(hBoxTitulo, gridPane,hBoxBtnAceptar);

        //Properties of panePrincipal
        panePrincipal.setPadding(new Insets(10));
        VBox.setMargin(hBoxTitulo, new Insets(0,0,10,0));
        VBox.setMargin(gridPane, new Insets(0,0,10,0));
        VBox.setMargin(hBoxCodigoPostal, new Insets(0,0,25,0));

        //Metodo para llenar los campos de acuerdo al producto seleccionado
        rellenar();

        //Acciones de los botones
        btnAceptar.setOnAction(event ->{
            editar();
            this.close();
        });

        setResizable(false);
        Scene scene = new Scene(panePrincipal, 410,300);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/editar_producto.css").toExternalForm());
        show();
    }

    public void rellenar(){

        if(productoEditar!=null){
            txtCod_barras.setText(productoEditar.getCod_barra());
            txtCod_barras.setEditable(false);
            txtNombre.setText(productoEditar.getNombre());
            txtPrecioCompra.setText(String.valueOf(productoEditar.getPrecio_compra()));
            txtPrecioVenta.setText(String.valueOf(productoEditar.getPrecio_venta()));
            txtExistencia.setText(String.valueOf(productoEditar.getExistencia()));
            txtObservaciones.setText(productoEditar.getObservaciones());
        }else
            System.out.println("Producto es null");

    }


    private void editar() {

        String codigo_barras = txtCod_barras.getText();
        String nombre = txtNombre.getText();
        float precio_compra = Float.parseFloat(txtPrecioCompra.getText());
        float precio_venta = Float.parseFloat(txtPrecioVenta.getText());
        int existencia = Integer.parseInt(txtExistencia.getText());
        String observaciones = txtObservaciones.getText();

        Producto producto = new Producto(codigo_barras,nombre,observaciones,precio_compra,precio_venta,existencia);
        conexion.establecerConexion();
        int resultSuccess = producto.editarProducto(conexion.getConection());
        conexion.cerrarConexion();
        if (resultSuccess == 1) {
            listP.set(index,producto);
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("Completado");
            trayNotification.setMessage("Se edito exitosamente!");
            trayNotification.setNotificationType(NotificationType.SUCCESS);
            trayNotification.showAndDismiss(Duration.millis(2000));
        } else {
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("Algo salio mal...");
            trayNotification.setMessage("Intentelo de nuevo");
            trayNotification.setNotificationType(NotificationType.ERROR);
            trayNotification.showAndDismiss(Duration.millis(2000));

        }

    }


}
