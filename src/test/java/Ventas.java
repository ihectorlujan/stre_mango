import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Ventas extends Application {
    //Paneles
    private VBox panelPrincipal;
    private GridPane panelTitulo, panelDatos, panelCliente,panelTable,panelTotal;

    //titulo
    private Label titulo;

    //Datos
    private Label noVenta,fecha,hora,tipoVenta,empleado,noCliente;
    private TextField tnoVenta,tFecha,tHora,tnoCliente;
    private TextField templeado;
    private ChoiceBox ctipoVenta;

    //Cliente
    private Label seleCliente;
    private ChoiceBox cseleCliente;
    private Button buscar,historial;

    //Table
    private TableView tVenta;

    //Total
    private Label total,pagoParcial,efectivo,cambio;
    private TextField tTotal,tPagoParcial,tEfectivo,tCambio;
    private Button aceptar,cancelar;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Instancias
        panelPrincipal = new VBox();
        panelTitulo = new GridPane();
        panelDatos  = new GridPane();
        panelCliente= new GridPane();
        panelTable  = new GridPane();
        panelTotal  = new GridPane();
        tVenta=new TableView();

        //Paneles
        panelTitulo();
        panelDatos();
        panelCliente();
        table();
        panelTotal();

        //Contenedor principal
        VBox.setMargin(panelTitulo, new Insets(0,0,0,0));
        VBox.setMargin(panelDatos, new Insets(10,50,0,30));
        VBox.setMargin(panelCliente, new Insets(10,50,0,30));
        VBox.setMargin(panelTable, new Insets(10,0,0,0));
        VBox.setMargin(panelTotal, new Insets(15,10,0,10));
        VBox.setVgrow(tVenta, Priority.ALWAYS);

        panelPrincipal.setPadding(new Insets(0,0,0,0));
        panelPrincipal.getChildren().addAll(panelTitulo, panelDatos, panelCliente, panelTable, panelTotal);

        primaryStage.setResizable(false);
        Scene scene = new Scene(panelPrincipal,835,583);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/estilos/historial_compras.css").toExternalForm());
        primaryStage.show();
    }


    private void panelTitulo(){
        titulo = new Label("   Ventas");
        panelTitulo = new GridPane();
        ColumnConstraints column1$Titulo = new ColumnConstraints(10,100,USE_COMPUTED_SIZE);
        GridPane.setMargin(titulo, new Insets(10,0,0,0));


        //Estilos
        //panelTitulo.getStyleClass().add("white");
        panelTitulo.add(titulo, 0,0);
        titulo.setPrefSize(180,26);
        titulo.getStyleClass().add("red");
        titulo.getStyleClass().add("labelVentas");
        //panelTitulo.getColumnConstraints().addAll(column1$Titulo);

    }


    private void panelDatos(){
        noVenta  = new Label("No Venta:");
        fecha    = new Label("Fecha:");
        hora     = new Label("Hora:");
        tipoVenta= new Label("Tipo de Venta:");
        empleado = new Label("Empleado");
        noCliente= new Label("No Cleinte");
        tnoVenta  = new TextField();
        tFecha  = new TextField();
        tHora = new TextField();
        tnoCliente = new TextField();
        templeado = new TextField();
        ctipoVenta = new ChoiceBox();

        ColumnConstraints column1$Datos = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column2$Datos = new ColumnConstraints(10,100, Double.MAX_VALUE);


        panelDatos.add(noVenta, 0,0);
        panelDatos.add(fecha,0,1);
        panelDatos.add(hora, 0,2);
        panelDatos.add(tipoVenta, 0,3);
        panelDatos.add(empleado,0,4);
        panelDatos.add(noCliente,0,5);
        panelDatos.add(tnoVenta, 1,0);
        panelDatos.add(tFecha,1,1);
        panelDatos.add(tHora,1,2);
        panelDatos.add(ctipoVenta, 1,3);
        panelDatos.add(templeado, 1,4);
        panelDatos.add(tnoCliente, 1,5);
        ctipoVenta.getItems().addAll("Efectivo", "Credito");
        ctipoVenta.setValue("Efectivo");

        //Estilos
        ctipoVenta.getStyleClass().add("btnRaisedMora");
        panelDatos.getStyleClass().add("white");
        panelDatos.setHgap(0);
        panelDatos.setVgap(5);
        VBox.setMargin(panelDatos, new Insets(10,50,0,30));
        panelDatos.setMargin(tnoVenta, new Insets(0,200,0,0));
        panelDatos.setMargin(tFecha, new Insets(0,200,0,0));
        panelDatos.setMargin(tHora, new Insets(0,200,0,0));
        panelDatos.setMargin(templeado, new Insets(0,200,0,0));
        panelDatos.setMargin(tnoCliente, new Insets(0,200,0,0));
        ctipoVenta.setPrefSize(178,25);



        panelDatos.getColumnConstraints().addAll(column1$Datos, column2$Datos);
        panelDatos.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));
    }

    public void panelCliente(){
        seleCliente = new Label("Seleccionar Cliente");
        cseleCliente = new ChoiceBox();
        buscar = new Button("...");
        historial= new Button("Historial");
        ColumnConstraints column1$Cliente = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column2$Cliente = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column3$Cliente = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column4$Cliente = new ColumnConstraints(10,100, Double.MAX_VALUE);
        cseleCliente.getItems().addAll("Peque", "Yair","Morgan","Ulises");
        cseleCliente.setValue("Peque");

        panelCliente.add(seleCliente,0,0);
        panelCliente.add(cseleCliente,1,0);
        panelCliente.add(buscar, 2,0);
        panelCliente.add(historial, 3,0);

        //Estilos
        cseleCliente.setPrefSize(200,25);
        buscar.setPrefSize(30,25);
        column1$Cliente.setPrefWidth(162);
        column2$Cliente.setPrefWidth(159);
        column3$Cliente.setPrefWidth(106);
        column4$Cliente.setPrefWidth(293);
        buscar.getStyleClass().add("btnRaisedBlue");
        historial.getStyleClass().add("btnRaisedBlue");
        cseleCliente.getStyleClass().add("btnRaisedMora");
        panelCliente.getStyleClass().add("white");
        panelCliente.getColumnConstraints().addAll(column1$Cliente, column2$Cliente,column3$Cliente,column4$Cliente);
        panelCliente.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));


    }


    public void table(){
        tVenta =  new TableView();
        tVenta.setEditable(true);
        TableColumn oneNameCol = new TableColumn("Codigo");
        TableColumn twoNameCol = new TableColumn("Nombre");
        TableColumn threeNameCol = new TableColumn("Cantidad");
        TableColumn fourNameCol = new TableColumn("Valor Unidad");
        TableColumn fiveNameCol = new TableColumn("Descuento");
        TableColumn sixNameCol = new TableColumn("Total");
        TableColumn sevenNameCol = new TableColumn("Pago parcial");
        TableColumn eitghNameCol = new TableColumn("Restante");

        ColumnConstraints column1$table = new ColumnConstraints(10,100, Double.MAX_VALUE);


        tVenta.getColumns().addAll(oneNameCol, twoNameCol, threeNameCol,fourNameCol,fiveNameCol,sixNameCol,sevenNameCol,eitghNameCol);
        panelTable.add(tVenta,0,0);

        //Estilos
        TableColumn [ ] acolumnas = {oneNameCol, twoNameCol, threeNameCol, fourNameCol,fiveNameCol,sixNameCol,sevenNameCol,eitghNameCol};

        for (int i = 0; i<acolumnas.length; i++){
            acolumnas[i].setPrefWidth(102);
            acolumnas[i].getStyleClass().add("btnRaisedMora");}

        tVenta.setPrefSize(816,133);
        panelTable.setMargin(tVenta, new Insets(10,10,0,10));
        panelTable.getColumnConstraints().addAll(column1$table);
        panelTable.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));
    }


    public void panelTotal(){
        total       = new Label("Total");
        pagoParcial = new Label("Pago Parcial");
        efectivo    = new Label("Efectivo");
        cambio      = new Label("Cambio");
        tTotal      = new TextField();
        tPagoParcial= new TextField();
        tEfectivo   = new TextField();
        tCambio     = new TextField();
        aceptar     = new Button("Aceptar");
        cancelar    = new Button("Cancelar");

        ColumnConstraints column1$total = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column2$total = new ColumnConstraints(10,100, Double.MAX_VALUE);
        ColumnConstraints column3$total = new ColumnConstraints(10,100, Double.MAX_VALUE);


        panelTotal.add(total,0,0);
        panelTotal.add(pagoParcial,0,1);
        panelTotal.add(efectivo,0,2);
        panelTotal.add(cambio,0,3);
        panelTotal.add(tTotal,1,0);
        panelTotal.add(tPagoParcial,1,1);
        panelTotal.add(tEfectivo,1,2);
        panelTotal.add(tCambio,1,3);
        panelTotal.add(aceptar,2,0);
        panelTotal.add(cancelar,2,2);

        //Estilos
        panelTotal.setVgap(2);
        panelTotal.setMargin(tTotal, new Insets(0,100,0,0));
        panelTotal.setMargin(tPagoParcial, new Insets(0,100,0,0));
        panelTotal.setMargin(tEfectivo, new Insets(0,100,0,0));
        panelTotal.setMargin(tCambio, new Insets(0,100,0,0));

        aceptar.getStyleClass().add("btnRaisedBoton");
        cancelar.getStyleClass().add("btnRaisedBoton");

        panelTotal.getColumnConstraints().addAll(column1$total,column2$total,column3$total);
        panelTotal.getColumnConstraints().forEach(c -> c.setHgrow(Priority.SOMETIMES));

    }










    public static void main(String [] args){
        launch(args);
    }



}









