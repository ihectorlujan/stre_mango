package com.controladores.empleados;

import com.jfoenix.controls.*;
import com.modelo.Conexion;
import com.modelo.cod_postal.CodigoPostal;
import com.modelo.empleado.Empleado;
import com.validators.Messages;
import com.validators.EntryValidator;
import com.validators.ValidateFields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;

public class ventanaEmpleado extends Stage {
    private Conexion conexion = new Conexion();
    private ObservableList<CodigoPostal> list = FXCollections.observableArrayList();
    private EntryValidator handler = new EntryValidator();
    private ValidateFields validateFields = new ValidateFields();

    private HBox hBoxTitulo = new HBox();
    private HBox hBoxCodigoPostal = new HBox();
    private HBox hBoxSexo = new HBox();
    private HBox hBoxEdad = new HBox();
    private GridPane gridPane = new GridPane();
    private GridPane gridPaneTelefono = new GridPane();
    private GridPane gridPaneDomicilio = new GridPane();

    private Label lblNombre = new Label("Nombre:*");
    private Label lblApellidoPaterno = new Label("Primer apellido:*");
    private Label lblApellidoMaterno = new Label("Segundo apellido:");
    private Label lblEdad = new Label("Edad:*");
    private Label lblSexo = new Label("Sexo:*");
    private Label lblTelefono = new Label("Telefono:*");
    private Label lblCorreo = new Label("Correo:*");
    private Label lblDatosDomiciliarios = new Label("Datos domiciliarios");
    private Label lblAgregar = new Label("Datos del empleado");
    private Label lblCalle = new Label("Calle:*");
    private Label lblNoCasa = new Label("No de casa:*");
    private Label lblCodPostal = new Label("C.P:*");

    private TextField txtNombre = new TextField();
    private TextField txtPrimerApellido = new TextField();
    private TextField txtSegundoApellido = new TextField();
    private TextField txtTelefono = new TextField();
    private TextField txtTelefono1 = new TextField();
    private TextField txtTelefono2 = new TextField();
    private TextField txtCorreo = new TextField();
    private TextField txtCalle = new TextField();
    private TextField txtNoCasa = new TextField();
    private TextField txtCodigoPostal = new TextField();

    private JFXRadioButton rbHombre = new JFXRadioButton("Hombre");
    private JFXRadioButton rbMujer = new JFXRadioButton("Mujer");
    private JFXComboBox<Integer> cmbEdad = new JFXComboBox<>();

    private ColumnConstraints column1 = new ColumnConstraints();
    private ColumnConstraints column2 = new ColumnConstraints();

    private Messages messages = new Messages();

    public ventanaEmpleado() {
        var panePrincipal = new VBox();

        //Llenar comboBox
        llenarComboEdad(cmbEdad);

        //Toggle group
        var toggleGroup = new ToggleGroup();
        rbHombre.setToggleGroup(toggleGroup);
        rbMujer.setToggleGroup(toggleGroup);

        //Containers
        gridPaneDatosGenerales();
        gridPaneDomicilio();
        gridPaneTelefono();
        hBoxContainers();

        loadPostalCodes();
        verificacionTelefono();

        JFXButton btnAgregar = new JFXButton("Aceptar");

        //Add styles
        btnAgregar.getStyleClass().add("btnRaisedBlue");
        gridPane.getStyleClass().add("white");

        //HBox de datos domiciliarios
        HBox hBoxDatosDomiciliarios = new HBox();
        hBoxDatosDomiciliarios.getChildren().add(lblDatosDomiciliarios);
        hBoxDatosDomiciliarios.setPadding(new Insets(5));
        hBoxDatosDomiciliarios.getStyleClass().add("white");

        //Hboxes de ayuda
        hBoxTitulo.getChildren().add(lblAgregar);
        hBoxTitulo.setPadding(new Insets(5));
        HBox hBoxBntAgregar = new HBox();
        hBoxBntAgregar.getChildren().add(btnAgregar);
        hBoxBntAgregar.setAlignment(Pos.CENTER_RIGHT);

        //Accion del boton
        btnAgregar.setOnAction(e -> {
            var idCodigo = validateFields.idCpDatabase(txtCodigoPostal.getText());
            if (verificarCampos() && idCodigo > 0) {
                agregarEmpleado(new Empleado(txtNombre.getText(),
                        txtPrimerApellido.getText(),
                        txtSegundoApellido.getText().equals("") ? "" : txtSegundoApellido.getText(),
                        Integer.parseInt(cmbEdad.getValue().toString()),
                        rbHombre.isSelected() ? "hombre" : "mujer",
                        txtTelefono.getText() + " " + txtTelefono1.getText() + " " + txtTelefono2.getText(),
                        txtCorreo.getText(),
                        txtCalle.getText(),
                        txtNoCasa.getText()), idCodigo);
            }else {
                messages.setMessageAlert(this, "Verifique lo siguiente:", "" +
                        "\t- Los campos con * son obligatorios\n" +
                        "\t- El correo tenga el formato correcto\n" +
                        "\t- El telefono contenga 10 digitos\n" +
                        "\t- El formato del codigo postal\n");
            }

        });

        //Add childs to pane Principal
        panePrincipal.getChildren().addAll(hBoxTitulo, gridPane, hBoxDatosDomiciliarios, gridPaneDomicilio, hBoxCodigoPostal, hBoxBntAgregar);

        //Properties of panePrincipal
        panePrincipal.setPadding(new Insets(10));
        VBox.setMargin(hBoxTitulo, new Insets(0,0,5,0));
        VBox.setMargin(gridPane, new Insets(0,0,5,0));
        VBox.setMargin(hBoxDatosDomiciliarios, new Insets(0,0,5,0));
        VBox.setMargin(hBoxCodigoPostal, new Insets(0,0,10,0));

        //Properties of stage
        setResizable(false);
        Scene scene = new Scene(panePrincipal, 440,470);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/agregar_empleado.css").toExternalForm());
        show();
    }

