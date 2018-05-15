package com.controladores.proveedores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Conexion;
import com.modelo.Proveedor;
import com.modelo.empleado.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class OpcionProveedor extends VBox {
    Conexion conexion = new Conexion();
    ObservableList<Proveedor> listProvee;

   private Insets   insetsBase;
   private Label    titulo;
   private HBox     hBoxTitulo;
   private HBox     hBoxDomicilio;
   private HBox     hBoxBusqueda;
   private HBox     hBoxLista;
   private HBox     hBoxTabla;
   private HBox     hBoxSpace;
   private HBox     hBoxSpace2;
   private HBox     hBoxSpace3;
   private HBox     hBoxSpace4;
   private GridPane gridPane;
   private GridPane gridPane2;
   private Label    lrazonSocial;
   private Label    lTelefono;
   private Label    lEmail;
   private Label    lDomicilio;
   private Label    lCalle;
   private Label    lNCasa;
   private Label    lCP;
   private Label    lEstado;
   private Label    lBuscar;
   private Label    lId;
   private Label    lIdContainer;
   private Label    lLista;
   private TextField tRazonSocial;
   private TextField tTelefono;
   private TextField tEmail;
   private TextField tCalle;
   private TextField tNcasa;
   private TextField tCP;
   private TextField tEstado;
   private TextField  tBuscar;
   private ComboBox<Integer>  cboxEdad ;
   private RadioButton rbSexoH ;
   private JFXButton   btnMas ;

    private Proveedor proveedor=null;

    public OpcionProveedor(){
        conexion.establecerConexion();
        listProvee = Proveedor.llenarProveedores(conexion.getConection());
        conexion.cerrarConexion();

        insetsBase   = new Insets(10);
        titulo       = new Label("Datos del proveedor.");
        hBoxTitulo   = new HBox();
        hBoxDomicilio= new HBox();
        hBoxBusqueda = new HBox();
        hBoxLista    = new HBox();
        hBoxTabla    = new HBox();
        hBoxSpace    = new HBox();
        hBoxSpace2   = new HBox();
        hBoxSpace3   = new HBox();
        hBoxSpace4   = new HBox();
        gridPane     = new GridPane();
        gridPane2    = new GridPane();
        lrazonSocial = new Label("Razon Social: ");
        lTelefono    = new Label("Telefono: ");
        lEmail       = new Label("Email: ");
        lDomicilio   = new Label("Datos domiciliarios del Proveedor.");
        lCalle       = new Label("Calle: ");
        lNCasa       = new Label("Numero de casa: ");
        lCP          = new Label("C.P: ");
        lEstado      = new Label("Estado: ");
        lBuscar      = new Label("Buscar: ");
        lId          = new Label("ID: ");
        lIdContainer = new Label();
        lLista       = new Label("Lista de Proveedores.");
        tRazonSocial = new TextField();
        tTelefono    = new TextField();
        tEmail       = new TextField();
        tCalle       = new TextField();
        tNcasa       = new TextField();
        tCP          = new TextField();
        tEstado      = new TextField();
        tBuscar      = new TextField();
        cboxEdad     = new ComboBox<Integer>();
        rbSexoH      = new RadioButton();
        btnMas       = new JFXButton();

        cboxEdad.getItems().addAll(18,19,20,21,22,23,24);
        ObservableList<Empleado> listEmpleados = FXCollections.observableArrayList();

        // Barra de titulo y busqueda.
        hBoxTitulo.getChildren().addAll(titulo, hBoxBusqueda);
        hBoxBusqueda.getChildren().addAll(lBuscar, tBuscar);
        hBoxBusqueda.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hBoxBusqueda, Priority.ALWAYS);
        lBuscar.setPadding(new Insets(0,10,0,0));
        hBoxTitulo.setPadding(new Insets(10,10,10,10));
        hBoxTitulo.getStyleClass().add("panelWhite");

        // Cuadriculas de informacion.
        var columna1 = new ColumnConstraints();
        var columna2 = new ColumnConstraints();
        var columna3 = new ColumnConstraints();
        var columna4 = new ColumnConstraints();
        var columna5 = new ColumnConstraints();
        var columna6 = new ColumnConstraints();

        // Datos del GridPane.
        // Primera columna.
        hBoxSpace.getChildren().addAll(lId, lIdContainer);
        lId.setPadding(new Insets(0,10,0,0));
        gridPane.add(hBoxSpace,0,0);
        gridPane.add(lrazonSocial,0,1);
        gridPane.add(tRazonSocial,1,1);
        gridPane.add(lTelefono,0,2);
        gridPane.add(tTelefono,1,2);
        gridPane.add(lEmail,0,3);
        gridPane.add(tEmail,1,3);

        // Segunda columna.

        // Barra de datos domiciliarios.
        hBoxDomicilio.getChildren().add(lDomicilio);
        hBoxDomicilio.setPadding(new Insets(10,10,10,10));
        hBoxDomicilio.getStyleClass().add("panelWhite");


        // Datos del GridPane2.

        tCalle.setPrefWidth(250);
        tNcasa.setPrefWidth(60);
        tCP.setPrefWidth(60);

        gridPane2.add(lCalle,0,0);
        gridPane2.add(tCalle,1,0);
        gridPane2.add(lNCasa,2,0);
        gridPane2.add(tNcasa,3,0);
        gridPane2.add(lCP,4,0);
        hBoxSpace4.getChildren().addAll(tCP, btnMas);
        gridPane2.add(hBoxSpace4,5,0);

        // Barra de lista de empleados.
        hBoxLista.getChildren().add(lLista);
        hBoxLista.setPadding(new Insets(10,10,10,10));
        hBoxLista.getStyleClass().add("panelWhite");

        // Agrega las columnas al GridPane.
        gridPane.getColumnConstraints().addAll(columna1, columna2, columna3, columna4);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPane2.getColumnConstraints().addAll(columna1, columna2, columna3, columna4, columna5, columna6);
        gridPane2.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));

        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(4);
        gridPane.getStyleClass().add("panelWhite");
        blockGridPaneFields(gridPane);

        gridPane2.setPadding(new Insets(10));
        gridPane2.setHgap(20);
        gridPane2.setVgap(4);
        gridPane2.getStyleClass().add("panelWhite");
        blockGridPaneFields(gridPane2);


        // Margen entre el titulo y el recuadro de informacion.
        VBox.setMargin(gridPane, new Insets(10,0,10,0));
        VBox.setMargin(gridPane2, new Insets(10,0,10,0));
        //VBox.setMargin(hBoxLista, new Insets(10,0,10,0));


        // Tabla de clientes.
        var table = createTable(listProvee);
        establecerCampos(table);

        // Agrega los nodos () al VBox.
        getChildren().addAll(hBoxTitulo, gridPane, hBoxDomicilio, gridPane2, hBoxLista, table);

        setPadding(insetsBase);

        //Hoja de estilos
        getStylesheets().add(getClass().getResource("/estilos/clientes.css").toExternalForm());
    }

    private void blockGridPaneFields(GridPane gridPane) {
        gridPane.getChildren()
                .filtered(x -> x instanceof TextField)
                .forEach(x -> ((TextField) x).setEditable(false));
    }

    private JFXTreeTableView createTable(ObservableList<Proveedor> list) {
        final TreeItem<Proveedor> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        var tableView = new JFXTreeTableView<>(root);

        var clmNombre = new JFXTreeTableColumn<Proveedor, String>("Razon Social");
        var clmApellido = new JFXTreeTableColumn<Proveedor, String>("Email");
        var clmTelefono = new JFXTreeTableColumn<Proveedor, String>("Telefono");
        var clmCorreo = new JFXTreeTableColumn<Proveedor, String>("Direccion");

        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(195);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Proveedor, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().razonSocialProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmApellido.setResizable(false);
        clmApellido.setPrefWidth(195);
        clmApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Proveedor, String> param) -> {
            if (clmApellido.validateValue(param))
                return param.getValue().getValue().correoProperty();
            else
                return clmApellido.getComputedValue(param);
        });

        clmTelefono.setResizable(false);
        clmTelefono.setPrefWidth(180);
        clmTelefono.setCellValueFactory((TreeTableColumn.CellDataFeatures<Proveedor, String> param) -> {
            if (clmTelefono.validateValue(param))
                return param.getValue().getValue().phoneProperty();
            else
                return clmTelefono.getComputedValue(param);
        });

        clmCorreo.setResizable(false);
        clmCorreo.setPrefWidth(190);
        clmCorreo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Proveedor, String> param) -> {
            if (clmCorreo.validateValue(param))
                return param.getValue().getValue().asentamientoProperty();
            else
                return clmCorreo.getComputedValue(param);
        });

        tableView.setShowRoot(false);
        tableView.setEditable(false);
        tableView.getColumns().setAll(clmNombre, clmApellido, clmTelefono, clmCorreo);

        return tableView;
    }

    private void establecerCampos(JFXTreeTableView<Proveedor> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null) {
                proveedor = newValue.getValue();
                lIdContainer.setText(newValue.getValue().getId()+"");
                tRazonSocial.setText(newValue.getValue().getRazonSocial());
                tTelefono.setText(newValue.getValue().getPhone());
                tEmail.setText(newValue.getValue().getCorreo());
                tCalle.setText(newValue.getValue().getCalle());
                tNcasa.setText(newValue.getValue().getNoCasa());
                tEstado.setText(newValue.getValue().getEstado());
                //tCP.setText(newValue.getValue().getCodigoPostal());
                //txtAsentamiento.setText(newValue.getValue().getAsentamiento());
                //txtCiudad.setText(newValue.getValue().getCiudad());
                //txtMunicipio.setText(newValue.getValue().getMunicipio());
                //txtTipoAsentamiento.setText(newValue.getValue().getTipoAsentamiento());
                //txtEdad.setText(newValue.getValue().getEdad()+"");
            }
        }));
    }





}
