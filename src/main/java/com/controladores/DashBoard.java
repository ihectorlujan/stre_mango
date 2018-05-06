package com.controladores;

import com.controladores.clientes.Opcion_clientes;
import com.controladores.compras.OpcionCompra;
import com.controladores.empleados.OpcionEmpleado;
//import com.controladores.productos.OpcionProducto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DashBoard {
    //Panel principal, panel central
    private BorderPane borderPanePrincipal = new BorderPane();
    private VBox centerBox = new VBox();

    //Iconos
    private Text ico;
    private Text ico1;

    DashBoard() {
        var topBox = new HBox();
        topBox.getChildren().addAll(primerPanelTop(), segundoPanelTop());

        //Left Box
        VBox leftBox = new VBox();
        leftBox.getStyleClass().add("panelWhite");
        leftBox.setPrefSize(190, Region.USE_COMPUTED_SIZE);
        leftBox.setPadding(new Insets(40,0,0,0));

        //Opciones del menu, son HBox
        var opcionHome = opcionMenu(FontAwesomeIcon.CIRCLE_ALT_NOTCH, "Menu", 0);
        var opcionCompras = opcionMenu(FontAwesomeIcon.SHOPPING_CART, "Compras", 10);
        var opcionVentas = opcionMenu(FontAwesomeIcon.MONEY, "Ventas", 10);
        var opcionUsuarios = opcionMenu(FontAwesomeIcon.USERS, "Clientes", 10);
        var opcionProveedores = opcionMenu(FontAwesomeIcon.USER_SECRET, "Proveedores", 10);
        var opcionEmpleados = opcionMenu(FontAwesomeIcon.MALE, "Empleados", 10);
        var opcionProductos = opcionMenu(FontAwesomeIcon.PRODUCT_HUNT, "Productos", 10);
        var opcionDevolucion = opcionMenu(FontAwesomeIcon.EXCHANGE, "Devoluciones",10);

        var masOpciones = new VBox();
        masOpciones.setAlignment(Pos.CENTER);
        VBox.setVgrow(masOpciones, Priority.ALWAYS);

        //Opciones del menu, parte inferior
        var ajustes = opcionMenu(FontAwesomeIcon.COG, "Ajustes", 0);
        var acerca = opcionMenu(FontAwesomeIcon.QUESTION_CIRCLE, "Acerca", 0);

        masOpciones.getChildren().addAll(ajustes,acerca);
        leftBox.getChildren().addAll(opcionHome, opcionCompras, opcionVentas, opcionUsuarios, opcionProveedores, opcionEmpleados, opcionProductos, opcionDevolucion, masOpciones);

        //Center Box
        centerBox.setPadding(new Insets(20,20,20,20));
        HBox primerBox = primerPanelCenter();

        HBox eventosRecientes = new HBox();
        VBox eventosRecientesH = new VBox();
        VBox.setMargin(eventosRecientes, new Insets(0,20,20,20));

        //Presentacion en cuadrados
        var ultimaCompra = actividadesRecientes(FontAwesomeIcon.SHOPPING_CART, "Ultima compra", "22/01/2018");
        var ultimaVenta = actividadesRecientes(FontAwesomeIcon.MONEY, "Ultima venta", "22/01/2018");
        var ultimoUsuario = actividadesRecientes(FontAwesomeIcon.USER, "Ultimo usuario", "22/01/2018");

        HBox.setHgrow(ultimaCompra, Priority.ALWAYS);
        HBox.setMargin(ultimaCompra, new Insets(20,20,20,20));
        HBox.setHgrow(ultimaVenta, Priority.ALWAYS);
        HBox.setMargin(ultimaVenta, new Insets(20,20,20,20));
        HBox.setHgrow(ultimoUsuario, Priority.ALWAYS);
        HBox.setMargin(ultimoUsuario, new Insets(20,20,20,20));
        eventosRecientes.getChildren().addAll(ultimaCompra, ultimaVenta, ultimoUsuario);

        //Presentacion en Lista
        var ultimaCompraH = actividadesRecientesH(FontAwesomeIcon.SHOPPING_CART, "Ultima Compra", "22/10/2017");
        var ultimaVentaH = actividadesRecientesH(FontAwesomeIcon.MONEY, "Ultima venta", "22/01/2018");
        var ultimoUsuarioH = actividadesRecientesH(FontAwesomeIcon.USER, "Ultimo usuario", "22/01/2018");
        eventosRecientesH.getChildren().addAll(ultimaCompraH, ultimaVentaH, ultimoUsuarioH);

        //Graficas
        var graficas = new HBox();
        var mejoresCompradores = graficasRecientes("Marzo", "Mejores compradores");
        var mejoresVentas = graficasRecientes("Marzo", "Mejores ventas");

        HBox.setMargin(mejoresCompradores, new Insets(20,20,20,20));
        HBox.setHgrow(mejoresCompradores, Priority.ALWAYS);
        HBox.setMargin(mejoresVentas, new Insets(20,20,20,20));
        HBox.setHgrow(mejoresVentas, Priority.ALWAYS);
        graficas.getChildren().addAll(mejoresCompradores, mejoresVentas);

        //Agregar nodos al center box
        centerBox.getChildren().addAll(primerBox, eventosRecientes, graficas);

        //Eventos
        opcionHome.setOnMouseClicked(a -> {
            if(a.getButton() == MouseButton.PRIMARY)
                borderPanePrincipal.setCenter(centerBox);
        });

        opcionProductos.setOnMouseClicked(a -> {
//            if(a.getButton()==MouseButton.PRIMARY)
//                borderPanePrincipal.setCenter(new OpcionProducto());
        });

        opcionUsuarios.setOnMouseClicked(a -> {
            if(a.getButton()==MouseButton.PRIMARY)
                borderPanePrincipal.setCenter(new Opcion_clientes());
        });

        opcionEmpleados.setOnMouseClicked(a -> {
            if(a.getButton()==MouseButton.PRIMARY)
                borderPanePrincipal.setCenter(new OpcionEmpleado());
        });

        opcionCompras.setOnMouseClicked(a -> {
            if(a.getButton() == MouseButton.PRIMARY)
                borderPanePrincipal.setCenter(new OpcionCompra());
        });

        opcionProveedores.setOnMouseClicked(a ->{
            if(a.getButton()==MouseButton.PRIMARY){}
                //borderPanePrincipal.setCenter(new OpcionProveedores());
        });

        opcionProductos.setOnMouseClicked(a ->{
//            if(a.getButton() == MouseButton.PRIMARY)
//                borderPanePrincipal.setCenter(new OpcionProducto());
        });

        acerca.setOnMouseClicked(a -> {
            if (a.getButton() == MouseButton.PRIMARY)
                new Acerca();
        });

        //Eventos de icono
        ico.setOnMouseClicked(e -> {
            try {
                centerBox.getChildren().remove(eventosRecientes);
                centerBox.getChildren().add(1, eventosRecientesH);
            }catch (Exception ignored) {}
        });

        ico1.setOnMouseClicked(e -> {
            try {
                centerBox.getChildren().remove(eventosRecientesH);
                centerBox.getChildren().add(1, eventosRecientes);
            }catch(Exception ignored) {}
        });

        borderPanePrincipal.setTop(topBox);
        borderPanePrincipal.setLeft(leftBox);
        borderPanePrincipal.setCenter(centerBox);

        var stage = new Stage();
        var scene = new Scene(borderPanePrincipal, 970, 600);
        scene.getStylesheets().add(getClass().getResource("/estilos/dashboard.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Menu principal");
        stage.show();
    }

    private HBox primerPanelTop() {
        var box = new HBox();
        var textChino = new Text("StreaMango");
        var ico = GlyphsDude.createIcon(FontAwesomeIcon.FIRE, "32");

        box.setPadding(new Insets(20,20,20,20));
        box.setPrefSize(190, Region.USE_COMPUTED_SIZE);
        box.setMaxWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(box, Priority.NEVER);

        //Estilos
        textChino.getStyleClass().add("textLogo");
        ico.getStyleClass().add("icoPrincipal");
        box.getStyleClass().add("panel1");

        HBox.setMargin(textChino, new Insets(0,0,0,20));

        box.getChildren().addAll(ico, textChino);
        box.setAlignment(Pos.CENTER);

        return box;
    }

    private HBox segundoPanelTop() {
        var box = new HBox();
        var userInfo = new HBox();

        // Primer box, variables no usadas
        var txtSearch = new JFXTextField();
        var ico = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH, "18");

        //Segundo Box
        var icoBell = GlyphsDude.createIcon(FontAwesomeIcon.BELL_ALT, "18");
        var user = new ImageView("/imagenes/riki.jpg");
        var icoDown = GlyphsDude.createIcon(FontAwesomeIcon.CHEVRON_DOWN, "16");
        var txtUser = new Text("Riki");

        user.setFitWidth(32);
        user.setFitHeight(32);
        userInfo.getChildren().addAll(icoBell, user, txtUser, icoDown);
        txtUser.getStyleClass().add("text1");
        HBox.setMargin(user, new Insets(0,20,0,20));
        HBox.setMargin(icoDown, new Insets(0,10,0,5));
        HBox.setHgrow(userInfo, Priority.ALWAYS);

        userInfo.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(box, Priority.ALWAYS);
        box.getStyleClass().add("panelWhite");
        box.getChildren().addAll(userInfo);

        return box;
    }

    private HBox opcionMenu(FontAwesomeIcon ico, String txt, int marginTop) {
        var box = new HBox();
        var icoMenu = GlyphsDude.createIcon(ico, "1.5em");
        var text = new Text(txt);

        icoMenu.setWrappingWidth(20);
        HBox.setMargin(box, new Insets(marginTop, 0, 0, 0));
        HBox.setMargin(text, new Insets(0,0,0,10));
        HBox.setMargin(icoMenu, new Insets(0,0,0,10));

        //Estilos
        text.getStyleClass().add("text1");
        icoMenu.getStyleClass().add("iconosMenu");
        box.setStyle("-fx-background-color: white;");
        box.getStyleClass().add("hover_border");

        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10,0,10,0));
        box.getChildren().addAll(icoMenu, text);

        return box;
    }

    private HBox primerPanelCenter() {
        var box = new HBox();
        var text = new Text("Chino Jr");
        var internalBox = new HBox();
        var button = new JFXButton("Estadisticas");

        //Internal Box
        ico = GlyphsDude.createIcon(FontAwesomeIcon.TH_LIST);
        ico1 = GlyphsDude.createIcon(FontAwesomeIcon.TH_LARGE);
        ico.getStyleClass().add("iconosMenu");
        ico1.getStyleClass().add("iconosMenu");
        HBox.setMargin(ico, new Insets(0,10,0,0));

        //Estilos
        button.getStyleClass().add("btnEstadistica");
        text.getStyleClass().add("textChino");

        internalBox.getChildren().addAll(ico, ico1);
        internalBox.setPadding(new Insets(5,5,5,5));
        internalBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setMargin(button, new Insets(0,0,0,10));
        HBox.setHgrow(internalBox, Priority.ALWAYS);
        box.getChildren().addAll(text, internalBox, button);

        return box;
    }

    private HBox actividadesRecientesH(FontAwesomeIcon icon, String text, String date) {
        var hBox = new HBox();
        var icoFecha = GlyphsDude.createIcon(FontAwesomeIcon.CALENDAR, "1.5em");
        var fecha = new Text(date);
        var ico = GlyphsDude.createIcon(icon, "1.5em");
        var textIco = new Text(text);

        //Estilos
        fecha.getStyleClass().add("text1");
        textIco.getStyleClass().add("text1");
        ico.getStyleClass().add("iconosMenu");
        icoFecha.getStyleClass().add("iconosMenu");
        hBox.getStyleClass().addAll("panelWhite", "tarMenu");

        icoFecha.setWrappingWidth(30);
        fecha.setWrappingWidth(150);
        textIco.setWrappingWidth(120);
        ico.setWrappingWidth(20);

        VBox.setMargin(hBox, new Insets(10,50,10,50));

        hBox.setPrefSize(200,100);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(icoFecha, fecha, textIco, ico);

        return hBox;
    }

    private VBox actividadesRecientes(FontAwesomeIcon icon, String text, String date) {
        var vBox = new VBox();
        var internalBox = new HBox();
        var icono = new HBox();

        var icoFecha = GlyphsDude.createIcon(FontAwesomeIcon.CALENDAR);
        var fecha = new Text(date);
        var ico = GlyphsDude.createIcon(icon);
        var textIco = new Text(text);

        //Estilos
        fecha.getStyleClass().add("text1");
        textIco.getStyleClass().add("text1");
        ico.getStyleClass().add("iconosGrande");
        icoFecha.getStyleClass().add("iconosMenu");

        VBox.setMargin(ico, new Insets(10,0,10,0));
        vBox.setPadding(new Insets(0,20,10,20));
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(100,200);
        vBox.getStyleClass().add("tarMenu");
        vBox.getChildren().addAll(internalBox, ico, textIco);
        internalBox.getChildren().addAll(fecha, icono);

        icono.setAlignment(Pos.CENTER_RIGHT);
        icono.setPrefSize(200, Region.USE_COMPUTED_SIZE);
        icono.getChildren().add(icoFecha);
        return vBox;
    }

    private VBox graficasRecientes(String mes, String txt) {
        var vBox = new VBox();
        var internalBox = new HBox();
        var icono = new HBox();
        var icoFecha = GlyphsDude.createIcon(FontAwesomeIcon.CALENDAR);
        var fecha = new Text(mes);
        var textIco = new Text(txt);

        //Grafica
        var xAxis = new CategoryAxis();
        var yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

        //Estilos
        fecha.getStyleClass().add("text1");
        textIco.getStyleClass().add("text1");
        icoFecha.getStyleClass().add("iconosMenu");
        vBox.getStyleClass().add("tarMenu");

        VBox.setMargin(chart, new Insets(10,0,10,0));
        vBox.setPadding(new Insets(0,20,10,20));
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(100,200);
        internalBox.setPadding(new Insets(5,0,0,0));
        internalBox.setAlignment(Pos.CENTER);
        internalBox.getChildren().addAll(fecha, icono);
        vBox.getChildren().addAll(internalBox,chart, textIco);

        icono.setAlignment(Pos.CENTER_RIGHT);
        icono.setPrefSize(200, Region.USE_COMPUTED_SIZE);
        icono.getChildren().add(icoFecha);
        return vBox;
    }

}