    public ventanaEmpleado(Empleado empleado) {
        var panePrincipal = new VBox();

        //Establecer los campos
        txtNombre.setText(empleado.getNombre());
        txtPrimerApellido.setText(empleado.getPrimerApellido());
        txtSegundoApellido.setText(empleado.getSegundoApellido());
        cmbEdad.setValue(empleado.getEdad());
        if (empleado.getSexo().equals("hombre"))
            rbHombre.setSelected(true);
        else
            rbMujer.setSelected(true);
        var telefono = empleado.getTelefono().split(" ");
        txtTelefono.setText(telefono[0]);
        txtTelefono1.setText(telefono[1]);
        txtTelefono2.setText(telefono[2]);
        txtCorreo.setText(empleado.getCorreo());
        txtCalle.setText(empleado.getNombreCalle());
        txtNoCasa.setText(empleado.getnCasa());
        txtCodigoPostal.setText(empleado.getCodigoPostal() + " " + empleado.getAsentamiento());

        //Llenar comboBox
        llenarComboEdad(cmbEdad);

        //Toggle group
        var toggleGroup = new ToggleGroup();
        rbHombre.setToggleGroup(toggleGroup);
        rbMujer.setToggleGroup(toggleGroup);

        //Containers
        gridPaneDatosGenerales();
        gridPaneDomicilio();
        gridPaneTelefono();
        hBoxContainers();

        loadPostalCodes();
        verificacionTelefono();

        JFXButton btnAgregar = new JFXButton("Aceptar");

        //Add styles
        btnAgregar.getStyleClass().add("btnRaisedBlue");
        gridPane.getStyleClass().add("white");

        //HBox de datos domiciliarios
        HBox hBoxDatosDomiciliarios = new HBox();
        hBoxDatosDomiciliarios.getChildren().add(lblDatosDomiciliarios);
        hBoxDatosDomiciliarios.setPadding(new Insets(5));
        hBoxDatosDomiciliarios.getStyleClass().add("white");

        //Hboxes de ayuda
        hBoxTitulo.getChildren().add(lblAgregar);
        hBoxTitulo.setPadding(new Insets(5));
        HBox hBoxBntAgregar = new HBox();
        hBoxBntAgregar.getChildren().add(btnAgregar);

        hBoxBntAgregar.setAlignment(Pos.CENTER_RIGHT);

        //Accion del boton
        btnAgregar.setOnAction(e -> {
            var idCodigo = validateFields.idCpDatabase(txtCodigoPostal.getText());
            if (verificarCampos() && idCodigo > 0) {
                updateEmpleado(new Empleado(empleado.getId(),
                        txtNombre.getText(),
                        txtPrimerApellido.getText(),
                        txtSegundoApellido.getText().equals("")? "" : txtSegundoApellido.getText(),
                        Integer.parseInt(cmbEdad.getValue().toString()),
                        rbHombre.isSelected() ? "hombre" : "mujer",
                        txtTelefono.getText() + " " + txtTelefono1.getText() + " " + txtTelefono2.getText(),
                        txtCorreo.getText(),
                        txtCalle.getText(),
                        txtNoCasa.getText()), idCodigo);
            }else {
                messages.setMessageAlert(this, "Verifique lo siguiente:", "" +
                        "\t- Los campos con * son obligatorios\n" +
                        "\t- El correo tenga el formato correcto\n" +
                        "\t- El telefono contenga 10 digitos\n" +
                        "\t- El formato del codigo postal\n");
            }
        });


        //Add childs to pane Principal
        panePrincipal.getChildren().addAll(hBoxTitulo, gridPane, hBoxDatosDomiciliarios, gridPaneDomicilio, hBoxCodigoPostal, hBoxBntAgregar);

        //Properties of panePrincipal
        panePrincipal.setPadding(new Insets(10));
        VBox.setMargin(hBoxTitulo, new Insets(0,0,5,0));
        VBox.setMargin(gridPane, new Insets(0,0,5,0));
        VBox.setMargin(hBoxDatosDomiciliarios, new Insets(0,0,5,0));
        VBox.setMargin(hBoxCodigoPostal, new Insets(0,0,10,0));

        //Properties of stage
        setResizable(false);
        Scene scene = new Scene(panePrincipal, 440,470);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        scene.getStylesheets().add(getClass().getResource("/estilos/agregar_empleado.css").toExternalForm());
        show();
    }

