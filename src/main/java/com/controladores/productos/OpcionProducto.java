package com.controladores.productos;


import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Conexion;
import com.modelo.Producto;
import com.validators.Messages;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OpcionProducto extends VBox {

    //paneles
    private HBox panelTitulo;
    private GridPane panelRegistroProducto;
    private GridPane panelListaProducto;
    private HBox panelBotones;
    private HBox panelButtons;

    //textfields
    private TextField txtfCodigoBarras;
    private TextField txtfNombre;
    private TextField txtfObservaciones;
    private TextField txtfPrecioCompra;
    private TextField txtfPrecioVenta;
    private TextField txtfExistencia;

    private TextField txtfSearch;
    //buttons
    private JFXButton btnAceptar;
    private JFXButton btnEditar;
    private JFXButton btnEliminar;
    //Conexion
    Conexion conexion = new Conexion();
    //Popup esta seguro
    JFXPopup popupEstaSeguro;

    // Messages
    Messages message = new Messages();

    // Producto a seleccionar
    Producto producto;
    int indice;

    private ObservableList<Producto> listaProductos;

    public OpcionProducto(){

        listaProductos = FXCollections.observableArrayList();


        panelTitulo = new HBox();
        //inicializacion de los paneles
        panelRegistroProducto = new GridPane();
        panelListaProducto = new GridPane();
        panelBotones = new HBox();


        JFXTreeTableView tblProductos = new JFXTreeTableView();
        panelListaProducto.getStyleClass().add("white");
        panelListaProducto.getChildren().add(tblProductos);
        fillPanelTitulo();
        panelButtons = fillPanelButtons();
        fillPaneRegistro();


        //Llenar la tabla
        llenarTabla(tblProductos, listaProductos);

        //margenes
        VBox.setMargin(panelTitulo, new Insets(10,10,10,10));
        VBox.setMargin(panelRegistroProducto, new Insets(0,10,10,10));
        VBox.setMargin(panelButtons,new Insets(0,10,0,10));
        VBox.setMargin(panelListaProducto, new Insets(20,10,5,10));
        VBox.setVgrow(panelListaProducto, Priority.ALWAYS);

        //Conexion para llenar los productos
        conexion.establecerConexion();
        Producto.llenarProductos(conexion.getConection(), listaProductos);
        conexion.cerrarConexion();

        //agregar los paneles principales
        this.getChildren().addAll(panelTitulo,panelRegistroProducto,panelButtons,panelListaProducto);

        // Fuente de Estilos
        this.getStylesheets().add(getClass().getResource("/estilos/producto.css").toExternalForm());


        /*Eventos de los botones aceptar y editar*/

        btnAceptar.setOnAction(e -> insertProduct(listaProductos));

        btnEditar.setOnAction(e->{
            int selection = tblProductos.getSelectionModel().getSelectedIndex();
            if(selection >=0){
                new EditarProducto(listaProductos,producto,indice);
            }else{
                message.setMessage("No selecciono ningun registro","Seleccione el producto a editar",NotificationType.INFORMATION);
            }
        });

        setBtnEditar(tblProductos);

        btnEliminar.setOnAction(x -> {
            int selection = tblProductos.getSelectionModel().getSelectedIndex();
            if(selection >=0){
                popupEliminar(listaProductos, tblProductos);
                popupEstaSeguro.show(btnEliminar, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
            }else{
                message.setMessage("No selecciono ningun registro","Seleccione el producto a eliminar",NotificationType.WARNING);
            }

        });
        //Evento de buscar
        search(txtfSearch,tblProductos);

    }

    private void setBtnEditar(JFXTreeTableView<Producto> tbl){
        tbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{

            if(newValue!=null){
                producto = newValue.getValue();
                indice = listaProductos.indexOf(producto);
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
        clmObservaciones.setPrefWidth(380);
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


    private HBox fillPanelButtons(){
        HBox box = new HBox();
        /*Button aceptar propiedades*/
        Text icoAceptar = GlyphsDude.createIcon(FontAwesomeIcon.PLUS);
        btnAceptar = new JFXButton();
        btnAceptar.setGraphic(icoAceptar);
        icoAceptar.setFill(Color.WHITE);
        btnAceptar.setTooltip(new Tooltip("Aceptar"));

        /*Button editar propiedades*/
        Text icoEditar = GlyphsDude.createIcon(FontAwesomeIcon.EDIT);
        btnEditar = new JFXButton();
        btnEditar.setGraphic(icoEditar);
        icoEditar.setFill(Color.WHITE);
        btnEditar.setTooltip(new Tooltip("Editar"));

        /*Button eliminar propiedades*/
        Text icoEliminar = GlyphsDude.createIcon(FontAwesomeIcon.REMOVE);
        btnEliminar = new JFXButton();
        btnEliminar.setGraphic(icoEliminar);
        icoEliminar.setFill(Color.WHITE);
        btnEliminar.setTooltip(new Tooltip("Eliminar"));

        /*Insertar en el panel de botones*/
        panelBotones.setAlignment(Pos.BASELINE_RIGHT);
        panelBotones.setPadding(new Insets(5,10,5,10));
        panelBotones.getChildren().addAll(btnAceptar,btnEditar,btnEliminar);

        HBox.setHgrow(panelBotones,Priority.ALWAYS);
        HBox.setMargin(btnAceptar,new Insets(5,5,5,5));
        HBox.setMargin(btnEditar,new Insets(5,5,5,5));
        HBox.setHgrow(box,Priority.ALWAYS);
        box.getChildren().add(panelBotones);

        return box;

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
        title.getStyleClass().add("text_title");
        panelTitulo.getStyleClass().add("white");
        panelTitulo.setAlignment(Pos.CENTER_LEFT);
        panelTitulo.setPadding(new Insets(0,0,0,10));
        HBox.setHgrow(panelTitulo,Priority.ALWAYS);

        HBox search = new HBox();
        txtfSearch = new TextField();
        txtfSearch.setTooltip(new Tooltip("Buscar productos"));
        txtfSearch.setPromptText("Buscar");
        Text icoSearch = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH);
        Label bus = new Label();
        bus.setGraphic(icoSearch);
        HBox.setMargin(txtfSearch,new Insets(0,10,0,10));

        search.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(search,Priority.ALWAYS);
        search.setPadding(new Insets(5,10,5,0));
        search.getChildren().addAll(txtfSearch,bus);

        panelTitulo.getChildren().addAll(title,search);


    }

    public void fillPaneRegistro(){

        ColumnConstraints columna1 = new ColumnConstraints(10,100,200);
        ColumnConstraints columna2 = new ColumnConstraints(10,200,250);
        ColumnConstraints columna3 = new ColumnConstraints(10,120,200);
        ColumnConstraints columna4 = new ColumnConstraints(10,90,200);
        ColumnConstraints columna5 = new ColumnConstraints(10,120,200);
        ColumnConstraints columna6 = new ColumnConstraints();

        //codigo de barras
        Text txtCodigoBarras = new Text("Codigo");
        txtCodigoBarras.getStyleClass().add("text");
        txtfCodigoBarras = new TextField();

        //nombre
        Text txtNombre = new Text("Nombre");
        txtNombre.getStyleClass().add("text");
        txtfNombre = new TextField();

        //observaciones
        Text txtObservaciones = new Text("Observaciones");
        txtObservaciones.getStyleClass().add("text");
        txtfObservaciones = new TextField();
        txtfObservaciones.setPrefWidth(220);

        //--------------------------------------------------------------------

        //precioCompra
        Text txtPrecioCompra = new Text("Precio de compra");
        txtPrecioCompra.getStyleClass().add("text");
        txtfPrecioCompra = new TextField();

        //precioVenta
        Text txtPrecioVenta = new Text("Precio de venta");
        txtPrecioVenta.getStyleClass().add("text");
        txtfPrecioVenta = new TextField();

        //existencia
        Text txtExistencia = new Text("Existencia");
        txtExistencia.getStyleClass().add("text");
        HBox exist = new HBox();
        txtfExistencia = new TextField();
        txtfExistencia.setPrefColumnCount(5);
        exist.getChildren().add(txtfExistencia);

        //----------------------------------------------------------------------

        //Insertar los paneles a los constraints
        panelRegistroProducto.add(txtCodigoBarras,0,0);
        panelRegistroProducto.add(txtfCodigoBarras,1,0);

        panelRegistroProducto.add(txtPrecioCompra,2,0);
        panelRegistroProducto.add(txtfPrecioCompra,3,0);

        panelRegistroProducto.add(txtObservaciones,4,0);
        panelRegistroProducto.add(txtfObservaciones,5,0);

        panelRegistroProducto.add(txtNombre,0,1);
        panelRegistroProducto.add(txtfNombre,1,1);

        panelRegistroProducto.add(txtPrecioVenta,2,1);
        panelRegistroProducto.add(txtfPrecioVenta,3,1);

        panelRegistroProducto.add(txtExistencia,4,1);
        panelRegistroProducto.add(exist,5,1);

        panelRegistroProducto.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        panelRegistroProducto.getColumnConstraints().addAll(columna1,columna2,columna3,columna4,columna5,columna6);
        panelRegistroProducto.setVgap(20);
        panelRegistroProducto.setHgap(10);
        panelRegistroProducto.setAlignment(Pos.CENTER);
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
                message.setMessage("Completado","Se agregó exitosamente!",NotificationType.SUCCESS);
            } else {
                message.setMessage("Algo salio mal","Intentelo de nuevo",NotificationType.ERROR);

            }
        }else{
            message.setMessage("Campos no introducidos","Por favor llene los campos!",NotificationType.INFORMATION);
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

            conexion.establecerConexion();
            int resultSuccess = producto.eliminarProducto(conexion.getConection());
            conexion.cerrarConexion();
            if (resultSuccess == 1) {
                listP.remove(producto);
                message.setMessage("Completado","Se eliminó correctamente!",NotificationType.SUCCESS);
            } else {
                message.setMessage("Algo salio mal","Intentelo de nuevo",NotificationType.ERROR);
            }

        }catch (NumberFormatException e) {
            message.setMessage("No selecciono ningun registro","Seleccione el producto a eliminar",NotificationType.WARNING);
        }

    }


}
