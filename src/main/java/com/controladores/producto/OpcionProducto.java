package com.controladores.producto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Producto;
import com.modelo.empleado.Empleado;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OpcionProducto extends VBox {

    public OpcionProducto() {
        var lblDatos = new Label("Datos del producto");
        var hBoxDatos = new HBox();
        var lblCodBarra = new Label("Codigo de barra:");
        var lblNombre = new Label("Nombre:");
        var lblPrecioCompra = new Label("Precio de compra:");
        var lblPrecioVenta = new Label("Precio de venta:");
        var lblExistencia = new Label("Existencia:");
        var lblCantidadDefectuoso = new Label("Defectuosos:");
        var lblObervaciones  = new Label("Observaciones:");
        var lblBuscar = new Label("Buscar:");
        var txtCodBarra = new TextField();
        var txtNombre = new TextField();
        var txtPrecioCompra = new TextField();
        var txtPrecioVenta = new TextField();
        var txtExistencia = new TextField();
        var txtCanDefectuoso = new TextField();
        var txtObservaciones = new TextField();
        var gridPane = new GridPane();
        var btnAnadir = new JFXButton();
        var btnEditar = new JFXButton();
        var btnEliminar = new JFXButton();
        var icoEdit = GlyphsDude.createIcon(FontAwesomeIcon.EDIT,"14px");
        var icoAdd = GlyphsDude.createIcon(FontAwesomeIcon.USER_PLUS,"14px");
        var icoDelete = GlyphsDude.createIcon(FontAwesomeIcon.TRASH_ALT,"14px");
        var txtFiltro = new TextField();
        var hBox = new HBox();
        var hBoxSearch = new HBox();
        ObservableList<Producto> listProductos = FXCollections.observableArrayList();

        //Hbox
        hBoxDatos.getChildren().add(lblDatos);
        hBoxDatos.getStyleClass().add("panelWhite");
        hBoxDatos.setPadding(new Insets(0,0,0,5));

        //Buttons and Icons, filtro
        setStyleIcons(icoAdd, icoDelete, icoEdit);
        btnAnadir.setGraphic(icoAdd);
        btnEditar.setGraphic(icoEdit);
        btnEliminar.setGraphic(icoDelete);
        txtFiltro.setPromptText("Codigo de barra");

        //GridPane
        gridPane.add(lblCodBarra,0,0);
        gridPane.add(lblNombre,0,1);
        gridPane.add(lblPrecioCompra,0,2);
        gridPane.add(lblObervaciones,0,3);
        gridPane.add(txtCodBarra,1,0);
        gridPane.add(txtNombre,1,1);
        gridPane.add(txtPrecioCompra,1,2);
        gridPane.add(txtObservaciones,1,3);
        gridPane.add(lblPrecioVenta,2,0);
        gridPane.add(lblExistencia,2,1);
        gridPane.add(lblCantidadDefectuoso,2,2);
        gridPane.add(txtPrecioVenta,3,0);
        gridPane.add(txtExistencia,3,1);
        gridPane.add(txtCanDefectuoso,3,2);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(4);
        gridPane.getStyleClass().add("panelWhite");

        //HBox and hBoxSearch
        hBoxSearch.getChildren().addAll(lblBuscar,txtFiltro);
        hBoxSearch.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(lblBuscar, new Insets(0,10,0,0));

        hBox.getChildren().addAll(hBoxSearch, btnAnadir, btnEditar, btnEliminar);
        hBox.getStyleClass().add("panelWhite");
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5));
        HBox.setMargin(btnAnadir, new Insets(0,10,0,10));
        HBox.setMargin(btnEliminar, new Insets(0,10,0,10));
        HBox.setHgrow(hBoxSearch, Priority.ALWAYS);

        //Table
        var table = createTable(listProductos);


        //VBox
        setPadding(new Insets(10));
        getChildren().addAll(hBoxDatos, gridPane, hBox,table);
        VBox.setMargin(hBoxDatos, new Insets(0,0,5,0));
        VBox.setMargin(gridPane, new Insets(0,0,10,0));
        VBox.setMargin(hBox, new Insets(0,0,10,0));
        getStylesheets().add(getClass().getResource("/estilos/productos.css").toExternalForm());
    }

    private void setStyleIcons(Text... text) {
        for(Text t: text)
            t.getStyleClass().add("ico");
    }

    private JFXTreeTableView createTable(ObservableList<Producto> list) {
        final TreeItem<Producto> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        var tableView = new JFXTreeTableView<>(root);

        var clmCodBarra = new JFXTreeTableColumn<Producto, String>("Codigo de barra");
        var clmNombre = new JFXTreeTableColumn<Producto, String>("Nombre");
        var clm$Venta = new JFXTreeTableColumn<Producto, Float>("Precio Venta");
        var clm$Compra = new JFXTreeTableColumn<Producto, Float>("Precio Compra");

        clmCodBarra.setResizable(false);
        clmCodBarra.setPrefWidth(200);
        clmCodBarra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, String> param) -> {
            if (clmCodBarra.validateValue(param))
                return param.getValue().getValue().cod_barraProperty();
            else
                return clmCodBarra.getComputedValue(param);
        });

        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(200);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clm$Venta.setResizable(false);
        clm$Venta.setPrefWidth(190);
        clm$Venta.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Float> param) -> {
            if (clm$Venta.validateValue(param))
                return param.getValue().getValue().precio_ventaProperty().asObject();
            else
                return clm$Venta.getComputedValue(param);
        });

        clm$Compra.setResizable(false);
        clm$Compra.setPrefWidth(250);
        clm$Compra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Float> param) -> {
            if (clm$Compra.validateValue(param))
                return param.getValue().getValue().precio_compraProperty().asObject();
            else
                return clm$Compra.getComputedValue(param);
        });


        tableView.setShowRoot(false);
        tableView.setEditable(false);
        tableView.getColumns().setAll(clmCodBarra, clmNombre, clm$Venta, clm$Compra);

        return tableView;
    }


}
