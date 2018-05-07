package com.controladores.productos;


import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Conexion;
import com.modelo.Producto;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class OpcionProducto extends VBox {

    //paneles
    private HBox panelTitulo;
    private GridPane panelRegistroProducto;
    private GridPane panelListaProducto;
    private HBox panelBotones;
    private HBox panelBuscar;

    //textfields
    private TextField txtfCodigoBarras;
    private TextField txtfNombre;
    private TextField txtfObservaciones;
    private TextField txtfPrecioCompra;
    private TextField txtfPrecioVenta;
    private TextField txtfExistencia;

    private JFXTextField txtfSearch;
    //buttons
    private JFXButton btnAceptar;
    private JFXButton btnEditar;
    private JFXButton btnEliminar;
    //Conexion
    Conexion conexion = new Conexion();
    //Popup esta seguro
    JFXPopup popupEstaSeguro;

    private ObservableList<Producto> listaProductos;

    public OpcionProducto(){

        listaProductos = FXCollections.observableArrayList();


        panelTitulo = new HBox();
        //inicializacion de los paneles
        panelRegistroProducto = new GridPane();
        panelListaProducto = new GridPane();
        panelBotones = new HBox();


        JFXTreeTableView tblProductos = new JFXTreeTableView();
        tblProductos.getStyleClass().add("jfx-list-cell");
        panelListaProducto.setPadding(new Insets(10));
        panelListaProducto.getChildren().add(tblProductos);
        fillPanelTitulo();
        panelBuscar = fillPanelBuscar();
        fillPaneRegistro();


        //Llenar la tabla
        llenarTabla(tblProductos, listaProductos);

        //margenes
        VBox.setMargin(panelTitulo, new Insets(10,10,10,10));
        VBox.setMargin(panelRegistroProducto, new Insets(0,10,10,10));
        VBox.setMargin(panelListaProducto, new Insets(20,10,5,10));
        VBox.setVgrow(panelListaProducto, Priority.ALWAYS);

        //Conexion para llenar los productos
        conexion.establecerConexion();
        Producto.llenarProductos(conexion.getConection(), listaProductos);
        conexion.cerrarConexion();

        //agregar los paneles principales
        this.getChildren().addAll(panelTitulo,panelRegistroProducto,panelBuscar,panelListaProducto);

        // Fuente de Estilos
        this.getStylesheets().add(getClass().getResource("/estilos/producto.css").toExternalForm());



        /*Eventos de los botones aceptar y editar*/

        btnAceptar.setOnAction(e -> insertProduct(listaProductos));

        setBtnEditar(tblProductos);

        btnEliminar.setOnAction(x -> {
            int selection = tblProductos.getSelectionModel().getSelectedIndex();
            if(selection >=0){
                popupEliminar(listaProductos, tblProductos);
                popupEstaSeguro.show(btnEliminar, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
            }else{
                TrayNotification trayNotification = new TrayNotification();
                trayNotification.setTitle("No selecciono ningun registro");
                trayNotification.setMessage("Seleccione el producto a eliminar");
                trayNotification.setNotificationType(NotificationType.ERROR);
                trayNotification.showAndDismiss(Duration.millis(2000));
            }

        });
        //Evento de buscar
        search(txtfSearch,tblProductos);

    }

    private void setBtnEditar(JFXTreeTableView<Producto> tbl){

        tbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{

            if(newValue!=null){
                final Producto producto = newValue.getValue();
                final int indice = listaProductos.indexOf(producto);
                btnEditar.setOnAction(e->{
                    int selection = tbl.getSelectionModel().getSelectedIndex();

                    if(selection >=0){
                        new EditarProducto(listaProductos,producto,indice);
                    }else{
                        TrayNotification trayNotification = new TrayNotification();
                        trayNotification.setTitle("No selecciono ningun registro");
                        trayNotification.setMessage("Seleccione el producto a editar");
                        trayNotification.setNotificationType(NotificationType.ERROR);
                        trayNotification.showAndDismiss(Duration.millis(2000));
                    }
                });
            }


        });


    }
    private void llenarTabla(JFXTreeTableView tblProductos, ObservableList<Producto> listaProductos) {
        final TreeItem<Producto> root = new RecursiveTreeItem<>(listaProductos, RecursiveTreeObject::getChildren);

        tblProductos.setRoot(root);

        //Columns
        JFXTreeTableColumn<Producto, String> clmCodigo = new JFXTreeTableColumn("Codigo");
        JFXTreeTableColumn<Producto, String> clmNombre = new JFXTreeTableColumn("Nombre");
        JFXTreeTableColumn<Producto, Float> clmPrecioCompra = new JFXTreeTableColumn("Precio Compra");
        JFXTreeTableColumn<Producto, Float> clmPrecioVenta = new JFXTreeTableColumn("Precio Venta");
        JFXTreeTableColumn<Producto, Integer> clmExistencia = new JFXTreeTableColumn("Existencia");
        JFXTreeTableColumn<Producto, Integer> clmDefectuoso = new JFXTreeTableColumn("Defectuoso");
        JFXTreeTableColumn<Producto, String> clmObservaciones = new JFXTreeTableColumn("Observaciones");

        clmCodigo.setResizable(true);
        clmCodigo.setPrefWidth(150);
        clmCodigo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, String> param) -> {
            if (clmCodigo.validateValue(param))
                return param.getValue().getValue().cod_barraProperty();
            else
                return clmCodigo.getComputedValue(param);
        });

        clmNombre.setResizable(true);
        clmNombre.setPrefWidth(250);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmPrecioCompra.setResizable(false);
        clmPrecioCompra.setPrefWidth(100);
        clmPrecioCompra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Float> param) -> {
            if (clmPrecioCompra.validateValue(param))
                return param.getValue().getValue().precio_compraProperty().asObject();
            else
                return clmPrecioCompra.getComputedValue(param);
        });

        clmPrecioVenta.setResizable(false);
        clmPrecioVenta.setPrefWidth(100);
        clmPrecioVenta.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Float> param) -> {
            if (clmPrecioVenta.validateValue(param))
                return param.getValue().getValue().precio_ventaProperty().asObject();
            else
                return clmPrecioVenta.getComputedValue(param);
        });

        clmExistencia.setResizable(false);
        clmExistencia.setPrefWidth(100);
        clmExistencia.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Integer> param) -> {
            if (clmExistencia.validateValue(param))
                return param.getValue().getValue().existenciaProperty().asObject();
            else
                return clmExistencia.getComputedValue(param);
        });

        clmObservaciones.setResizable(false);
        clmObservaciones.setPrefWidth(350);
        clmObservaciones.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, String> param) -> {
            if (clmObservaciones.validateValue(param))
                return param.getValue().getValue().observacionesProperty();
            else
                return clmObservaciones.getComputedValue(param);
        });

        tblProductos.setEditable(false);
        tblProductos.setShowRoot(false);
        tblProductos.getColumns().setAll(clmCodigo,clmNombre,clmPrecioCompra,clmPrecioVenta,clmExistencia,clmDefectuoso,clmObservaciones);

    }


    private HBox fillPanelBuscar(){
        HBox boxSearch = new HBox();
        txtfSearch = new JFXTextField();
        txtfSearch.setTooltip(new Tooltip("Buscar productos"));
        txtfSearch.setPromptText("Buscar");
        Text icoSearch = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH);
        Label bus = new Label();
        bus.setGraphic(icoSearch);
        HBox.setMargin(txtfSearch,new Insets(0,5,0,10));
        boxSearch.setAlignment(Pos.CENTER_LEFT);
        boxSearch.setPadding(new Insets(0,0,0,10));
        boxSearch.getChildren().addAll(txtfSearch,bus);
        return boxSearch;


    }


    public void search(TextField textField, JFXTreeTableView<Producto> treeTableView){

        textField.textProperty().addListener((prop,old,text)->{
            treeTableView.setPredicate(e->{

                String buscarNombre=e.getValue().getNombre().toLowerCase();
                String buscarCod = e.getValue().getCod_barra().toLowerCase();
                if(buscarNombre.contains(text.toLowerCase())){
                    return buscarNombre.contains(text.toLowerCase());
                }else if(buscarCod.contains(text.toLowerCase())){
                    return buscarCod.contains(text.toLowerCase());
                }
                return false;
            } );
        });
    }

    private void fillPanelTitulo() {
        Text title=new Text("Productos");
        panelTitulo.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(panelTitulo,Priority.SOMETIMES);
        panelTitulo.getChildren().addAll(title);
        panelTitulo.getStyleClass().add("panelWhite");
        title.getStyleClass().add("text_title");
        panelTitulo.setPadding(new Insets(10,10,0,10));


    }

    public void fillPaneRegistro(){


        ColumnConstraints columna1 = new ColumnConstraints(10,538,551);
        ColumnConstraints columna2 = new ColumnConstraints(10,538,551);
        ColumnConstraints columna3 = new ColumnConstraints(10,538,551);
        columna1.setHgrow(Priority.ALWAYS);
        columna2.setHgrow(Priority.ALWAYS);
        columna3.setHgrow(Priority.ALWAYS);

        //Primer panel del codigo de barras fila 1 columna 1
        Text txtCodigoBarras = new Text("Codigo");
        txtCodigoBarras.getStyleClass().add("text");
        txtfCodigoBarras = new TextField();
        BorderPane panelCodigo = new BorderPane();
        panelCodigo.setTop(txtCodigoBarras);
        panelCodigo.setCenter(txtfCodigoBarras);


        //segundo panel del nombre fila 1 columna 2
        Text txtNombre = new Text("Nombre");
        txtNombre.getStyleClass().add("text");
        txtfNombre = new TextField();
        BorderPane panelNomb = new BorderPane();
        panelNomb.setTop(txtNombre);
        panelNomb.setCenter(txtfNombre);

        //Tercer panel de observaciones fila 1 columna 3
        Text txtObservaciones = new Text("Observaciones");
        txtObservaciones.getStyleClass().add("text");
        txtfObservaciones = new TextField();
        BorderPane panelObservacion = new BorderPane();
        panelObservacion.setTop(txtObservaciones);
        panelObservacion.setCenter(txtfObservaciones);


        //--------------------------------------------------------------------

        //Primer panel del precioCompra fila 2 columna 1
        Text txtPrecioCompra = new Text("Precio de compra");
        txtPrecioCompra.getStyleClass().add("text");
        txtfPrecioCompra = new TextField();
        BorderPane panelCompra = new BorderPane();
        panelCompra.setTop(txtPrecioCompra);
        panelCompra.setCenter(txtfPrecioCompra);

        // Segundo panel del precioVenta fila 2 columna 2
        Text txtPrecioVenta = new Text("Precio de venta");
        txtPrecioVenta.getStyleClass().add("text");
        txtfPrecioVenta = new TextField();
        BorderPane panelVenta = new BorderPane();
        panelVenta.setTop(txtPrecioVenta);
        panelVenta.setCenter(txtfPrecioVenta);

        // Tercer panel de existencia
        Text txtExistencia = new Text("Existencia");
        txtExistencia.getStyleClass().add("text");
        txtfExistencia = new TextField();
        BorderPane panelExistence = new BorderPane();
        panelExistence.setTop(txtExistencia);
        panelExistence.setCenter(txtfExistencia);

        //----------------------------------------------------------------------





        // Panel contenedor de los botones aceptar y editar
        /*Button aceptar propiedades*/
        Text icoAceptar = GlyphsDude.createIcon(FontAwesomeIcon.CHECK);
        btnAceptar = new JFXButton();
        btnAceptar.setGraphic(icoAceptar);
        btnAceptar.getStyleClass().add("btnRaisedGreenDark");
        icoAceptar.setFill(Color.WHITE);
        btnAceptar.setTooltip(new Tooltip("Aceptar"));



        /*Button editar propiedades*/
        Text icoEditar = GlyphsDude.createIcon(FontAwesomeIcon.EDIT);
        btnEditar = new JFXButton();
        btnEditar.setGraphic(icoEditar);
        btnEditar.getStyleClass().add("btnRaisedGreenDarkGrass");
        icoEditar.setFill(Color.WHITE);
        btnEditar.setTooltip(new Tooltip("Editar"));

        /*Button eliminar propiedades*/
        Text icoEliminar = GlyphsDude.createIcon(FontAwesomeIcon.REMOVE);
        btnEliminar = new JFXButton();
        btnEliminar.setGraphic(icoEliminar);
        btnEliminar.getStyleClass().add("btnRaisedRed");
        icoEliminar.setFill(Color.WHITE);
        btnEliminar.setTooltip(new Tooltip("Eliminar"));

        /*Insertar en el panel de botones*/
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.getChildren().addAll(btnAceptar,btnEditar,btnEliminar);

        HBox.setHgrow(panelBotones,Priority.ALWAYS);
        HBox.setMargin(btnAceptar,new Insets(5,5,5,5));
        HBox.setMargin(btnEditar,new Insets(5,5,5,5));

        //Insertar los paneles a los constraints
        GridPane.setConstraints(panelCodigo,0,0);
        GridPane.setConstraints(panelNomb,1,0);
        GridPane.setConstraints(panelObservacion,2,0);

        GridPane.setConstraints(panelCompra,0,1);
        GridPane.setConstraints(panelVenta,1,1);
        GridPane.setConstraints(panelExistence,2,1);
        GridPane.setConstraints(panelBotones,3,1);




        panelRegistroProducto.getColumnConstraints().addAll(columna1,columna2,columna3);
        panelRegistroProducto.getChildren().addAll(panelCodigo, panelNomb, panelObservacion, panelCompra,panelVenta,panelExistence,panelBotones);
        panelRegistroProducto.setVgap(15);
        panelRegistroProducto.setHgap(10);
        panelRegistroProducto.setPadding(new Insets(10));

        panelRegistroProducto.getStyleClass().add("white");


    }


    private void popupEliminar(ObservableList<Producto> list, JFXTreeTableView tableView) {

        var vBox = new VBox();
        var gridPane = new GridPane();

        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();

        var lblSeguro = new Label("¿Esta seguro de eliminar este producto?");
        var btnAceptar = new JFXButton("Eliminar");
        btnAceptar.getStyleClass().add("btnRaisedRedEliminar");
        var btnCancelar = new JFXButton("Cancelar");
        btnCancelar.getStyleClass().add("btnRaisedGreenCancelar");

        btnAceptar.setOnAction(x -> deleteProduct(list, tableView));
        btnCancelar.setOnAction(x -> popupEstaSeguro.hide());

        gridPane.add(btnAceptar, 0,0);
        gridPane.add(btnCancelar,1,0);

        vBox.getChildren().addAll(lblSeguro, gridPane);

        gridPane.setHgap(20);
        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.getColumnConstraints().forEach(x -> {
            x.setHgrow(Priority.SOMETIMES);
            x.setHalignment(HPos.CENTER);
        });

        vBox.getStylesheets().setAll("/estilos/producto.css");
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(lblSeguro, new Insets(0,0,10,0));

        popupEstaSeguro = new JFXPopup(vBox);
    }
    public void insertProduct(ObservableList<Producto> listp){


        if( !(txtfCodigoBarras.getText().isEmpty()) && !(txtfNombre.getText().isEmpty()) &&
                !(txtfPrecioCompra.getText().isEmpty()) &&
                !(txtfPrecioVenta.getText().isEmpty()) && !(txtfExistencia.getText().isEmpty())){

            String codigo_barras = txtfCodigoBarras.getText();
            String nombre = txtfNombre.getText();
            float precio_compra = Float.parseFloat(txtfPrecioCompra.getText());
            float precio_venta = Float.parseFloat(txtfPrecioVenta.getText());
            int existencia = Integer.parseInt(txtfExistencia.getText());
            String observaciones = txtfObservaciones.getText();

            Producto product = new Producto(codigo_barras,nombre,observaciones,precio_compra,precio_venta,existencia);

            conexion.establecerConexion();
            int resultSuccess = product.insertarProducto(conexion.getConection());
            conexion.cerrarConexion();
            if (resultSuccess == 1) {
                listp.add(product);
                clearFields();
                TrayNotification trayNotification = new TrayNotification();
                trayNotification.setTitle("Completado");
                trayNotification.setMessage("Se agregó exitosamente!");
                trayNotification.setNotificationType(NotificationType.SUCCESS);
                trayNotification.showAndDismiss(Duration.millis(3000));
            } else {
                TrayNotification trayNotification = new TrayNotification();
                trayNotification.setTitle("Algo salio mal...");
                trayNotification.setMessage("Intentelo de nuevo");
                trayNotification.setNotificationType(NotificationType.ERROR);
                trayNotification.showAndDismiss(Duration.millis(2000));

            }
        }else{
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("Campos no introducidos");
            trayNotification.setMessage("Por favor llene los campos!");
            trayNotification.setNotificationType(NotificationType.ERROR);
            trayNotification.showAndDismiss(Duration.millis(2000));
        }
    }
    public void clearFields(){
        txtfCodigoBarras.setText("");
        txtfObservaciones.setText("");
        txtfNombre.setText("");
        txtfPrecioVenta.setText("");
        txtfPrecioCompra.setText("");
        txtfExistencia.setText("");
    }
    public void deleteProduct(ObservableList<Producto> listP,JFXTreeTableView tableView){

        try{
            Producto product = listP.get(tableView.getSelectionModel().getSelectedIndex());

            conexion.establecerConexion();
            int resultSuccess = product.eliminarProducto(conexion.getConection());
            conexion.cerrarConexion();
            if (resultSuccess == 1) {
                listP.remove(product);
                TrayNotification trayNotification = new TrayNotification();
                trayNotification.setTitle("Completado");
                trayNotification.setMessage("Se eliminó correctamente!");
                trayNotification.setNotificationType(NotificationType.SUCCESS);
                trayNotification.showAndDismiss(Duration.millis(2000));
            } else {
                TrayNotification trayNotification = new TrayNotification();
                trayNotification.setTitle("Algo salio mal...");
                trayNotification.setMessage("Intentelo de nuevo");
                trayNotification.setNotificationType(NotificationType.ERROR);
                trayNotification.showAndDismiss(Duration.millis(2000));

            }

        }catch (NumberFormatException e) {
            TrayNotification trayNotification = new TrayNotification();
            trayNotification.setTitle("No selecciono registro");
            trayNotification.setMessage("Seleccione el producto a eliminar");
            trayNotification.setNotificationType(NotificationType.ERROR);
            trayNotification.showAndDismiss(Duration.millis(2000));
        }



    }


}
