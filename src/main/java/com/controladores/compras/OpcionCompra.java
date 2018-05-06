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

    private Conexion connection = new Conexion();

    public OpcionCompra() {
        //Paneles
        var hBoxPrimerContenedor = new HBox();
        var hBoxOpcionesCompra = opciones();
        var hBoxUltimasCompras = ultimasCompras();
        var vBoxMasComprados = prodComprados();
        var vBoxGrafica = grafica();

        JFXListView<Compra> listaUltimasCompras = new JFXListView<>();
        ObservableList<Compra> ultimasCompras = FXCollections.observableArrayList();


        //Ultimas compras, Y mas Vendidos
        connection.establecerConexion();
        Compra.llenarCompras(connection.getConection(), ultimasCompras);
        connection.cerrarConexion();

        //Anadir 8 elementos a la lista de ultimas compras
        ultimasCompras.stream().limit(8).forEach(x -> listaUltimasCompras.getItems().add(x));

        //Estilos
        listaUltimasCompras.getStyleClass().add("jfx-list-cell");
        hBoxPrimerContenedor.getStyleClass().add("white");


        hBoxPrimerContenedor.getChildren().addAll(vBoxGrafica, vBoxMasComprados);
        VBox.setMargin(hBoxPrimerContenedor, new Insets(10,10,0,10));
        VBox.setMargin(listaUltimasCompras, new Insets(0,10,10,10));
        VBox.setMargin(hBoxOpcionesCompra, new Insets(10,10,10,10));
        VBox.setMargin(hBoxUltimasCompras, new Insets(10,10,10,10));
        HBox.setHgrow(vBoxMasComprados, Priority.ALWAYS);

        this.getChildren().addAll(hBoxPrimerContenedor, hBoxOpcionesCompra, hBoxUltimasCompras, listaUltimasCompras);
        this.getStylesheets().add(getClass().getResource("/estilos/compras.css").toExternalForm());

    }

    private VBox grafica() {
        var box = new VBox();
        var txtMes = new Text("Ultimas 8 Compras");

        //Grafica
        var xAxis = new CategoryAxis();
        var yAxis = new NumberAxis();
        BarChart<String, Number> grafica = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        ObservableList<Compra> listCompra = FXCollections.observableArrayList();

        xAxis.setLabel("ID");
        yAxis.setLabel("Monto");
        grafica.setLegendVisible(false);

        connection.establecerConexion();
        Compra.llenarCompras(connection.getConection(), listCompra);
        connection.cerrarConexion();

        listCompra.sort(Comparator.comparing(Compra::getFecha).reversed());
        listCompra.stream().limit(8).forEach(c -> series.getData().add(new XYChart.Data<>(c.getId() + "", c.getMonto())));

        grafica.getData().add(series);

        grafica.setPrefHeight(230);
        txtMes.getStyleClass().add("text");
        VBox.setMargin(txtMes, new Insets(5,0,5,10));

        box.getChildren().addAll(txtMes, grafica);

        return box;
    }

    private VBox prodComprados() {
        var box = new VBox();
        var txtProductos = new Text("Productos mas comprados");

        JFXListView<String> lista = new JFXListView<>();

        //Estilos
        lista.getStyleClass().add("jfx-list-cell");
        txtProductos.getStyleClass().add("text");

        //Establecer la conexion, obtener los datos y cerrar la conexion
        connection.establecerConexion();
        var listMasComprados = Compra.getMasComprados(connection.getConection());
        connection.cerrarConexion();

        for (String x:listMasComprados)
            lista.getItems().add(x);

        txtProductos.setTextAlignment(TextAlignment.CENTER);
        VBox.setMargin(lista, new Insets(0,5,5,5));
        VBox.setMargin(txtProductos, new Insets(5,0,5,10));

        box.getChildren().addAll(txtProductos, lista);
        return box;
    }

    private HBox opciones() {
        var box = new HBox();
        var textBox = new HBox();
        var btnAddCompra = new JFXButton();
        var btnHistorial = new JFXButton();
        var icoCompra = GlyphsDude.createIcon(FontAwesomeIcon.PLUS);
        var icoHistorial = GlyphsDude.createIcon(FontAwesomeIcon.HISTORY);
        var text = new Text("Opciones");

        //Buttons
        btnAddCompra.setGraphic(icoCompra);
        btnHistorial.setGraphic(icoHistorial);
        btnAddCompra.setRipplerFill(Color.web("#90708c"));
        btnHistorial.setRipplerFill(Color.web("#90708c"));

        //Styles
        btnAddCompra.getStyleClass().add("button-raised");
        btnHistorial.getStyleClass().add("button-raised");
        text.getStyleClass().add("text");
        box.getStyleClass().add("white");
        icoCompra.getStyleClass().add("ico");
        icoHistorial.getStyleClass().add("ico");

        //Eventos
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
        var box = new HBox();
        var text = new Text("Ultimas 8 compras");

        //Estilos
        text.getStyleClass().add("text");
        box.getStyleClass().add("white");

        HBox.setHgrow(text, Priority.ALWAYS);
        HBox.setMargin(text, new Insets(0,0,0,10));

        box.getChildren().add(text);

        return box;
    }
}