    private void verificacionTelefono() {
        txtNombre.addEventFilter(KeyEvent.ANY, handler.onlyLetters());
        txtPrimerApellido.addEventFilter(KeyEvent.ANY, handler.onlyLetters());
        txtSegundoApellido.addEventFilter(KeyEvent.ANY, handler.onlyLetters());
        txtTelefono.addEventFilter(KeyEvent.ANY, handler.onlyNumbers());
        txtTelefono1.addEventFilter(KeyEvent.ANY, handler.onlyNumbers());
        txtTelefono2.addEventFilter(KeyEvent.ANY, handler.onlyNumbers());

        txtTelefono.addEventHandler(KeyEvent.ANY, e -> {
            if(txtTelefono.getText().length() > 2) {
                txtTelefono.getStyleClass().add("verificado");
                txtTelefono.setFocusTraversable(true);
                txtTelefono1.requestFocus();
            }
        });

        txtTelefono1.addEventHandler(KeyEvent.ANY, e -> {
            if(txtTelefono1.getText().length() > 2) {
                txtTelefono1.getStyleClass().add("verificado");
                txtTelefono1.setFocusTraversable(true);
                txtTelefono2.requestFocus();
            }
        });

        txtTelefono2.addEventHandler(KeyEvent.ANY, e -> {
            if(txtTelefono2.getText().length() > 3) {
                txtTelefono2.getStyleClass().add("verificado");
                txtTelefono2.setFocusTraversable(true);
                txtCorreo.requestFocus();
            }
        });

    }

    private boolean verificarCampos() {
        var verificarEdad = false;
        var verificarCamposVacios = validateFields.validateFields(txtNombre, txtPrimerApellido, txtTelefono, txtTelefono1, txtTelefono2, txtCalle, txtNoCasa, txtCorreo);
        var verificarSexo = rbHombre.isSelected() || rbMujer.isSelected();
        var verificarCorreo = validateFields.validateEmail(txtCorreo.getText());

        try { verificarEdad = cmbEdad.getValue() > 16; } catch (Exception ignored){};

        return verificarCamposVacios && verificarEdad && verificarSexo && verificarCorreo;
    }

    private void loadPostalCodes() {
        conexion.establecerConexion();
        CodigoPostal.getCodigosPostales(conexion.getConection(), list);
        conexion.cerrarConexion();
        TextFields.bindAutoCompletion(txtCodigoPostal, list);
    }

