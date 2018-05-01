package com.controladores.empleados;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class OpcionEmpleado extends VBox {

    public OpcionEmpleado() {
        var bottomInsets = new Insets(0,0,10,0);
        var txtDatos = new Label("Datos Personales");
        var gridPane = new GridPane();
        var lblNombre = new Label("Nombre:");
        var lblApellido = new Label("Apellido:");
        var lblEdad = new Label("Edad:");
        var lblTelefono = new Label("Telefono:");
        var lblCorreo = new Label("Correo:");
        var lblEstado = new Label("Estado:");
        var lblNombreCalle = new Label("Calle:");
        var lblNoCasa = new Label("No de casa:");
        var lblCodigoPostal = new Label("Codigo Postal:");
        var lblAsentamiento = new Label("Asentamiento:");
        var lblCiudad = new Label("Ciudad:");
        var lblMunicipio = new Label("Municipio:");
        var lblBuscar = new Label("Buscar:");
        var txtNombre = new TextField();
        var txtApellido = new TextField();
        var txtEdad = new TextField();
        var txtTelefono = new TextField();
        var txtCorreo = new TextField();
        var txtEstado = new TextField();
        var txtNombreCalle = new TextField();
        var txtNoCasa = new TextField();
        var txtCodPostal = new TextField();
        var txtAsentamiento = new TextField();
        var txtCiudad = new TextField();
        var txtMunicipio = new TextField();
        var hBox = new HBox();
        var hBoxSearch = new HBox();
        var hBoxDatos = new HBox();
        var btnAnadir = new JFXButton();
        var btnEditar = new JFXButton();
        var btnEliminar = new JFXButton();
        var icoEdit = GlyphsDude.createIcon(FontAwesomeIcon.EDIT,"14px");
        var icoAdd = GlyphsDude.createIcon(FontAwesomeIcon.USER_PLUS,"14px");
        var icoDelete = GlyphsDude.createIcon(FontAwesomeIcon.TRASH_ALT,"14px");
        var txtFiltro = new TextField();
        ObservableList<Empleado> listEmpleados = FXCollections.observableArrayList();

        //Primer box and txtFiltro
        hBoxDatos.getChildren().add(txtDatos);
        hBoxDatos.setPadding(new Insets(0,0,0,5));
        hBoxDatos.getStyleClass().add("panelWhite");
        txtFiltro.setPromptText("ID o Nombre");
        txtFiltro.setPrefWidth(180);

        //Buttons and Icons
        setStyleIcons(icoAdd, icoDelete, icoEdit);
        btnAnadir.setGraphic(icoAdd);
        btnEditar.setGraphic(icoEdit);
        btnEliminar.setGraphic(icoDelete);

        //GridPane
            //Columns
        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();
        var column3 = new ColumnConstraints();
        var column4 = new ColumnConstraints();

        gridPane.add(lblNombre,0,0);
        gridPane.add(lblApellido,0,1);
        gridPane.add(lblEdad,0,2);
        gridPane.add(lblTelefono,0,3);
        gridPane.add(lblCorreo,0,4);
        gridPane.add(lblEstado,0,5);

        gridPane.add(txtNombre,1,0);
        gridPane.add(txtApellido,1,1);
        gridPane.add(txtEdad,1,2);
        gridPane.add(txtTelefono,1,3);
        gridPane.add(txtCorreo,1,4);
        gridPane.add(txtEstado,1,5);

        gridPane.add(lblNombreCalle,2,0);
        gridPane.add(lblNoCasa,2,1);
        gridPane.add(lblCodigoPostal,2,2);
        gridPane.add(lblAsentamiento,2,3);
        gridPane.add(lblCiudad,2,4);
        gridPane.add(lblMunicipio,2,5);

        gridPane.add(txtNombreCalle, 3,0);
        gridPane.add(txtNoCasa, 3,1);
        gridPane.add(txtCodPostal, 3,2);
        gridPane.add(txtAsentamiento, 3,3);
        gridPane.add(txtCiudad, 3,4);
        gridPane.add(txtMunicipio, 3,5);

        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));

        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(4);
        gridPane.getStyleClass().add("panelWhite");
        blockGridPaneFields(gridPane);


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
        var table = createTable(listEmpleados);


        VBox.setMargin(hBoxDatos, bottomInsets);
        VBox.setMargin(txtDatos, bottomInsets);
        VBox.setMargin(gridPane, bottomInsets);
        VBox.setMargin(hBox, bottomInsets);

        setPadding(new Insets(10));
        getChildren().addAll(hBoxDatos, gridPane, hBox, table);
        getStylesheets().add(getClass().getResource("/estilos/empleados.css").toExternalForm());
    }


    private JFXTreeTableView createTable(ObservableList<Empleado> list) {
        final TreeItem<Empleado> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        var tableView = new JFXTreeTableView<>(root);

        var clmNombre = new JFXTreeTableColumn<Empleado, String>("Nombre");
        var clmApellido = new JFXTreeTableColumn<Empleado, String>("Apellido");
        var clmTelefono = new JFXTreeTableColumn<Empleado, String>("Telefono");
        var clmCorreo = new JFXTreeTableColumn<Empleado, String>("Correo");

        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(195);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmApellido.setResizable(false);
        clmApellido.setPrefWidth(195);
        clmApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmApellido.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmApellido.getComputedValue(param);
        });

        clmTelefono.setResizable(false);
        clmTelefono.setPrefWidth(180);
        clmTelefono.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmTelefono.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmTelefono.getComputedValue(param);
        });

        clmCorreo.setResizable(false);
        clmCorreo.setPrefWidth(190);
        clmCorreo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmCorreo.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmCorreo.getComputedValue(param);
        });

        tableView.setShowRoot(false);
        tableView.setEditable(false);
        tableView.getColumns().setAll(clmNombre, clmApellido, clmTelefono, clmCorreo);

        return tableView;
    }

    private void blockGridPaneFields(GridPane gridPane) {
        gridPane.getChildren()
                .filtered(x -> x instanceof TextField)
                .forEach(x -> ((TextField) x).setEditable(false));
    }

    private void setStyleIcons(Text... text) {
        for(Text t: text)
            t.getStyleClass().add("ico");
    }
}
