package com.controladores.compras;

import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class OpcionCompra extends VBox{

    public OpcionCompra() {
        HBox primerContenedor = new HBox();
        HBox opcionesCompra = opciones();
        JFXListView<String> listaUltimasCompras = new JFXListView<>();
        HBox txtUltimasCompras = ultimasCompras();
        VBox masComprados = prodComprados();
        VBox grafica = grafica();

        //Ultimas compras
        for (int i = 0; i < 5; i++)
            listaUltimasCompras.getItems().add("22/02/2028\t" + "proveedor\t" + "monto: " + "$00.00");
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
        Text txtMes = new Text("Marzo");
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> grafica = new BarChart<>(xAxis, yAxis);

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
        JFXListView<Label> lista = new JFXListView<>();
        lista.getStyleClass().add("jfx-list-cell");

        for (int i = 0; i < 5; i++)
            lista.getItems().add(new Label("producto " + i));

        //
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
        Text addCompra = GlyphsDude.createIcon(FontAwesomeIcon.PLUS);
        Text historial = GlyphsDude.createIcon(FontAwesomeIcon.HISTORY);
        Text text = new Text("Opciones");

        //Styles
        addCompra.getStyleClass().add("ico");
        historial.getStyleClass().add("ico");
        text.getStyleClass().add("text");
        box.getStyleClass().add("white");

        addCompra.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                new AgregarCompra();
        });

        HBox.setMargin(addCompra, new Insets(0,5,0,5));
        HBox.setMargin(historial, new Insets(0,5,0,5));
        HBox.setMargin(textBox, new Insets(0,0,0,10));

        textBox.getChildren().add(text);
        HBox.setHgrow(textBox, Priority.ALWAYS);
        textBox.setAlignment(Pos.CENTER_LEFT);
        box.getChildren().addAll(textBox,addCompra, historial);
        box.setAlignment(Pos.CENTER_RIGHT);
        return box;
    }

    private HBox ultimasCompras() {
        HBox box = new HBox();
        Text text = new Text("Ultimas compras");

        //
        HBox.setHgrow(text, Priority.ALWAYS);
        text.getStyleClass().add("text");
        HBox.setMargin(text, new Insets(0,0,0,10));

        box.getChildren().add(text);
        box.getStyleClass().add("white");

        return box;
    }
}
