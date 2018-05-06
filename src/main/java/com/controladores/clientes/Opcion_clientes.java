package com.controladores.clientes;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.empleado.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class Opcion_clientes extends VBox {
    public Opcion_clientes(){

        var insetsBase = new Insets(10);
        var titulo = new Label("Datos del Cliente.");
        var hBoxTitulo = new HBox();
        var hBoxDomicilio = new HBox();
        var hBoxBusqueda = new HBox();
        var hBoxLista = new HBox();
        var hBoxTabla = new HBox();
        var hBoxSpace = new HBox();
        var hBoxSpace2 = new HBox();
        var hBoxSpace3 = new HBox();
        var hBoxSpace4 = new HBox();
        var gridPane = new GridPane();
        var gridPane2 = new GridPane();
        var lnombre = new Label("Nombre: ");
        var lApellidoP = new Label("Apellido paterno: ");
        var lApellidoM = new Label("Apellido materno: ");
        var lEdad = new Label("Edad: ");
        var lSexoH = new Label("Hombre: ");
        var lSexoM = new Label("Mujer: ");
        var lTelefono = new Label("Telefono: ");
        var lCelular = new Label("Celular: ");
        var lEmail = new Label("Email: ");
        var lDomicilio = new Label("Datos domiciliarios del Cliente.");
        var lCalle = new Label("Calle: ");
        var lNCasa = new Label("Numero de casa: ");
        var lCP = new Label("C.P: ");
        var lEstado = new Label("Estado: ");
        var lBuscar = new Label("Buscar: ");
        var lId = new Label("ID: ");
        var lIdContainer = new Label("13");
        var lLista = new Label("Lista de empleados.");
        var tNombre = new TextField();
        var tApellidoP = new TextField();
        var tApellidoM = new TextField();
        var tTelefono = new TextField();
        var tCelular = new TextField();
        var tEmail = new TextField();
        var tCalle = new TextField();
        var tNcasa = new TextField();
        var tCP = new TextField();
        var tEstado = new TextField();
        var tBuscar = new TextField();
        var cboxEdad = new ComboBox<Integer>();
        var rbSexoH = new RadioButton();
        var rbSexoM = new RadioButton();   //Group - empleado femenino
        var btnMas = new JFXButton();
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
        var table = createTable(listEmpleados);

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

    private JFXTreeTableView createTable(ObservableList<Empleado> list) {
        final TreeItem<Empleado> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        var tableView = new JFXTreeTableView<>(root);

        var clmNombre = new JFXTreeTableColumn<Empleado, String>("Nombre");
        var clmApellido = new JFXTreeTableColumn<Empleado, String>("Apellido paterno");
        var clmTelefono = new JFXTreeTableColumn<Empleado, String>("Telefono materno");
        var clmCorreo = new JFXTreeTableColumn<Empleado, String>("Direccion");

        clmNombre.setResizable(false);
        clmNombre.setPrefWidth(195);
        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmApellido.setResizable(false);
        clmApellido.setPrefWidth(195);
        clmApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmApellido.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmApellido.getComputedValue(param);
        });

        clmTelefono.setResizable(false);
        clmTelefono.setPrefWidth(180);
        clmTelefono.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmTelefono.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmTelefono.getComputedValue(param);
        });

        clmCorreo.setResizable(false);
        clmCorreo.setPrefWidth(190);
        clmCorreo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmCorreo.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmCorreo.getComputedValue(param);
        });

        tableView.setShowRoot(false);
        tableView.setEditable(false);
        tableView.getColumns().setAll(clmNombre, clmApellido, clmTelefono, clmCorreo);

        return tableView;
    }
}
