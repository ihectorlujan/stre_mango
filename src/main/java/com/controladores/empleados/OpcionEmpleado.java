package com.controladores.empleados;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Conexion;
import com.modelo.empleado.Empleado;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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

    TextField txtNombre = new TextField();
    TextField txtApellidoPaterno = new TextField();
    TextField txtApellidoMaterno = new TextField();
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
    Label lblIdN = new Label("10");
    Label lblEstado = new Label("Estado:");
    Label lblAsentamiento = new Label("Asentamiento:");
    Label lblCiudad = new Label("Ciudad:");
    Label lblMunicipio = new Label("Municipio:");
    JFXRadioButton rbHombre = new JFXRadioButton("Hombre");
    JFXRadioButton rbMujer = new JFXRadioButton("Mujer");
    JFXComboBox<Integer> cmbEdad = new JFXComboBox<>();
    JFXPopup popupCodigoPostal;

    public OpcionEmpleado() {
        var bottomInsets = new Insets(0,0,10,0);
        var txtDatos = new Label("Datos Personales");
        var gridPane = new GridPane();
        var gridPaneDomicilio = new GridPane();
        var lblID = new Label("ID:");
        var lblNombre = new Label("Nombre:");
        var lblApellidoPaterno = new Label("Apellido Paterno:");
        var lblApellidoMaterno = new Label("Apellido Materno:");
        var lblEdad = new Label("Edad:");
        var lblSexo = new Label("Sexo:");
        var lblTelefono = new Label("Telefono:");
        var lblCorreo = new Label("Correo:");
        var lblNombreCalle = new Label("Calle:");
        var lblNoCasa = new Label("No de casa:");
        var lblCodigoPostal = new Label("C.P:");
        var lblBuscar = new Label("Buscar:");
        var toggleGroup = new ToggleGroup();
        var hBox = new HBox();
        var hBoxSearch = new HBox();
        var hBoxDatos = new HBox();
        var hBoxSexo = new HBox();
        var hBoxCodigoPostal = new HBox();
        var hBoxId = new HBox(5);
        var hBoxEdad = new HBox();
        var btnAnadir = new JFXButton();
        var btnEditar = new JFXButton();
        var btnEliminar = new JFXButton();
        var btnMas = new JFXButton();
        var icoEdit = GlyphsDude.createIcon(FontAwesomeIcon.EDIT,"14px");
        var icoAdd = GlyphsDude.createIcon(FontAwesomeIcon.USER_PLUS,"14px");
        var icoDelete = GlyphsDude.createIcon(FontAwesomeIcon.TRASH_ALT,"14px");
        var icoInfo = GlyphsDude.createIcon(FontAwesomeIcon.INFO,"14px");
        var txtFiltro = new TextField();
        ObservableList<Empleado> listEmpleados;

        //TextFields properties
        txtNombreCalle.setPrefWidth(250);
        txtNoCasa.setPrefWidth(60);
        txtCodPostal.setPrefWidth(60);
        txtEdad.setPadding(new Insets(8,0,0,0));

        //Radio Buttons
        rbHombre.setToggleGroup(toggleGroup);
        rbMujer.setToggleGroup(toggleGroup);
        rbHombre.setDisable(true);
        rbMujer.setDisable(true);

        //Hbox Sexo
        hBoxSexo.getChildren().addAll(lblSexo,rbHombre, rbMujer);
        hBoxSexo.setPadding(new Insets(8,0,0,0));
        HBox.setMargin(rbHombre, new Insets(0,10,0,10));

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
        setStyleIcons(icoAdd, icoDelete, icoEdit, icoInfo);
        btnAnadir.setGraphic(icoAdd);
        btnEditar.setGraphic(icoEdit);
        btnEliminar.setGraphic(icoDelete);
        btnMas.setGraphic(icoInfo);

        //HBox id
        hBoxId.getChildren().addAll(lblID, lblIdN);
        HBox.setMargin(lblID, new Insets(0,10,0,0));

        //HBox edad
        hBoxEdad.getChildren().addAll(lblEdad, cmbEdad);
        HBox.setMargin(lblEdad, new Insets(0,10,0,0));

        //GridPane Datos del empleado
            //Columns
        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();
        var column3 = new ColumnConstraints();
        var column4 = new ColumnConstraints();
        var column5 = new ColumnConstraints();
        var column6 = new ColumnConstraints();

        gridPane.add(hBoxId,0,0);
        gridPane.add(lblNombre,0,1);
        gridPane.add(lblApellidoPaterno,0,2);
        gridPane.add(lblApellidoMaterno,0,3);

        gridPane.add(txtNombre,1,1);
        gridPane.add(txtApellidoPaterno,1,2);
        gridPane.add(txtApellidoMaterno,1,3);

        gridPane.add(hBoxEdad,2,1);
        gridPane.add(lblTelefono,2,2);
        gridPane.add(lblCorreo,2,3);

        gridPane.add(hBoxSexo,3,1);

        gridPane.add(txtTelefono,3,2);
        gridPane.add(txtCorreo, 3,3);

        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));

        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(4);
        gridPane.getStyleClass().add("panelWhite");
        blockGridPaneFields(gridPane);

        //Datos del empleado, domicicilio
        gridPaneDomicilio.add(lblNombreCalle, 0,0);
        gridPaneDomicilio.add(lblNoCasa,3,0);
        gridPaneDomicilio.add(lblCodigoPostal,5,0);

        gridPaneDomicilio.add(txtNombreCalle,1,0);
        gridPaneDomicilio.add(txtNoCasa,4,0);
        gridPaneDomicilio.add(hBoxCodigoPostal,6,0);

        gridPaneDomicilio.getStyleClass().add("panelWhite");
        gridPaneDomicilio.getColumnConstraints().addAll(column1,column2,column3,column4,column5,column6);
        gridPaneDomicilio.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPaneDomicilio.setHgap(20);
        blockGridPaneFields(gridPaneDomicilio);
        gridPaneDomicilio.setPadding(new Insets(10));

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
        btnAnadir.setOnAction(this::addEmpleado);
        btnMas.setOnAction(x -> {
            initPopUpCodigoPostal();
            popupCodigoPostal.show(txtCodPostal);
        });

        VBox.setMargin(hBoxDatos, bottomInsets);
        VBox.setMargin(txtDatos, bottomInsets);
        VBox.setMargin(gridPane, bottomInsets);
        VBox.setMargin(gridPaneDomicilio, bottomInsets);
        VBox.setMargin(hBox, bottomInsets);

        setPadding(new Insets(10));
        getChildren().addAll(hBoxDatos, gridPane, gridPaneDomicilio, hBox, table);
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
        tableView.getColumns().setAll(clmID, clmNombre, clmMunicipio, clmTelefono, clmCorreo);

        return tableView;
    }

    private void deleteUser(ObservableList<Empleado> list, JFXTreeTableView tableView) {
        try {
            var temporalityEmployeed = new Empleado(Integer.parseInt(lblIdN.getText()));
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
                lblIdN.setText(newValue.getValue().getId()+"");
                txtNombre.setText(newValue.getValue().getNombre());
                txtApellidoPaterno.setText(newValue.getValue().getApellidoPaterno());
                txtApellidoMaterno.setText(newValue.getValue().getApellidoMaterno());
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

    private void addEmpleado(ActionEvent e) {
        new AgregarEmpleado();
    }
}
