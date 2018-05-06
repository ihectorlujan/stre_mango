package com.controladores.productos;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.Conexion;
import com.modelo.Producto;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class OpcionProducto extends VBox {

    //paneles
    private HBox panelTitulo;
    private GridPane panelRegistroProducto;
    private GridPane panelListaProducto;
    private HBox panelBotones;

    //Conexion
    Conexion conexion = new Conexion();


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
        fillPaneRegistro();


        //Llenar la tabla
        llenarTabla(tblProductos, listaProductos);

        //margenes
        VBox.setMargin(panelTitulo, new Insets(10,10,10,10));
        VBox.setMargin(panelRegistroProducto, new Insets(0,10,10,10));
        VBox.setMargin(panelListaProducto, new Insets(10,10,10,10));
        VBox.setVgrow(panelListaProducto, Priority.ALWAYS);

        //Conexion para llenar los productos
        conexion.establecerConexion();
        Producto.llenarProductos(conexion.getConection(), listaProductos);
        conexion.cerrarConexion();

        //agregar los paneles principales
        this.getChildren().addAll(panelTitulo,panelRegistroProducto,panelListaProducto);

        // Fuente de Estilos
        this.getStylesheets().add(getClass().getResource("/estilos/producto.css").toExternalForm());

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
        clmNombre.setPrefWidth(200);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmPrecioCompra.setResizable(false);
        clmPrecioCompra.setPrefWidth(150);
        clmPrecioCompra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Float> param) -> {
            if (clmPrecioCompra.validateValue(param))
                return param.getValue().getValue().precio_compraProperty().asObject();
            else
                return clmPrecioCompra.getComputedValue(param);
        });

        clmPrecioVenta.setResizable(false);
        clmPrecioVenta.setPrefWidth(150);
        clmPrecioVenta.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Float> param) -> {
            if (clmPrecioVenta.validateValue(param))
                return param.getValue().getValue().precio_ventaProperty().asObject();
            else
                return clmPrecioVenta.getComputedValue(param);
        });

        clmExistencia.setResizable(false);
        clmExistencia.setPrefWidth(150);
        clmExistencia.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Integer> param) -> {
            if (clmExistencia.validateValue(param))
                return param.getValue().getValue().existenciaProperty().asObject();
            else
                return clmExistencia.getComputedValue(param);
        });
