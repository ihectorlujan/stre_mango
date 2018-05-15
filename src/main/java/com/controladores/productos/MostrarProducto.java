package com.controladores.productos;

import com.jfoenix.controls.JFXButton;
import com.modelo.Producto;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MostrarProducto extends Stage {
    private Producto productoMostrar;
    private Label txtCod_barras;
    private Label txtNombre;
    private Text txtObservaciones;
    private Label txtPrecioCompra;
    private Label txtPrecioVenta;
    private Label txtExistencia;


    public MostrarProducto(Producto producto){
        setTitle(producto.getNombre());
        productoMostrar = producto;
        var panePrincipal = new VBox();

        //Childs of panePrincipal
        var hBoxTitulo = new HBox();
        var hBoxCodigoPostal = new HBox();
        var hBoxBtnAceptar = new HBox();


        var btnAceptar = new JFXButton("Aceptar");

        var gridPane = new GridPane();
        //Childs of gridPane
        var lblCod_barras = new Label("Codigo");
        var lblNombre = new Label("Nombre:");
        var lblObservaciones = new Label("Observaciones:");
        var lblPrecioCompra = new Label("Precio de Compra:");
        var lblPrecioVenta = new Label("Precio de venta:");
        var lblExistencia = new Label("Existencia:");


        txtCod_barras = new Label();
        txtNombre = new Label();
        txtObservaciones = new Text();
        txtPrecioCompra = new Label();
        txtPrecioVenta = new Label();
        txtExistencia = new Label();



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
        //this is dumb
        lblCod_barras.getStyleClass().add("labelPrinc");
        lblNombre.getStyleClass().add("labelPrinc");
        lblPrecioCompra.getStyleClass().add("labelPrinc");
        lblPrecioVenta.getStyleClass().add("labelPrinc");
        lblObservaciones.getStyleClass().add("labelPrinc");
        lblExistencia.getStyleClass().add("labelPrinc");

        txtCod_barras.getStyleClass().add("labelSub");
        txtNombre.getStyleClass().add("labelSub");
        txtPrecioCompra.getStyleClass().add("labelSub");
        txtPrecioVenta.getStyleClass().add("labelSub");
        txtObservaciones.getStyleClass().add("labelSub");
        txtExistencia.getStyleClass().add("labelSub");


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
            this.close();
        });

        panePrincipal.getStyleClass().add("white");
        Scene scene = new Scene(panePrincipal, 510,270);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/mostrar_producto.css").toExternalForm());
        show();
    }

    public void rellenar(){

        if(productoMostrar!=null){
            txtCod_barras.setText(productoMostrar.getCod_barra());
            txtNombre.setText(productoMostrar.getNombre());
            txtPrecioCompra.setText(String.valueOf(productoMostrar.getPrecio_compra()));
            txtPrecioVenta.setText(String.valueOf(productoMostrar.getPrecio_venta()));
            txtExistencia.setText(String.valueOf(productoMostrar.getExistencia()));
            txtObservaciones.setText(productoMostrar.getObservaciones());
        }else
            System.out.println("Producto es null");

    }

}
