package com.controladores.clientes;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Conexion;
import com.modelo.cliente.Cliente;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


public class Opcion_clientes extends VBox {

    Conexion connection = new Conexion();
    private Cliente cliente = null;
    private Insets insetsBase = new Insets(10);
    private Label titulo = new Label("Datos del Cliente.");
    private HBox hBoxTitulo = new HBox();
    private HBox hBoxDomicilio = new HBox();
    private HBox hBoxBusqueda = new HBox();
    private HBox hBoxBotones = new HBox();
    private HBox hBoxLista = new HBox();
    private HBox hBoxSpace = new HBox();
    private HBox hBoxSpace2 = new HBox();
    private HBox hBoxSpace3 = new HBox();
    private HBox hBoxSpace4 = new HBox();
    private GridPane gridPane = new GridPane();
    private GridPane gridPane2 = new GridPane();
    private Label lnombre = new Label("Nombre: ");
    private Label lApellidoP = new Label("Apellido paterno: ");
    private Label lApellidoM = new Label("Apellido materno: ");
    private Label lEdad = new Label("Edad: ");
    private Label lSexoH = new Label("Hombre: ");
    private Label lSexoM = new Label("Mujer: ");
    private Label lTelefono = new Label("Telefono: ");
    private Label lEmail = new Label("Email: ");
    private Label lDomicilio = new Label("Datos domiciliarios del Cliente.");
    private Label lCalle = new Label("Calle: ");
    private Label lNCasa = new Label("Numero de casa: ");
    private Label lCP = new Label("C.P: ");
    private Label lBuscar = new Label("Buscar: ");
    private Label lId = new Label("ID: ");
    private Label lIdContainer = new Label("13");
    private Label lLista = new Label("Lista de Clientes.");
    private TextField tNombre = new TextField();
    private TextField tApellidoP = new TextField();
    private TextField tApellidoM = new TextField();
    private TextField tTelefono = new TextField();
    private TextField tEmail = new TextField();
    private TextField tCalle = new TextField();
    private TextField tNcasa = new TextField();
    private TextField tCP = new TextField();
    private TextField tBuscar = new TextField();
    private ComboBox cboxEdad = new ComboBox<Integer>();
    private RadioButton rbSexoH = new RadioButton();
    private RadioButton rbSexoM = new RadioButton();   //Group - Cliente femenino
    private JFXButton btnAnadir = new JFXButton();
    private JFXButton btnEditar = new JFXButton();
    private JFXButton btnEliminar = new JFXButton();
    private JFXButton btnMas = new JFXButton();
    private Label lEstado = new Label("Estado: ");
    private Label lCiudad = new Label("Ciudad: ");
    private Label lMunicipio = new Label("Municipio: ");
    private Label lTipoAsentamiento = new Label("Tipo: ");
    private Label lAsentamiento = new Label("Asentamiento: ");
    private TextField tEstado = new TextField();
    private TextField tCiudad = new TextField();
    private TextField tMunicipio = new TextField();
    private TextField tTipoAsentamiento = new TextField();
    private TextField tAsentamiento = new TextField();
    private JFXPopup popupCodigoPostal;

