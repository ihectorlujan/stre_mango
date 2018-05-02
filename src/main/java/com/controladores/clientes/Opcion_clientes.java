package com.controladores.clientes;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.modelo.empleado.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class Opcion_clientes extends VBox {
    public Opcion_clientes(){

        var insetsBase = new Insets(10);
        var titulo = new Label("Datos del cliente.");
        var hBoxTitulo = new HBox();
        var hBoxDomicilio = new HBox();
        var hBoxBuscar = new HBox();
        var hBoxTabla = new HBox();
        var hBoxSeparacion = new HBox();
        var hBoxSeparacion2 = new HBox();
        var gridPane = new GridPane();
        var gridPane2 = new GridPane();
        var lnombre = new Label("Nombre: ");
        var lApellidoP = new Label("Apellido paterno: ");
        var lApellidoM = new Label("Apellido materno: ");
        var lEdad = new Label("Edad: ");
        var lSexoM = new Label("Masculino: ");
        var lSexoF = new Label("Femenino: ");
        var lTelefono = new Label("Telefono: ");
        var lCelular = new Label("Celular: ");
        var lEmail = new Label("Email: ");
        var lDomicilio = new Label("Datos domiciliarios del cliente.");
        var lCalle = new Label("Calle: ");
        var lColonia = new Label("Colonia: ");
        var lMunicipio = new Label("Municipio: ");
        var lEstado = new Label("Estado: ");
        var lBuscar = new Label("Buscar: ");
        var tNombre = new TextField();
        var tApellidoP = new TextField();
        var tApellidoM = new TextField();
        var tTelefono = new TextField();
        var tCelular = new TextField();
        var tEmail = new TextField();
        var tCalle = new TextField();
        var tColonia = new TextField();
        var tMunicipio = new TextField();
        var tEstado = new TextField();
        var tBuscar = new TextField();
        var cboxEdad = new ComboBox<Integer>();
        var rbSexoM = new RadioButton();
        var rbSexoF = new RadioButton();   //Group - empleado femenino
        cboxEdad.getItems().addAll(18,19,20,21,22,23,24);
        ObservableList<Empleado> listEmpleados = FXCollections.observableArrayList();

        // Barra de titulo.
        hBoxTitulo.getChildren().add(titulo);
        hBoxTitulo.setPadding(new Insets(10,10,10,10));
        hBoxTitulo.getStyleClass().add("panelWhite");

        // Recuadro de informacion.
        var columna1 = new ColumnConstraints();
        var columna2 = new ColumnConstraints();
        var columna3 = new ColumnConstraints();
        var columna4 = new ColumnConstraints();

        // Datos del GridPane.
        gridPane.add(lnombre,0,0);
        gridPane.add(tNombre,1,0);
        gridPane.add(lApellidoP,2,0);
        gridPane.add(tApellidoP,3,0);

        gridPane.add(lApellidoM,0,1);
        gridPane.add(tApellidoM,1,1);
        gridPane.add(hBoxSeparacion,2,1);
        gridPane.add(hBoxSeparacion2,3,1);
        hBoxSeparacion.getChildren().addAll(lEdad,cboxEdad);
        lEdad.setPadding(new Insets(0,10,0,0));
        hBoxSeparacion2.getChildren().addAll(lSexoM, rbSexoM, lSexoF, rbSexoF);
        rbSexoM.setPadding(new Insets(0,10,0,0));

        gridPane.add(lTelefono,0,2);
        gridPane.add(tTelefono,1,2);
        gridPane.add(lCelular,2,2);
        gridPane.add(tCelular,3,2);

        gridPane.add(lEmail,0,3);
        gridPane.add(tEmail,1,3);

        // Barra de datos domiciliarios.
        hBoxDomicilio.getChildren().add(lDomicilio);
        hBoxDomicilio.setPadding(new Insets(10,10,10,10));
        hBoxDomicilio.getStyleClass().add("panelWhite");

        // Segundo recuadro de informacion.
        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();
        var column3 = new ColumnConstraints();
        var column4 = new ColumnConstraints();

        // Datos del GridPane2.
        gridPane2.add(lCalle,0,0);
        gridPane2.add(tCalle,1,0);
        gridPane2.add(lColonia,2,0);
        gridPane2.add(tColonia,3,0);

        gridPane2.add(lMunicipio,0,1);
        gridPane2.add(tMunicipio,1,1);
        gridPane2.add(lEstado,2,1);
        gridPane2.add(tEstado,3,1);

        // Agrega las columnas al GridPane.
        gridPane.getColumnConstraints().addAll(columna1, columna2, columna3, columna4);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPane2.getColumnConstraints().addAll(column1, column2, column3, column4);
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

        // Barra de busqueda.
        hBoxBuscar.getChildren().addAll(lBuscar, tBuscar);
        lBuscar.setPadding(new Insets(0,10,0,0));
        hBoxBuscar.setPadding(new Insets(10,10,10,10));
        hBoxBuscar.getStyleClass().add("panelWhite");

        // Tabla de clientes.
        var table = createTable(listEmpleados);

        // Agrega los nodos () al VBox.
        getChildren().addAll(hBoxTitulo, gridPane, hBoxDomicilio, gridPane2, hBoxBuscar, table);

        setPadding(insetsBase);
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
