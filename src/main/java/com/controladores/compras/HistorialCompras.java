package com.controladores.compras;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Compra;
import com.modelo.Conexion;
import com.modelo.DetalleCompra;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class HistorialCompras extends Stage {

    //Paneles
    private GridPane panelDatos;
    private GridPane panelEtiquetas;
    private GridPane panelOpciones;

    //Datos
    private TextField txtID;
    private JFXDatePicker datePicker;
    private JFXTimePicker timePicker;
    private TextField txtEmpleado;
    private TextField txtProveedor;
    private JFXButton btnDetalles;
    private JFXPopup popup;

    //Opciones
    private TextField txtBuscar;
    private JFXButton btnBuscar;
    private JFXDatePicker pickerDateBuscar;
    private JFXDatePicker pickerDateBuscar2;
    private JFXButton btnReset;

    //Conexion
    private Conexion conexion;

    //Colecciones
    private ObservableList<Compra> listaCompra;
    private ObservableList<DetalleCompra> detalleCompras;

    public HistorialCompras() {
        conexion = new Conexion();
        listaCompra = FXCollections.observableArrayList();
        detalleCompras = FXCollections.observableArrayList();

        conexion.establecerConexion();

        Compra.llenarCompras(conexion.getConection(), listaCompra);
        DetalleCompra.llenarDatos(conexion.getConection(), detalleCompras);

        conexion.cerrarConexion();

        //Instancias
        VBox contenedorPrincipal = new VBox();
        panelEtiquetas = new GridPane();
        panelDatos = new GridPane();
        panelOpciones = new GridPane();
        JFXTreeTableView tblCompras = new JFXTreeTableView();

        //Paneles
        panelEtiquetas();
        panelDatos();
        panelOpciones();

        tblCompras.setPrefSize(731,186);

        //ContenedorPricipal
        VBox.setMargin(panelEtiquetas, new Insets(10,0,10,0));
        VBox.setMargin(panelDatos, new Insets(5,0,8,0));
        VBox.setMargin(panelOpciones, new Insets(10,0,10,0));
        VBox.setVgrow(tblCompras, Priority.ALWAYS);

        contenedorPrincipal.setPadding(new Insets(5,15,15,15));
        contenedorPrincipal.getChildren().addAll(panelEtiquetas, panelDatos, panelOpciones, tblCompras);

        //Operaciones de la tabla
        llenarTabla(tblCompras, listaCompra);
        buscarTbl(txtBuscar, tblCompras);

        //Eventos
        btnBuscar.setOnAction(e -> buscarPorFecha(pickerDateBuscar, pickerDateBuscar2, tblCompras));
        btnReset.setOnAction(e -> {
            pickerDateBuscar.setValue(null);
            pickerDateBuscar2.setValue(null);
            txtBuscar.setText("");
            tblCompras.setPredicate(p -> true);
        });

        setResizable(false);
        Scene  scene = new Scene(contenedorPrincipal, 835,583);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/historial_compras.css").toExternalForm());
        show();
    }


    private void panelEtiquetas() {
        Label lblHcompras = new Label("Historial de compras");
        GridPane panelIco = new GridPane();
        DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Text icoFecha = GlyphsDude.createIcon(FontAwesomeIcon.CALENDAR,"14px");
        Label lblFecha = new Label("  " + date.format(new Date()));
        ColumnConstraints column1$Etiquetas = new ColumnConstraints(10,543,543);
        ColumnConstraints column2$Etiquetas = new ColumnConstraints(10,188,296);
        ColumnConstraints column1$Ico = new ColumnConstraints(10,100, Double.MAX_VALUE);
        RowConstraints row = new RowConstraints(10,30.0,Double.MAX_VALUE);

        //Estilos
        icoFecha.getStyleClass().add("ico");
        panelIco.getStyleClass().add("purple");
        lblFecha.getStyleClass().add("labelFecha");
        GridPane.setMargin(lblHcompras, new Insets(0,0,0,5));
        GridPane.setHalignment(lblFecha, HPos.RIGHT);
        GridPane.setMargin(lblFecha, new Insets(0,10,0,0));
        column1$Ico.setHgrow(Priority.ALWAYS);
        column2$Etiquetas.setHgrow(Priority.SOMETIMES);
        row.setVgrow(Priority.ALWAYS);
        panelEtiquetas.getStyleClass().add("white");

        lblFecha.setGraphic(icoFecha);
        panelIco.add(lblFecha,0,0);
        panelIco.getColumnConstraints().add(column1$Ico);
        panelIco.getRowConstraints().add(row);

        panelEtiquetas.add(lblHcompras, 0,0);
        panelEtiquetas.add(panelIco,1,0);
        panelEtiquetas.getColumnConstraints().addAll(column1$Etiquetas, column2$Etiquetas);
    }

    private void panelDatos() {
        Label lblId = new Label("ID:");
        Label lblFecha = new Label("Fecha:");
        Label lblHora = new Label("Hora:");
        Label lblEmpleado = new Label("ID Empleado:");
        Label lblProveedor = new Label("Proveedor:");
        Label lblDetalles = new Label("Detalles de la compra");
        txtID = new TextField();
        datePicker = new JFXDatePicker();
        timePicker = new JFXTimePicker();
        txtEmpleado = new TextField();
        txtProveedor = new TextField();
        btnDetalles = new JFXButton("Ver detalles");
        ColumnConstraints column1$Datos = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column2$Datos = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column3$Datos = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column4$Datos = new ColumnConstraints(10,100, Double.MAX_VALUE);

        panelDatos.add(lblId, 0,0);
        panelDatos.add(lblFecha,0,1);
        panelDatos.add(lblHora, 0,2);
        panelDatos.add(txtID, 1,0);
        panelDatos.add(datePicker,1,1);
        panelDatos.add(timePicker,1,2);
        panelDatos.add(lblEmpleado, 2,0);
        panelDatos.add(lblProveedor,2,1);
        panelDatos.add(lblDetalles,2,2);
        panelDatos.add(txtEmpleado, 3,0);
        panelDatos.add(txtProveedor, 3,1);
        panelDatos.add(btnDetalles, 3,2);

        //Estilos
        btnDetalles.getStyleClass().add("btnRaisedBlue");
        panelDatos.getStyleClass().add("white");
        panelDatos.setHgap(30);
        panelDatos.setVgap(5);
        panelDatos.setPadding(new Insets(10,10,10,10));

        panelDatos.getColumnConstraints().addAll(column1$Datos, column2$Datos, column3$Datos,column4$Datos);
        panelDatos.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));

    }

    private void panelOpciones() {
        //panelOpciones
        GridPane paneOpciones1 = new GridPane();
        GridPane paneOpciones2 = new GridPane();
        ColumnConstraints column1$Opcion = new ColumnConstraints(10,538,551);
        ColumnConstraints column2$Opcion = new ColumnConstraints(10,139,318);
        ColumnConstraints column1$Opciones1 = new ColumnConstraints(10,236,276);
        ColumnConstraints column2$Opciones1 = new ColumnConstraints(10,143,189);
        ColumnConstraints column3$Opciones1 = new ColumnConstraints(10,143,189);
        ColumnConstraints column1$Opciones2 = new ColumnConstraints(10,100,Double.MAX_VALUE);
        ColumnConstraints column2$Opciones2 = new ColumnConstraints(10,100,Double.MAX_VALUE);

        //paneOpciones1
        HBox box1 = new HBox();
        Label lblBuscar = new Label("Buscar por:");
        txtBuscar = new TextField();
        box1.getChildren().addAll(lblBuscar, txtBuscar);

        HBox box2 = new HBox();
        Label lblDe = new Label("De:");
        pickerDateBuscar = new JFXDatePicker();
        box2.getChildren().addAll(lblDe, pickerDateBuscar);

        HBox box3 = new HBox();
        Label lblA = new Label("A:");
        pickerDateBuscar2 = new JFXDatePicker();
        box3.getChildren().addAll(lblA, pickerDateBuscar2);

        paneOpciones1.add(box1, 0,0);
        paneOpciones1.add(box2,1,0);
        paneOpciones1.add(box3,2,0);

        //paneOpciones2
        btnBuscar = new JFXButton("Buscar");
        btnReset = new JFXButton("Reset");
        paneOpciones2.add(btnBuscar, 0,0);
        paneOpciones2.add(btnReset,1,0);

        //Estilos
        btnBuscar.getStyleClass().add("btnRaisedBlue");
        btnReset.getStyleClass().add("btnRaisedPurple");
        pickerDateBuscar.setEditable(false);
        pickerDateBuscar2.setEditable(false);
        lblBuscar.setPrefWidth(100);
        lblDe.setPrefWidth(100);
        lblA.setPrefWidth(100);
        txtBuscar.setPromptText("Proveedor o ID");

        GridPane.setHalignment(btnBuscar, HPos.RIGHT);
        GridPane.setHalignment(btnReset, HPos.RIGHT);
        box1.setAlignment(Pos.CENTER);
        box2.setAlignment(Pos.CENTER);
        box3.setAlignment(Pos.CENTER);
        paneOpciones1.setHgap(5);
        paneOpciones1.setVgap(5);
        paneOpciones1.setPadding(new Insets(5));
        paneOpciones2.setPadding(new Insets(7,5,5,5));


        HBox.setMargin(txtBuscar, new Insets(5,3,0,0));

        panelOpciones.setPadding(new Insets(10,10,10,5));
        panelOpciones.setHgap(10);
        panelOpciones.getStyleClass().add("white");

        panelOpciones.getColumnConstraints().addAll(column1$Opcion, column2$Opcion);
        panelOpciones.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));
        paneOpciones1.getColumnConstraints().addAll(column1$Opciones1, column2$Opciones1, column3$Opciones1);
        paneOpciones1.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));
        paneOpciones2.getColumnConstraints().addAll(column1$Opciones2, column2$Opciones2);
        paneOpciones2.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));
        panelOpciones.add(paneOpciones1,0,0);
        panelOpciones.add(paneOpciones2,1,0);
    }

    private void llenarTabla(JFXTreeTableView<Compra> tbl, ObservableList<Compra> list) {
        final TreeItem<Compra> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        tbl.setRoot(root);

        //Columns
        JFXTreeTableColumn<Compra, Integer> clmId = new JFXTreeTableColumn("ID");
        JFXTreeTableColumn<Compra, String> clmFecha = new JFXTreeTableColumn("Fecha");
        JFXTreeTableColumn<Compra, String> clmHora = new JFXTreeTableColumn("Hora");
        JFXTreeTableColumn<Compra, Integer> clmEmpleado = new JFXTreeTableColumn("Empleado");
        JFXTreeTableColumn<Compra, String> clmProveedor = new JFXTreeTableColumn("Proveedor");

        clmId.setResizable(false);
        clmId.setPrefWidth(150);
        clmId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Compra, Integer> param) -> {
            if (clmId.validateValue(param))
                return param.getValue().getValue().idProperty().asObject();
            else
                return clmId.getComputedValue(param);
        });

        clmFecha.setResizable(false);
        clmFecha.setPrefWidth(200);
        clmFecha.setCellValueFactory((TreeTableColumn.CellDataFeatures<Compra, String> param) -> {
            if (clmFecha.validateValue(param))
                return param.getValue().getValue().fechaProperty();
            else
                return clmFecha.getComputedValue(param);
        });

        clmHora.setResizable(false);
        clmHora.setPrefWidth(150);
        clmHora.setCellValueFactory((TreeTableColumn.CellDataFeatures<Compra, String> param) -> {
            if (clmHora.validateValue(param))
                return param.getValue().getValue().timeProperty();
            else
                return clmHora.getComputedValue(param);
        });

        clmEmpleado.setResizable(false);
        clmEmpleado.setPrefWidth(150);
        clmEmpleado.setCellValueFactory((TreeTableColumn.CellDataFeatures<Compra, Integer> param) -> {
            if (clmEmpleado.validateValue(param))
                return param.getValue().getValue().idEmpleadoProperty().asObject();
            else
                return clmEmpleado.getComputedValue(param);
        });

        clmProveedor.setResizable(false);
        clmProveedor.setPrefWidth(150);
        clmProveedor.setCellValueFactory((TreeTableColumn.CellDataFeatures<Compra, String> param) -> {
            if (clmProveedor.validateValue(param))
                return param.getValue().getValue().proveedorProperty();
            else
                return clmProveedor.getComputedValue(param);
        });

        // Operaciones con la tabla
        setDatos(tbl);
        bloquearCampos();

        tbl.setEditable(false);
        tbl.setShowRoot(false);
        tbl.getColumns().setAll(clmId, clmFecha, clmHora, clmProveedor, clmEmpleado);

    }

    private void setDatos(JFXTreeTableView<Compra> tbl) {
        tbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtID.setText(newValue.getValue().getId() + "");
                txtEmpleado.setText(newValue.getValue().getIdEmpleado() + "");
                txtProveedor.setText(newValue.getValue().getProveedor() + "");
                datePicker.setValue(LocalDate.parse(newValue.getValue().getFecha()));
                timePicker.setValue(LocalTime.parse(newValue.getValue().getTime()));

                datePicker.setDisable(false);
                timePicker.setDisable(false);
                btnDetalles.setDisable(false);

                initPopup(newValue.getValue());
                btnDetalles.setOnAction(e -> popup.show(btnDetalles));
            }

        });
    }

    private void bloquearCampos() {
        txtID.setEditable(false);
        txtProveedor.setEditable(false);
        txtEmpleado.setEditable(false);
        btnDetalles.setDisable(true);
        timePicker.setEditable(false);
        datePicker.setEditable(false);
        pickerDateBuscar.setEditable(false);
        pickerDateBuscar2.setEditable(false);

        timePicker.setDisable(true);
        datePicker.setDisable(true);
    }

    private void initPopup(Compra compra) {
        VBox vBox = new VBox();
        GridPane pane = new GridPane();

        Label lblId = new Label("ID Empleado:");
        Label lblNombre = new Label("Nombre:");
        Label lblApellido = new Label("Apellido:");
        TextField txtId = new TextField(compra.getIdEmpleado() + "");
        TextField txtNombre = new TextField(compra.getNombreEmpleado());
        TextField txtApellido = new TextField(compra.getApellidoEmpleado());

        txtId.setEditable(false);
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);

        pane.setHgap(20);
        pane.setVgap(10);

        pane.add(lblId,0,0);
        pane.add(lblNombre,0,1);
        pane.add(lblApellido,0,2);
        pane.add(txtId,1,0);
        pane.add(txtNombre,1,1);
        pane.add(txtApellido,1,2);

        JFXTreeTableView table = createTableDetalle(detalleCompras.filtered(dC -> dC.getIdTransaccion() == compra.getId()));

        VBox.setMargin(table, new Insets(10,0,0,0));
        VBox.setVgrow(table, Priority.ALWAYS);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(pane, table);
        vBox.setPrefSize(400,400);

        popup = new JFXPopup(vBox);
    }

    private void buscarTbl(TextField text, JFXTreeTableView<Compra> tbl) {
        text.textProperty().addListener((value, oldValue, newValue) -> {
            tbl.setPredicate(c ->
                    c.getValue().getProveedor().toLowerCase().contains(newValue.toLowerCase()) ||
                            String.valueOf(c.getValue().getId()).contains(newValue)
            );
        });
    }

    private void buscarPorFecha(JFXDatePicker date1, JFXDatePicker date2, JFXTreeTableView<Compra> table) {
        if (date1.getValue() == null || date2.getValue() == null)
            errorNotification("Verifique las fechas", "Ingrese ambas fechas");
        else {
            Date picker1 = Date.from(date1.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date picker2 = Date.from(date2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (picker1.after(picker2))
                errorNotification("Verifique las fechas", "La primer fecha debe ser inferior a a segunda");
            else {
                table.setPredicate(c -> {
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(c.getValue().getFecha());
                        return (date.after(picker1) || date.equals(picker1)) &&
                                (date.before(picker2) || date.equals(picker2));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    return false;
                });
            }
        }
    }

    private void errorNotification(String msgPrincipal, String msgSecundario) {
        TrayNotification trayNotification = new TrayNotification();
        trayNotification.setTitle(msgPrincipal);
        trayNotification.setMessage(msgSecundario);
        trayNotification.setNotificationType(NotificationType.ERROR);
        trayNotification.showAndDismiss(Duration.millis(3000));
    }

    private JFXTreeTableView createTableDetalle(ObservableList<DetalleCompra> list) {
        final TreeItem<DetalleCompra> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        JFXTreeTableView tableView = new JFXTreeTableView(root);

        JFXTreeTableColumn<DetalleCompra, String> clmBarCode = new JFXTreeTableColumn("Codigo");
        JFXTreeTableColumn<DetalleCompra, String> clmNombre = new JFXTreeTableColumn("Nombre");
        JFXTreeTableColumn<DetalleCompra, Double> clmPrecio = new JFXTreeTableColumn("Precio");

        clmBarCode.setResizable(false);
        clmBarCode.setPrefWidth(100);
        clmBarCode.setCellValueFactory((TreeTableColumn.CellDataFeatures<DetalleCompra, String> param) -> {
            if (clmBarCode.validateValue(param))
                return param.getValue().getValue().codBarraProperty();
            else
                return clmBarCode.getComputedValue(param);
        });

        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(200);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<DetalleCompra, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProductoProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmPrecio.setResizable(false);
        clmPrecio.setPrefWidth(70);
        clmPrecio.setCellValueFactory((TreeTableColumn.CellDataFeatures<DetalleCompra, Double> param) -> {
            if (clmPrecio.validateValue(param))
                return param.getValue().getValue().precioProductoProperty().asObject();
            else
                return clmPrecio.getComputedValue(param);
        });

        tableView.setEditable(false);
        tableView.setShowRoot(false);

        tableView.getColumns().setAll(clmBarCode, clmNombre, clmPrecio);

        return tableView;
    }
}
