package com.controladores.compras;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AgregarCompra extends Stage {
    HBox prodCompras = new HBox();

    AgregarCompra() {
        // list of products
        ObservableList<DemoProducto> productos = FXCollections.observableArrayList();
        productos.add(new DemoProducto("Llanta", 90.0));
        productos.add(new DemoProducto("Bujia", 50.0));
        productos.add(new DemoProducto("Aceite", 100.0));
        //Build tree
        final TreeItem<DemoProducto> root = new RecursiveTreeItem<>(productos, RecursiveTreeObject::getChildren);

        VBox box = new VBox();
        Label lblProveedor = new Label("Proveedor");
        Label lblFecha = new Label("Fecha");
        JFXComboBox<String> cbmProveedores = new JFXComboBox<>();
        JFXDatePicker picker = new JFXDatePicker();
        Label lblProductos = new Label("Productos de la compra");
        JFXTreeTableView<DemoProducto> tblProductos = new JFXTreeTableView(root);
        JFXTreeTableColumn<DemoProducto, String> clmNombre = new JFXTreeTableColumn("Nombre");
        JFXTreeTableColumn<DemoProducto, Double> clmPrecio = new JFXTreeTableColumn("Precio");
        Label lblMonto = new Label("Monto");
        Label lbl$Monto = new Label("$0.00");
        JFXButton btnAceptar = new JFXButton();
        JFXButton btnCerrar = new JFXButton();
        Text icoCerrar = GlyphsDude.createIcon(FontAwesomeIcon.CLOSE);
        Text icoAceptar = GlyphsDude.createIcon(FontAwesomeIcon.CHECK);
        GridPane pane1 = new GridPane();
        GridPane pane2 = new GridPane();
        GridPane paneCerrar = new GridPane();
        GridPane paneAceptar = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints(10,100,Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(10,100,Double.MAX_VALUE);
        column1.setHgrow(Priority.SOMETIMES);
        column2.setHgrow(Priority.SOMETIMES);

        //Properties of nodes
        cbmProveedores.setPrefWidth(200);
        lbl$Monto.setPrefWidth(200);
        lbl$Monto.setAlignment(Pos.CENTER_RIGHT);
        btnAceptar.setRipplerFill(Color.web("#90708c"));
        btnAceptar.getStyleClass().addAll("btnAceptar", "button-raised");
        btnAceptar.setGraphic(icoAceptar);
        btnAceptar.setStyle("-fx-pref-width: 90 !important;");
        btnCerrar.setRipplerFill(Color.web("#90708c"));
        btnCerrar.getStyleClass().addAll("btnCerrar", "button-raised");
        btnCerrar.setGraphic(icoCerrar);
        lblProveedor.getStyleClass().add("font");
        lblFecha.getStyleClass().add("font");
        lblProductos.getStyleClass().add("font");
        lblMonto.getStyleClass().add("font");
        lbl$Monto.getStyleClass().add("font");
        icoCerrar.getStyleClass().add("ico");
        icoAceptar.getStyleClass().add("ico");

        //Pane 1 properties
        GridPane.setConstraints(lblProveedor,0,0);
        GridPane.setConstraints(lblFecha,0,1);
        GridPane.setConstraints(cbmProveedores,1,0);
        GridPane.setConstraints(picker,1,1);
        pane1.getColumnConstraints().addAll(column1, column2);
        pane1.getChildren().addAll(lblProveedor, lblFecha, cbmProveedores, picker);
        pane1.setVgap(5);
        pane1.setPadding(new Insets(5));
        pane1.getStyleClass().add("white");
        VBox.setMargin(pane1, new Insets(10,0,10,0));

        // Pane 2 properties
        GridPane.setConstraints(lblMonto, 0,0);
        GridPane.setConstraints(lbl$Monto,1,0);
        pane2.getChildren().addAll(lblMonto, lbl$Monto);
        pane2.getColumnConstraints().addAll(column1, column2);
        pane2.setPadding(new Insets(5));
        pane2.getStyleClass().add("white");
        VBox.setMargin(pane2, new Insets(10,0,10,0));

        //paneCerrar & paneAceptar
        GridPane.setConstraints(btnCerrar,0,0);
        GridPane.setConstraints(btnAceptar,0,0);
        GridPane.setHalignment(btnCerrar, HPos.RIGHT);
        GridPane.setHalignment(btnAceptar, HPos.RIGHT);
        paneCerrar.getColumnConstraints().add(column1);
        paneCerrar.getChildren().add(btnCerrar);
        paneAceptar.getColumnConstraints().add(column1);
        paneAceptar.getChildren().add(btnAceptar);


        //box properties
        VBox.setMargin(lblProductos, new Insets(0,0,5,0));
        VBox.setMargin(tblProductos, new Insets(0,0,10,0));
        VBox.setMargin(btnCerrar, new Insets(0,0,0,315));
        VBox.setMargin(btnAceptar, new Insets(0,0,0,310));
        box.setPadding(new Insets(25));
        box.getChildren().addAll(paneCerrar, pane1, lblProductos, tblProductos, pane2, paneAceptar);

         //      TABLE   PROPERTIES         //
        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(260);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<DemoProducto, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombre;
            else
                return clmNombre.getComputedValue(param);
        });

        clmPrecio.setResizable(false);
        clmPrecio.setPrefWidth(118);
        clmPrecio.setCellValueFactory((TreeTableColumn.CellDataFeatures<DemoProducto, Double> param) -> {
            if (clmPrecio.validateValue(param))
                return param.getValue().getValue().precio.asObject();
            else
                return clmPrecio.getComputedValue(param);
        });

        tblProductos.setEditable(false);
        tblProductos.setShowRoot(false);
        tblProductos.getColumns().setAll(clmNombre, clmPrecio);

        //      END TABLE                  //

        //actions
        btnCerrar.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                this.close();
        });

        btnAceptar.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                this.close();
        });

        // Stage properties
        Scene scene = new Scene(box,430,524);
        setResizable(false);
        setScene(scene);
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/opcion_compra.css").toExternalForm());
        show();
    }

}


class DemoProducto extends RecursiveTreeObject<DemoProducto> {
    StringProperty nombre;
    DoubleProperty precio;

    public DemoProducto(String nombre, Double precio) {
        this.nombre =  new SimpleStringProperty(nombre);
        this.precio = new SimpleDoubleProperty(precio);
    }
}
