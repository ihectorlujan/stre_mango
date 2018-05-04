package com.controladores.empleados;

import com.jfoenix.controls.*;
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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class OpcionEmpleado extends VBox {

    Conexion connection = new Conexion();

    TextField txtID = new TextField();
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
    Label lblEstado = new Label("Estado:");
    Label lblAsentamiento = new Label("Asentamiento:");
    Label lblCiudad = new Label("Ciudad:");
    Label lblMunicipio = new Label("Municipio:");
    JFXRadioButton rbHombre = new JFXRadioButton("Hombre");
    JFXRadioButton rbMujer = new JFXRadioButton("Mujer");
    JFXPopup popupCodigoPostal;

    public OpcionEmpleado() {
        var bottomInsets = new Insets(0,0,10,0);
        var txtDatos = new Label("Datos Personales");
        var gridPane = new GridPane();
        var lblID = new Label("ID:");
        var lblNombre = new Label("Nombre:");
        var lblApellido = new Label("Apellido:");
        var lblEdad = new Label("Edad:");
        var lblSexo = new Label("Sexo:");
        var lblTelefono = new Label("Telefono:");
        var lblCorreo = new Label("Correo:");
        var lblNombreCalle = new Label("Calle:");
        var lblNoCasa = new Label("No de casa:");
        var lblCodigoPostal = new Label("Codigo Postal:");
        var lblBuscar = new Label("Buscar:");
        var toggleGroup = new ToggleGroup();
        var hBox = new HBox();
        var hBoxSearch = new HBox();
        var hBoxDatos = new HBox();
        var hBoxSexo = new HBox();
        var hBoxCodigoPostal = new HBox();
        var btnAnadir = new JFXButton();
        var btnEditar = new JFXButton();
        var btnEliminar = new JFXButton();
        var btnMas = new JFXButton("Mas");
        var icoEdit = GlyphsDude.createIcon(FontAwesomeIcon.EDIT,"14px");
        var icoAdd = GlyphsDude.createIcon(FontAwesomeIcon.USER_PLUS,"14px");
        var icoDelete = GlyphsDude.createIcon(FontAwesomeIcon.TRASH_ALT,"14px");
        var txtFiltro = new TextField();
        ObservableList<Empleado> listEmpleados;

        //Radio Buttons
        rbHombre.setToggleGroup(toggleGroup);
        rbMujer.setToggleGroup(toggleGroup);
        rbHombre.setDisable(true);
        rbMujer.setDisable(true);
        hBoxSexo.getChildren().addAll(rbHombre, rbMujer);
        hBoxSexo.setPadding(new Insets(8,0,0,0));
        HBox.setMargin(rbHombre, new Insets(0,10,0,0));

        //Codigo postal
        hBoxCodigoPostal.getChildren().addAll(txtCodPostal, btnMas);
        HBox.setMargin(txtCodPostal, new Insets(0,10,0,0));

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

        gridPane.add(lblID,0,0);
        gridPane.add(lblNombre,0,1);
        gridPane.add(lblApellido,0,2);
        gridPane.add(lblEdad,0,3);
        gridPane.add(lblSexo,0,4);

        gridPane.add(txtID,1,0);
        gridPane.add(txtNombre,1,1);
        gridPane.add(txtApellido,1,2);
        gridPane.add(txtEdad,1,3);
        gridPane.add(hBoxSexo,1,4);

        gridPane.add(lblTelefono,2,0);
        gridPane.add(lblCorreo,2,1);
        gridPane.add(lblNombreCalle,2,2);
        gridPane.add(lblNoCasa,2,3);
        gridPane.add(lblCodigoPostal,2,4);

        gridPane.add(txtTelefono,3,0);
        gridPane.add(txtCorreo, 3,1);
        gridPane.add(txtNombreCalle, 3,2);
        gridPane.add(txtNoCasa, 3,3);
        gridPane.add(hBoxCodigoPostal, 3,4);

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

        //Acciones de los botones
        btnEliminar.setOnAction(x -> deleteUser(listEmpleados, table));
        btnEditar.setOnAction(this::noFunciona);
        btnAnadir.setOnAction(this::noFunciona);
        btnMas.setOnAction(x -> {
            initPopUpCodigoPostal();
            popupCodigoPostal.show(txtCodPostal);
        });

        VBox.setMargin(hBoxDatos, bottomInsets);
        VBox.setMargin(txtDatos, bottomInsets);
        VBox.setMargin(gridPane, bottomInsets);
        VBox.setMargin(hBox, bottomInsets);

        setPadding(new Insets(10));
        getChildren().addAll(hBoxDatos, gridPane, hBox, table);
        getStylesheets().add(getClass().getResource("/estilos/empleados.css").toExternalForm());
    }

    private void initPopUpCodigoPostal() {
        var pane = new GridPane();

        pane.add(lblEstado,0,0);
        pane.add(lblCiudad,0,1);
        pane.add(lblMunicipio,0,2);
        pane.add(lblAsentamiento,0,3);

        pane.add(txtEstado,1,0);
        pane.add(txtCiudad,1,1);
        pane.add(txtMunicipio,1,2);
        pane.add(txtAsentamiento,1,3);

        pane.getChildren().forEach(x -> {
            if (x instanceof TextField)
                ((TextField) x).setEditable(false);
        });
        pane.getStylesheets().setAll("/estilos/empleados.css");
        pane.setHgap(20);
        pane.setVgap(4);
        pane.setPadding(new Insets(10));

        popupCodigoPostal = new JFXPopup(pane);
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

        var clmID = new JFXTreeTableColumn<Empleado, Integer>("ID");
        var clmNombre = new JFXTreeTableColumn<Empleado, String>("Nombre");
        var clmApellido = new JFXTreeTableColumn<Empleado, String>("Apellido");
        var clmTelefono = new JFXTreeTableColumn<Empleado, String>("Telefono");
        var clmCorreo = new JFXTreeTableColumn<Empleado, String>("Correo");
        var clmMunicipio = new JFXTreeTableColumn<Empleado, String>("Municipio");

        clmID.setResizable(false);
        clmID.setPrefWidth(50);
        clmID.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, Integer> param) -> {
            if (clmID.validateValue(param))
                return param.getValue().getValue().idProperty().asObject();
            else
                return clmID.getComputedValue(param);
        });

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
        tableView.getColumns().setAll(clmID, clmNombre, clmApellido, clmMunicipio, clmTelefono, clmCorreo);

        return tableView;
    }

    private void deleteUser(ObservableList<Empleado> list, JFXTreeTableView tableView) {
        try {
            var temporalityEmployeed = new Empleado(Integer.parseInt(txtID.getText()));
            connection.establecerConexion();
            int resultado = temporalityEmployeed.eliminarEmpleado(connection.getConection());
            connection.cerrarConexion();
            if (resultado == 1) {
                list.remove(tableView.getSelectionModel().getSelectedIndex());
                TrayNotification trayNotification = new TrayNotification();
                trayNotification.setTitle("Completado");
                trayNotification.setMessage("Se elimino satisfactoriamente");
                trayNotification.setNotificationType(NotificationType.SUCCESS);
                trayNotification.showAndDismiss(Duration.millis(3000));
            } else {
                TrayNotification trayNotification = new TrayNotification();
                trayNotification.setTitle("Algo salio mal...");
                trayNotification.setMessage("Intentelo mas tarde");
                trayNotification.setNotificationType(NotificationType.ERROR);
                trayNotification.showAndDismiss(Duration.millis(3000));

            }
        }catch (NumberFormatException e) {
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("No selecciono registro");
            trayNotification.setMessage("Seleccione el empleado a eliminar");
            trayNotification.setNotificationType(NotificationType.ERROR);
            trayNotification.showAndDismiss(Duration.millis(3000));
        }
    }

    private void establecerCampos(JFXTreeTableView<Empleado> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null) {
                txtID.setText(newValue.getValue().getId()+"");
                txtNombre.setText(newValue.getValue().getNombre());
                txtApellido.setText(newValue.getValue().getApellido());
                txtEdad.setText(newValue.getValue().getEdad()+"");
                if (newValue.getValue().getSexo().equals("hombre"))
                    rbHombre.setSelected(true);
                else
                    rbMujer.setSelected(true);
                txtTelefono.setText(newValue.getValue().getTelefono());
                txtCorreo.setText(newValue.getValue().getCorreo());
                txtEstado.setText(newValue.getValue().getEstado());
                txtNombreCalle.setText(newValue.getValue().getNombreCalle());
                txtNoCasa.setText(newValue.getValue().getnCasa()+"");
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
