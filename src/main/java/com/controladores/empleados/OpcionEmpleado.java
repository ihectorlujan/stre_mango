package com.controladores.empleados;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Conexion;
import com.modelo.empleado.Empleado;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class OpcionEmpleado extends VBox {

    Conexion connection = new Conexion();

    TextField txtNombre = new TextField();
    TextField txtApellido = new TextField();
    TextField txtEdad = new TextField();
    TextField txtTelefono = new TextField();
    TextField txtCorreo = new TextField();
    TextField txtEstado = new TextField();
    TextField txtNombreCalle = new TextField();
    TextField txtNoCasa = new TextField();
    TextField txtCodPostal = new TextField();
    TextField txtAsentamiento = new TextField();
    TextField txtCiudad = new TextField();
    TextField txtMunicipio = new TextField();
    IntegerProperty id = new SimpleIntegerProperty();

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
        ObservableList<Empleado> listEmpleados;

        //Ultimas compras, Y mas Vendidos
        connection.establecerConexion();
        listEmpleados = Empleado.llenarEmpleados(connection.getConection());
        connection.cerrarConexion();

        //Primer box and txtFiltro
        hBoxDatos.getChildren().add(txtDatos);
        hBoxDatos.setPadding(new Insets(0,0,0,5));
        hBoxDatos.getStyleClass().add("panelWhite");
        txtFiltro.setPromptText("Nombre o Correo");
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
        buscarTbl(txtFiltro, table);
        establecerCampos(table);

        //Acciones de os botones
        btnEliminar.setOnAction(this::noFunciona);
        btnEditar.setOnAction(this::noFunciona);
        btnAnadir.setOnAction(this::noFunciona);

        VBox.setMargin(hBoxDatos, bottomInsets);
        VBox.setMargin(txtDatos, bottomInsets);
        VBox.setMargin(gridPane, bottomInsets);
        VBox.setMargin(hBox, bottomInsets);

        setPadding(new Insets(10));
        getChildren().addAll(hBoxDatos, gridPane, hBox, table);
        getStylesheets().add(getClass().getResource("/estilos/empleados.css").toExternalForm());
    }

    private void deshabilitarEmpleado(ActionEvent e) {
    }

    private void noFunciona(ActionEvent e) {
        TrayNotification trayNotification = new TrayNotification();
        trayNotification.setTitle("Verifique");
        trayNotification.setMessage("Esta opcion no esta disponible");
        trayNotification.setNotificationType(NotificationType.NOTICE);
        trayNotification.showAndDismiss(Duration.millis(3000));
    }

    private JFXTreeTableView createTable(ObservableList<Empleado> list) {
        final TreeItem<Empleado> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        var tableView = new JFXTreeTableView<>(root);

        var clmNombre = new JFXTreeTableColumn<Empleado, String>("Nombre");
        var clmApellido = new JFXTreeTableColumn<Empleado, String>("Apellido");
        var clmTelefono = new JFXTreeTableColumn<Empleado, String>("Telefono");
        var clmCorreo = new JFXTreeTableColumn<Empleado, String>("Correo");
        var clmMunicipio = new JFXTreeTableColumn<Empleado, String>("Municipio");

        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(200);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmApellido.setResizable(false);
        clmApellido.setPrefWidth(200);
        clmApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmApellido.validateValue(param))
                return param.getValue().getValue().apellidoProperty();
            else
                return clmApellido.getComputedValue(param);
        });

        clmTelefono.setResizable(false);
        clmTelefono.setPrefWidth(190);
        clmTelefono.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmTelefono.validateValue(param))
                return param.getValue().getValue().telefonoProperty();
            else
                return clmTelefono.getComputedValue(param);
        });

        clmCorreo.setResizable(false);
        clmCorreo.setPrefWidth(250);
        clmCorreo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmCorreo.validateValue(param))
                return param.getValue().getValue().correoProperty();
            else
                return clmCorreo.getComputedValue(param);
        });

        clmMunicipio.setResizable(false);
        clmMunicipio.setPrefWidth(250);
        clmMunicipio.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmMunicipio.validateValue(param))
                return param.getValue().getValue().municipioProperty();
            else
                return clmMunicipio.getComputedValue(param);
        });

        tableView.setShowRoot(false);
        tableView.setEditable(false);
        tableView.getColumns().setAll(clmNombre, clmApellido, clmMunicipio, clmTelefono, clmCorreo);

        return tableView;
    }

    private void establecerCampos(JFXTreeTableView<Empleado> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null) {
                txtNombre.setText(newValue.getValue().getNombre());
                txtApellido.setText(newValue.getValue().getApellido());
                txtEdad.setText(newValue.getValue().getEdad()+"");
                txtTelefono.setText(newValue.getValue().getTelefono());
                txtCorreo.setText(newValue.getValue().getCorreo());
                txtEstado.setText(newValue.getValue().getEstado());
                txtNombreCalle.setText(newValue.getValue().getNombreCalle());
                txtNoCasa.setText(newValue.getValue().getnCalle()+"");
                txtCodPostal.setText(newValue.getValue().getCodigoPostal());
                txtAsentamiento.setText(newValue.getValue().getAsentamiento());
                txtCiudad.setText(newValue.getValue().getCiudad());
                txtMunicipio.setText(newValue.getValue().getMunicipio());
            }
        }));
    }

    private void buscarTbl(TextField field, JFXTreeTableView<Empleado> tbl) {
        field.textProperty().addListener((value, oldValue, newValue) -> {
            tbl.setPredicate(c ->
                c.getValue().getNombre().toLowerCase().contains(newValue.toLowerCase()) ||
                        c.getValue().getCorreo().toLowerCase().contains(newValue.toLowerCase())
            );
        });
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