//
//        clmDefectuoso.setResizable(true);
//        clmDefectuoso.setPrefWidth(150);
//        clmDefectuoso.setCellValueFactory((TreeTableColumn.CellDataFeatures<Producto, Integer> param) -> {
//            if (clmDefectuoso.validateValue(param))
//                return param.getValue().getValue().cantidad_defectuosoProperty().asObject();
//            else
//                return clmDefectuoso.getComputedValue(param);
//        });


        clmObservaciones.setResizable(false);
        clmObservaciones.setPrefWidth(200);
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

    private void fillPanelTitulo() {
        Text title=new Text("Productos");
        panelTitulo.setAlignment(Pos.CENTER_LEFT);
        VBox.setVgrow(panelTitulo,Priority.SOMETIMES);
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
        TextField txtfCodigoBarras = new TextField();
        BorderPane panelCodigo = new BorderPane();
        panelCodigo.setTop(txtCodigoBarras);
        panelCodigo.setCenter(txtfCodigoBarras);


        //segundo panel del nombre fila 1 columna 2
        Text txtNombre = new Text("Nombre");
        txtNombre.getStyleClass().add("text");
        TextField txtfNombre = new TextField();
        BorderPane panelNomb = new BorderPane();
        panelNomb.setTop(txtNombre);
        panelNomb.setCenter(txtfNombre);

        //Tercer panel de observaciones fila 1 columna 3
        Text txtObservaciones = new Text("Observaciones");
        txtObservaciones.getStyleClass().add("text");
        TextField txtfObservaciones = new TextField();
        BorderPane panelObservacion = new BorderPane();
        panelObservacion.setTop(txtObservaciones);
        panelObservacion.setCenter(txtfObservaciones);


        //--------------------------------------------------------------------

        //Primer panel del precioCompra fila 2 columna 1
        Text txtPrecioCompra = new Text("Precio de compra");
        txtPrecioCompra.getStyleClass().add("text");
        TextField txtfPrecioCompra = new TextField();
        BorderPane panelCompra = new BorderPane();
        panelCompra.setTop(txtPrecioCompra);
        panelCompra.setCenter(txtfPrecioCompra);

        // Segundo panel del precioVenta fila 2 columna 2
        Text txtPrecioVenta = new Text("Precio de venta");
        txtPrecioVenta.getStyleClass().add("text");
        TextField txtfPrecioVenta = new TextField();
        BorderPane panelVenta = new BorderPane();
        panelVenta.setTop(txtPrecioVenta);
        panelVenta.setCenter(txtfPrecioVenta);

        // Tercer panel de existencia
        Text txtExistencia = new Text("Existencia");
        txtExistencia.getStyleClass().add("text");
        TextField txtfExistencia = new TextField();
        BorderPane panelExistence = new BorderPane();
        panelExistence.setTop(txtExistencia);
        panelExistence.setCenter(txtfExistencia);

        //----------------------------------------------------------------------

        //Primer panel de productos defectuosos
        Text txtCanDefectuoso = new Text("Cantidad defectuoso");
        txtCanDefectuoso.getStyleClass().add("text");
        TextField txtfCanDefectuoso = new TextField();
        BorderPane panelDefectuoso = new BorderPane();
        panelDefectuoso.setTop(txtCanDefectuoso);
        panelDefectuoso.setCenter(txtfCanDefectuoso);



        // Panel contenedor de los botones aceptar y editar
        /*Button aceptar propiedades*/
        Text icoAceptar = GlyphsDude.createIcon(FontAwesomeIcon.CHECK);
        JFXButton btnAceptar = new JFXButton();
        btnAceptar.setGraphic(icoAceptar);
        btnAceptar.getStyleClass().add("btnRaisedGreenDark");
        icoAceptar.setFill(Color.WHITE);
        btnAceptar.setTooltip(new Tooltip("Aceptar"));



        /*Button editar propiedades*/
        Text icoEditar = GlyphsDude.createIcon(FontAwesomeIcon.EDIT);
        JFXButton btnEditar = new JFXButton();
        btnEditar.setGraphic(icoEditar);
        btnEditar.getStyleClass().add("btnRaisedGreenDarkGrass");
        icoEditar.setFill(Color.WHITE);
        btnEditar.setTooltip(new Tooltip("Editar"));

        /*Insertar en el panel de botones*/
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.getChildren().addAll(btnAceptar,btnEditar);

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

        GridPane.setConstraints(panelDefectuoso,3,0);
        GridPane.setConstraints(panelBotones,3,1);




        panelRegistroProducto.getColumnConstraints().addAll(columna1,columna2,columna3);
        panelRegistroProducto.getChildren().addAll(panelCodigo, panelNomb, panelObservacion, panelCompra,panelVenta,panelExistence,panelDefectuoso,panelBotones);
        panelRegistroProducto.setVgap(15);
        panelRegistroProducto.setHgap(10);
        panelRegistroProducto.setPadding(new Insets(10));

        panelRegistroProducto.getStyleClass().add("white");
        VBox.setMargin(panelRegistroProducto, new Insets(10,0,10,0));



        /*Eventos de los botones aceptar y editar*/

        btnAceptar.setOnAction(e ->{
            if( !(txtfCodigoBarras.getText().isEmpty()) && !(txtfNombre.getText().isEmpty()) &&
                !(txtfObservaciones.getText().isEmpty()) && !(txtfPrecioCompra.getText().isEmpty()) &&
                !(txtfPrecioVenta.getText().isEmpty()) && !(txtfExistencia.getText().isEmpty())  &&
                    !(txtfCanDefectuoso.getText().isEmpty())){

//                listaProductos.add(new Producto(txtfCodigoBarras.getText(),
//                        txtfNombre.getText(),txtfObservaciones.getText(),
//                        Float.parseFloat(txtfPrecioCompra.getText()),
//                        Float.parseFloat(txtfPrecioVenta.getText()),
//                        Integer.parseInt(txtfExistencia.getText()),
//                        Integer.parseInt(txtfCanDefectuoso.getText())));

            }
        });

    }

}