    private void hBoxContainers() {
        //HBox Sexo
        hBoxSexo.getChildren().addAll(lblSexo,rbHombre, rbMujer);
        hBoxSexo.setPadding(new Insets(5,0,0,0));
        HBox.setMargin(lblSexo, new Insets(0,10,0,0));
        HBox.setMargin(rbHombre, new Insets(0,10,0,0));

        //HBox Edad
        hBoxEdad.getChildren().addAll(lblEdad, cmbEdad);
        hBoxEdad.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(lblEdad, new Insets(0,10,0,0));

        //HBox codigo postal
        hBoxCodigoPostal.getChildren().addAll(lblCodPostal, txtCodigoPostal);
        hBoxCodigoPostal.getStyleClass().add("white");
        hBoxCodigoPostal.setAlignment(Pos.CENTER);
        hBoxCodigoPostal.setPadding(new Insets(0,10,5,10));
        HBox.setHgrow(txtCodigoPostal, Priority.ALWAYS);
        HBox.setMargin(lblCodPostal, new Insets(0,10,0,0));

        //Estilos de los diversos Box
        hBoxTitulo.getStyleClass().add("white");
    }

    private void gridPaneDomicilio() {
        HBox hBoxNoCasa = new HBox();
        hBoxNoCasa.setAlignment(Pos.CENTER_LEFT);
        hBoxNoCasa.getChildren().add(txtNoCasa);
        txtNoCasa.setPrefWidth(60);

        gridPaneDomicilio.add(lblCalle, 0,0);
        gridPaneDomicilio.add(lblNoCasa, 0,1);

        gridPaneDomicilio.add(txtCalle,1,0);
        gridPaneDomicilio.add(hBoxNoCasa,1,1);

        gridPaneDomicilio.getColumnConstraints().addAll(column1, column2);
        gridPaneDomicilio.setPadding(new Insets(10));
        gridPaneDomicilio.getStyleClass().add("white");
        gridPaneDomicilio.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPaneDomicilio.setHgap(20);
        gridPaneDomicilio.setVgap(5);
    }

    private void gridPaneDatosGenerales() {
        //Column 1
        gridPane.add(lblNombre,0,0);
        gridPane.add(lblApellidoPaterno,0,1);
        gridPane.add(lblApellidoMaterno,0,2);
        gridPane.add(hBoxEdad, 0,3);
        gridPane.add(lblTelefono,0,4);
        gridPane.add(lblCorreo,0,5);

        //Column 2
        gridPane.add(txtNombre,1,0);
        gridPane.add(txtPrimerApellido,1,1);
        gridPane.add(txtSegundoApellido,1,2);
        gridPane.add(hBoxSexo,1,3);
        gridPane.add(gridPaneTelefono,1,4);
        gridPane.add(txtCorreo,1,5);

        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(5);
    }

    private void gridPaneTelefono() {
        txtTelefono.setPrefWidth(40);
        txtTelefono1.setPrefWidth(40);
        txtTelefono2.setPrefWidth(50);

        gridPaneTelefono.add(txtTelefono,0,0);
        gridPaneTelefono.add(txtTelefono1,1,0);
        gridPaneTelefono.add(txtTelefono2,2,0);

        gridPaneTelefono.getColumnConstraints().addAll(column1, column2, new ColumnConstraints());
        gridPaneTelefono.getColumnConstraints().forEach(x -> x.setHgrow(Priority.SOMETIMES));
        gridPaneTelefono.setHgap(5);
    }

    private void llenarComboEdad(JFXComboBox<Integer> combo) {
        for (int i = 16; i < 60; i++)
            combo.getItems().add(i);
    }

    private void agregarEmpleado(Empleado x, int idCodigoPostal) {

        conexion.establecerConexion();
        var newEmpleado  = Empleado.addEmpleado(conexion.getConection(), x,idCodigoPostal);
        conexion.cerrarConexion();

        if (newEmpleado != null) {
            messages.setMessage("Empleado agregado", "El empleado se agrego satisfactoriamente", NotificationType.SUCCESS);
            OpcionEmpleado.listEmpleados.add(newEmpleado);
            this.close();
        }

        else
            messages.setMessage("Hubo un error","El empleado no se agrego", NotificationType.ERROR);
    }

    private void updateEmpleado(Empleado x, int idCodigoPostal) {

        conexion.establecerConexion();
        var success  = Empleado.updateEmpleado(conexion.getConection(), x, idCodigoPostal);
        conexion.cerrarConexion();

        if (success != null) {
            OpcionEmpleado.listEmpleados.removeIf(l -> l.getId() == success.getId());
            OpcionEmpleado.listEmpleados.add(success);
            OpcionEmpleado.table.getSelectionModel().getSelectedIndex();
            messages.setMessage("Empleado editado", "El empleado se edito satisfactoriamente", NotificationType.SUCCESS);
            this.close();
        }else
           messages.setMessage("Algo salio mal","El empleado no se edito correctamente", NotificationType.ERROR);
    }

}