    public Opcion_clientes(){
        var icoEdit = GlyphsDude.createIcon(FontAwesomeIcon.EDIT,"14px");
        var icoAdd = GlyphsDude.createIcon(FontAwesomeIcon.USER_PLUS,"14px");
        var icoDelete = GlyphsDude.createIcon(FontAwesomeIcon.TRASH_ALT,"14px");
        var icoInfo = GlyphsDude.createIcon(FontAwesomeIcon.INFO,"14px");

        cboxEdad.getItems().addAll(18,19,20,21,22,23,24);
        ObservableList<Cliente> listClientes = null;

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
        gridPane.add(lnombre,0,1);
        gridPane.add(tNombre,1,1);
        gridPane.add(lApellidoP,0,2);
        gridPane.add(tApellidoP,1,2);
        gridPane.add(lApellidoM,0,3);
        gridPane.add(tApellidoM,1,3);

        // Segunda columna.
        hBoxSpace2.getChildren().addAll(lEdad,cboxEdad);
        lEdad.setPadding(new Insets(0,10,0,0));
        gridPane.add(hBoxSpace2,2,1);
        hBoxSpace3.getChildren().addAll(lSexoH, rbSexoH, lSexoM, rbSexoM);
        rbSexoH.setPadding(new Insets(0,10,0,0));
        gridPane.add(hBoxSpace3,3,1);
        gridPane.add(lTelefono,2,2);
        gridPane.add(tTelefono,3,2);
        gridPane.add(lEmail,2,3);
        gridPane.add(tEmail,3,3);

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
        HBox.setMargin(btnMas,new Insets(0,0,0,10));
        gridPane2.add(hBoxSpace4,5,0);

        // Barra de lista de Clientes.
        hBoxLista.getChildren().addAll(lLista, hBoxBotones);
        hBoxBotones.getChildren().addAll(btnAnadir, btnEditar, btnEliminar);
        hBoxBotones.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hBoxBotones, Priority.ALWAYS);
        hBoxLista.setPadding(new Insets(10,10,10,10));
        hBoxLista.getStyleClass().add("panelWhite");
        HBox.setMargin(btnEditar, new Insets(0,0,0,10));
        HBox.setMargin(btnEliminar, new Insets(0,0,0,10));

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

        // Botones e iconos.
        setStyleIcons(icoAdd, icoDelete, icoEdit, icoInfo);
        btnAnadir.setGraphic(icoAdd);
        btnEditar.setGraphic(icoEdit);
        btnEliminar.setGraphic(icoDelete);
        btnMas.setGraphic(icoInfo);

        // Margen entre el titulo y el recuadro de informacion.
        VBox.setMargin(gridPane, new Insets(10,0,10,0));
        VBox.setMargin(gridPane2, new Insets(10,0,10,0));
        VBox.setMargin(hBoxLista, new Insets(0,0,10,0));

        // Conexion con base de datos.
        connection.establecerConexion();
        listClientes = Cliente.llenarClientes(connection.getConection());
        connection.cerrarConexion();

        // Tabla de clientes.
        var table = createTable(listClientes);
        establecerCampos(table);

        // Accion de los botones.
        btnMas.setOnAction(x -> {
                    initPopUpCodigoPostal();
                    popupCodigoPostal.show(btnMas, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.RIGHT);// Agrega los nodos () al VBox.
                });getChildren().addAll(hBoxTitulo, gridPane, hBoxDomicilio, gridPane2, hBoxLista, table);

        setPadding(insetsBase);

