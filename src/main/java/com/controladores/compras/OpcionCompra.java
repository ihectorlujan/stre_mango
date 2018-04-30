package com.controladores.compras;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.modelo.compra.Compra;
import com.modelo.Conexion;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Comparator;

public class OpcionCompra extends VBox{
    Conexion connection = new Conexion();

    public OpcionCompra() {
        HBox primerContenedor = new HBox();
        HBox opcionesCompra = opciones();
        JFXListView<Compra> listaUltimasCompras = new JFXListView<>();
        ObservableList<Compra> ultimasCompras = FXCollections.observableArrayList();
        HBox txtUltimasCompras = ultimasCompras();
        VBox masComprados = prodComprados();
        VBox grafica = grafica();

        //Ultimas compras, Y mas Vendidos
        connection.establecerConexion();
        Compra.llenarCompras(connection.getConection(), ultimasCompras);
        connection.cerrarConexion();

        ultimasCompras.stream().limit(8).forEach(x -> listaUltimasCompras.getItems().add(x));

        listaUltimasCompras.getStyleClass().add("jfx-list-cell");


        //
        primerContenedor.getChildren().addAll(grafica, masComprados);
        primerContenedor.getStyleClass().add("white");
        VBox.setMargin(primerContenedor, new Insets(10,10,0,10));
        VBox.setMargin(listaUltimasCompras, new Insets(0,10,10,10));
        VBox.setMargin(opcionesCompra, new Insets(10,10,10,10));
        VBox.setMargin(txtUltimasCompras, new Insets(10,10,10,10));
        HBox.setHgrow(masComprados, Priority.ALWAYS);


        this.getChildren().addAll(primerContenedor, opcionesCompra, txtUltimasCompras, listaUltimasCompras);
        this.getStylesheets().add(getClass().getResource("/estilos/compras.css").toExternalForm());
    }

    private VBox grafica() {
        VBox box = new VBox();
        Text txtMes = new Text("Ultimas 8 Compras");
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> grafica = new BarChart<>(xAxis, yAxis);
        ObservableList<Compra> listCompra = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        xAxis.setLabel("ID");
        yAxis.setLabel("Monto");
        grafica.setLegendVisible(false);

        connection.establecerConexion();
        Compra.llenarCompras(connection.getConection(), listCompra);
        connection.cerrarConexion();

        listCompra.sort(Comparator.comparing(Compra::getFecha).reversed());

        listCompra.stream().limit(8).forEach(c ->
                series.getData().add(new XYChart.Data<>(c.getId() + "", c.getMonto()))
        );

        grafica.getData().add(series);

        //
        grafica.setPrefHeight(230);
        txtMes.getStyleClass().add("text");
        VBox.setMargin(txtMes, new Insets(5,0,5,10));

        box.getChildren().addAll(txtMes, grafica);

        return box;
    }

    private VBox prodComprados() {
        VBox box = new VBox();
        Text txtProductos = new Text("Productos mas comprados");
        JFXListView<String> lista = new JFXListView<>();
        lista.getStyleClass().add("jfx-list-cell");

        //Establecer la conexion, obtener los datos y cerrar la conexion
        connection.establecerConexion();
        var listMasComprados = Compra.getMasComprados(connection.getConection());
        connection.cerrarConexion();

        for (String x:listMasComprados)
            lista.getItems().add(x);

            //Expanded List
            //lista.setVerticalGap(10.0);
            //lista.setExpanded(true);
        txtProductos.setTextAlignment(TextAlignment.CENTER);
        txtProductos.getStyleClass().add("text");
        VBox.setMargin(lista, new Insets(0,5,5,5));
        VBox.setMargin(txtProductos, new Insets(5,0,5,10));

        box.getChildren().addAll(txtProductos, lista);
        return box;
    }

    private HBox opciones() {
        HBox box = new HBox();
        HBox textBox = new HBox();
        JFXButton btnAddCompra = new JFXButton();
        JFXButton btnHistorial = new JFXButton();
        Text icoCompra = GlyphsDude.createIcon(FontAwesomeIcon.PLUS);
        Text icoHistorial = GlyphsDude.createIcon(FontAwesomeIcon.HISTORY);
        Text text = new Text("Opciones");

        //Buttons
        btnAddCompra.setGraphic(icoCompra);
        btnHistorial.setGraphic(icoHistorial);
        btnAddCompra.setRipplerFill(Color.web("#90708c"));
        btnHistorial.setRipplerFill(Color.web("#90708c"));
        icoCompra.getStyleClass().add("ico");
        icoHistorial.getStyleClass().add("ico");

        //Styles
        btnAddCompra.getStyleClass().add("button-raised");
        btnHistorial.getStyleClass().add("button-raised");
        text.getStyleClass().add("text");
        box.getStyleClass().add("white");

        //Acciones
        btnAddCompra.setOnAction(e -> new AgregarCompra());

        btnHistorial.setOnAction(e -> new HistorialCompras());

        btnAddCompra.setTooltip(new Tooltip("Agregar una nueva compra"));
        btnHistorial.setTooltip(new Tooltip("Ver el historial de compras"));

        HBox.setMargin(btnAddCompra, new Insets(0,5,0,5));
        HBox.setMargin(btnHistorial, new Insets(0,5,0,5));
        HBox.setMargin(textBox, new Insets(0,0,0,10));

        textBox.getChildren().add(text);
        HBox.setHgrow(textBox, Priority.ALWAYS);
        textBox.setAlignment(Pos.CENTER_LEFT);
        box.getChildren().addAll(textBox,btnAddCompra, btnHistorial);
        box.setAlignment(Pos.CENTER_RIGHT);
        return box;
    }

    private HBox ultimasCompras() {
        HBox box = new HBox();
        Text text = new Text("Ultimas 8 compras");

        //
        HBox.setHgrow(text, Priority.ALWAYS);
        text.getStyleClass().add("text");
        HBox.setMargin(text, new Insets(0,0,0,10));

        box.getChildren().add(text);
        box.getStyleClass().add("white");

        return box;
    }
}