        //Hoja de estilos
        getStylesheets().add(getClass().getResource("/estilos/clientes.css").toExternalForm());

    }

    private void blockGridPaneFields(GridPane gridPane) {
        gridPane.getChildren()
                .filtered(x -> x instanceof TextField)
                .forEach(x -> ((TextField) x).setEditable(false));
    }

    private JFXTreeTableView createTable(ObservableList<Cliente> list) {
        final TreeItem<Cliente> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        var tableView = new JFXTreeTableView<>(root);

        var clmNombre = new JFXTreeTableColumn<Cliente, String>("Nombre");
        var clmApellidoP = new JFXTreeTableColumn<Cliente, String>("Primer apellido");
        var clmApellidoM = new JFXTreeTableColumn<Cliente, String>("Segundo apellido");
        var clmEdad = new JFXTreeTableColumn<Cliente, Integer>("Edad");
        var clmTel = new JFXTreeTableColumn<Cliente, String>("Telefono");
        var clmCorreo = new JFXTreeTableColumn<Cliente, String>("Correo");


        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(195);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cliente, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmApellidoP.setResizable(false);
        clmApellidoP.setPrefWidth(195);
        clmApellidoP.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cliente, String> param) -> {
            if (clmApellidoP.validateValue(param))
                return param.getValue().getValue().primer_apellidoProperty();
            else
                return clmApellidoP.getComputedValue(param);
        });

        clmApellidoM.setResizable(false);
        clmApellidoM.setPrefWidth(180);
        clmApellidoM.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cliente, String> param) -> {
            if (clmApellidoM.validateValue(param))
                return param.getValue().getValue().segundo_apellidoProperty();
            else
                return clmApellidoM.getComputedValue(param);
        });

        clmEdad.setResizable(false);
        clmEdad.setPrefWidth(190);
        clmEdad.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cliente, Integer> param) -> {
            if (clmEdad.validateValue(param))
                return param.getValue().getValue().edadProperty().asObject();
            else
                return clmEdad.getComputedValue(param);
        });

        clmTel.setResizable(false);
        clmTel.setPrefWidth(195);
        clmTel.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cliente, String> param) -> {
            if (clmTel.validateValue(param))
                return param.getValue().getValue().telefonoProperty();
            else
                return clmTel.getComputedValue(param);
        });

        clmCorreo.setResizable(false);
        clmCorreo.setPrefWidth(195);
        clmCorreo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Cliente, String> param) -> {
            if (clmCorreo.validateValue(param))
                return param.getValue().getValue().correoProperty();
            else
                return clmCorreo.getComputedValue(param);
        });

        tableView.setShowRoot(false);
        tableView.setEditable(false);
        tableView.getColumns().setAll(clmNombre, clmApellidoP, clmApellidoM, clmEdad, clmTel, clmCorreo);

        return tableView;
    }

    private void setStyleIcons(Text... text) {
        for(Text t: text)
            t.getStyleClass().add("ico");
    }

    private void establecerCampos(JFXTreeTableView<Cliente> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null) {
                cliente = newValue.getValue();
                lIdContainer.setText(newValue.getValue().getId()+"");
                tNombre.setText(newValue.getValue().getNombre());
                tApellidoP.setText(newValue.getValue().getPrimer_apellido());
                tApellidoM.setText(newValue.getValue().getSegundo_apellido());
                cboxEdad.setValue(newValue.getValue().getEdad()+"");
                if (newValue.getValue().getSexo().equals("hombre"))
                    rbSexoH.setSelected(true);
                else
                    rbSexoM.setSelected(true);
                tTelefono.setText(newValue.getValue().getTelefono());
                tEmail.setText(newValue.getValue().getCorreo());
                tEstado.setText(newValue.getValue().getEstado());
                tCalle.setText(newValue.getValue().getnCalle());
                tNcasa.setText(newValue.getValue().getnCasa()+"");
                tCP.setText(newValue.getValue().getCodigoPostal());
                tAsentamiento.setText(newValue.getValue().getAsentamiento());
                tCiudad.setText(newValue.getValue().getCiudad());
                tMunicipio.setText(newValue.getValue().getMunicipio());
                tTipoAsentamiento.setText(newValue.getValue().getTipo_asentamiento());
            }
        }));
    }

    private void initPopUpCodigoPostal() {
        var pane = new GridPane();

        //Columns
        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();

        column1.setHgrow(Priority.SOMETIMES);
        column2.setHgrow(Priority.ALWAYS);

        pane.add(lEstado,0,0);
        pane.add(lCiudad,0,1);
        pane.add(lMunicipio,0,2);
        pane.add(lTipoAsentamiento,0,3);
        pane.add(lAsentamiento,0,4);

        pane.add(tEstado,1,0);
        pane.add(tCiudad,1,1);
        pane.add(tMunicipio,1,2);
        pane.add(tTipoAsentamiento,1,3);
        pane.add(tAsentamiento,1,4);

        pane.getChildren().forEach(x -> {
            if (x instanceof TextField)
                ((TextField) x).setEditable(false);
        });

        pane.getStylesheets().setAll("/estilos/empleados.css");
        pane.setHgap(20);
        pane.setVgap(4);
        pane.setPrefSize(400,190);
        pane.getColumnConstraints().addAll(column1, column2);
        pane.setPadding(new Insets(10));

        popupCodigoPostal = new JFXPopup(pane);
    }
}
