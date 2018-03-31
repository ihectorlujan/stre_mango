package com.controladores;

import com.controladores.compras.OpcionCompra;
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
    private Stage stage = new Stage();
    private BorderPane borderPane = new BorderPane();
    private Scene scene = new Scene(borderPane, 970,600);

    private HBox topBox = new HBox();
    private VBox leftBox = new VBox();
    private VBox centerBox = new VBox();

    private Text ico;
    private Text ico1;


    DashBoard() {
        topBox.getChildren().addAll(primerPanelTop(), segundoPanelTop());

        //Left Box
        leftBox.getStyleClass().add("panelWhite");
        leftBox.setPrefSize(190, Region.USE_COMPUTED_SIZE);
        leftBox.setPadding(new Insets(40,0,0,0));
        HBox opcionHome = opcionMenu(FontAwesomeIcon.CIRCLE_ALT_NOTCH, "Menu", 0);
        HBox opcionCompras = opcionMenu(FontAwesomeIcon.SHOPPING_CART, "Compras", 10);
        HBox opcionVentas = opcionMenu(FontAwesomeIcon.MONEY, "Ventas", 10);
        HBox opcionUsuarios = opcionMenu(FontAwesomeIcon.USERS, "Usuarios", 10);
        HBox opcionProveedores = opcionMenu(FontAwesomeIcon.USER_SECRET, "Proveedores", 10);
        HBox opcionEmpleados = opcionMenu(FontAwesomeIcon.MALE, "Empleados", 10);
        HBox opcionProductos = opcionMenu(FontAwesomeIcon.PRODUCT_HUNT, "Productos", 10);
        VBox masOpciones = new VBox();
        masOpciones.setAlignment(Pos.CENTER);
        VBox.setVgrow(masOpciones, Priority.ALWAYS);
        HBox ajustes = opcionMenu(FontAwesomeIcon.COG, "Ajustes", 0);
        HBox acerca = opcionMenu(FontAwesomeIcon.QUESTION_CIRCLE, "Acerca", 0);
        masOpciones.getChildren().addAll(ajustes,acerca);
        leftBox.getChildren().addAll(opcionHome, opcionCompras, opcionVentas, opcionUsuarios, opcionProveedores, opcionEmpleados, opcionProductos, masOpciones);

        //Center Box
        centerBox.setPadding(new Insets(20,20,20,20));
        HBox primerBox = primerPanelCenter();

        HBox eventosRecientes = new HBox();
        VBox eventosRecientesH = new VBox();
        VBox.setMargin(eventosRecientes, new Insets(0,20,20,20));

        //Forma cuadrados
        VBox ultimaCompra = actividadesRecientes(FontAwesomeIcon.SHOPPING_CART, "Ultima compra", "22/01/2018");
        VBox ultimaVenta = actividadesRecientes(FontAwesomeIcon.MONEY, "Ultima venta", "22/01/2018");
        VBox ultimoUsuario = actividadesRecientes(FontAwesomeIcon.USER, "Ultimo usuario", "22/01/2018");
        HBox.setHgrow(ultimaCompra, Priority.ALWAYS);
        HBox.setMargin(ultimaCompra, new Insets(20,20,20,20));
        HBox.setHgrow(ultimaVenta, Priority.ALWAYS);
        HBox.setMargin(ultimaVenta, new Insets(20,20,20,20));
        HBox.setHgrow(ultimoUsuario, Priority.ALWAYS);
        HBox.setMargin(ultimoUsuario, new Insets(20,20,20,20));
        eventosRecientes.getChildren().addAll(ultimaCompra, ultimaVenta, ultimoUsuario);

        //Forma Lista
        HBox ultimaCompraH = actividadesRecientesH(FontAwesomeIcon.SHOPPING_CART, "Ultima Compra", "22/10/2017");
        HBox ultimaVentaH = actividadesRecientesH(FontAwesomeIcon.MONEY, "Ultima venta", "22/01/2018");
        HBox ultimoUsuarioH = actividadesRecientesH(FontAwesomeIcon.USER, "Ultimo usuario", "22/01/2018");
        eventosRecientesH.getChildren().addAll(ultimaCompraH, ultimaVentaH, ultimoUsuarioH);

        HBox graficas = new HBox();
        VBox mejoresCompradores = graficasRecientes("Marzo", FontAwesomeIcon.CALENDAR, "Mejores compradores");
        VBox mejoresVentas = graficasRecientes("Marzo", FontAwesomeIcon.CALENDAR, "Mejores ventas");
        HBox.setMargin(mejoresCompradores, new Insets(20,20,20,20));
        HBox.setHgrow(mejoresCompradores, Priority.ALWAYS);
        HBox.setMargin(mejoresVentas, new Insets(20,20,20,20));
        HBox.setHgrow(mejoresVentas, Priority.ALWAYS);
        graficas.getChildren().addAll(mejoresCompradores, mejoresVentas);

        centerBox.getChildren().addAll(primerBox, eventosRecientes, graficas);

        //Eventos
        opcionHome.setOnMouseClicked(a -> {
            if(a.getButton() == MouseButton.PRIMARY)
                borderPane.setCenter(centerBox);
        });

        opcionCompras.setOnMouseClicked(a -> {
            if(a.getButton() == MouseButton.PRIMARY)
                borderPane.setCenter(new OpcionCompra());
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

        borderPane.setTop(topBox);
        borderPane.setLeft(leftBox);
        borderPane.setCenter(centerBox);

        scene.getStylesheets().add(getClass().getResource("/estilos/dashboard.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Menu principal");
        stage.show();
    }

    private HBox primerPanelTop() {
        HBox box = new HBox();
        box.setPadding(new Insets(20,20,20,20));
        box.setPrefSize(190, Region.USE_COMPUTED_SIZE);
        box.setMaxWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(box, Priority.NEVER);

        Text textChino = new Text("StreMango");

        Text ico = GlyphsDude.createIcon(FontAwesomeIcon.FIRE, "32");
        ico.getStyleClass().add("icoPrincipal");
        HBox.setMargin(textChino, new Insets(0,0,0,20));

        textChino.getStyleClass().add("textLogo");
        box.getChildren().addAll(ico, textChino);
        box.getStyleClass().add("panel1");
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private HBox segundoPanelTop() {
        HBox box = new HBox();
        HBox search = new HBox();
        HBox userInfo = new HBox();

        // Primer box
        JFXTextField txtSearch = new JFXTextField();
        Text ico = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH, "18");
        txtSearch.setPromptText("Buscar");
        txtSearch.setPadding(new Insets(0,10,0,5));
        search.setPrefSize(150, Region.USE_COMPUTED_SIZE);
        search.setMaxWidth(Region.USE_PREF_SIZE);
        search.getChildren().addAll(txtSearch, ico);

        //Segundo Box
        Text icoBell = GlyphsDude.createIcon(FontAwesomeIcon.BELL_ALT, "18");
        ImageView user = new ImageView("/imagenes/riki.jpg");
        Text icoDown = GlyphsDude.createIcon(FontAwesomeIcon.CHEVRON_DOWN, "16");
        Text txtUser = new Text("Riki");
        user.setFitWidth(32);
        user.setFitHeight(32);
        txtUser.getStyleClass().add("text1");
        HBox.setMargin(user, new Insets(0,20,0,20));
        HBox.setMargin(icoDown, new Insets(0,10,0,5));
        HBox.setHgrow(userInfo, Priority.ALWAYS);
        userInfo.getChildren().addAll(icoBell, user, txtUser, icoDown);


        search.setAlignment(Pos.CENTER_RIGHT);
        userInfo.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(box, Priority.ALWAYS);
        box.getStyleClass().add("panelWhite");
        box.getChildren().addAll(search, userInfo);

        return box;
    }

    private HBox opcionMenu(FontAwesomeIcon ico, String txt, int marginTop) {
        HBox box = new HBox();
        Text icoMenu = GlyphsDude.createIcon(ico, "1.5em");
        icoMenu.setWrappingWidth(20);
        Text text = new Text(txt);
        HBox.setMargin(box, new Insets(marginTop, 0, 0, 0));
        HBox.setMargin(text, new Insets(0,0,0,10));
        HBox.setMargin(icoMenu, new Insets(0,0,0,10));
        text.getStyleClass().add("text1");
        icoMenu.getStyleClass().add("iconosMenu");

        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10,0,10,0));
        box.setStyle("-fx-background-color: white;");
        box.getStyleClass().add("hover_border");
        box.getChildren().addAll(icoMenu, text);


        return box;
    }

    private HBox primerPanelCenter() {
        HBox box = new HBox();
        Text text = new Text("Chino Jr");
        HBox internalBox = new HBox();
        JFXButton button = new JFXButton("Estadisticas");

        //Internal Box
        ico = GlyphsDude.createIcon(FontAwesomeIcon.TH_LIST);
        ico1 = GlyphsDude.createIcon(FontAwesomeIcon.TH_LARGE);
        JFXTextField txtSearch = new JFXTextField();
        Text icoSearch = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH, "18");
        HBox.setMargin(ico, new Insets(0,10,0,0));
        txtSearch.setPromptText("Buscar");
        txtSearch.setPadding(new Insets(0,10,0,10));
        ico.getStyleClass().add("iconosMenu");
        ico1.getStyleClass().add("iconosMenu");

        button.getStyleClass().add("btnEstadistica");
        text.getStyleClass().add("textChino");

        internalBox.getChildren().addAll(ico, ico1, txtSearch, icoSearch);
        internalBox.setPadding(new Insets(5,5,5,5));
        internalBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setMargin(button, new Insets(0,0,0,10));
        HBox.setHgrow(internalBox, Priority.ALWAYS);
        box.getChildren().addAll(text, internalBox, button);

        return box;
    }

    private HBox actividadesRecientesH(FontAwesomeIcon icon, String text, String date) {
        HBox hBox = new HBox();

        Text icoFecha = GlyphsDude.createIcon(FontAwesomeIcon.CALENDAR_ALT, "1.5em");
        Text fecha = new Text(date);
        Text ico = GlyphsDude.createIcon(icon, "1.5em");
        Text textIco = new Text(text);
        fecha.getStyleClass().add("text1");
        textIco.getStyleClass().add("text1");
        ico.getStyleClass().add("iconosMenu");
        icoFecha.getStyleClass().add("iconosMenu");

        icoFecha.setWrappingWidth(30);
        fecha.setWrappingWidth(150);
        textIco.setWrappingWidth(120);
        ico.setWrappingWidth(20);

        VBox.setMargin(hBox, new Insets(10,50,10,50));

        hBox.setPrefSize(200,100);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(icoFecha, fecha, textIco, ico);
        hBox.getStyleClass().addAll("panelWhite", "tarMenu");
        return hBox;
    }


    private VBox actividadesRecientes(FontAwesomeIcon icon, String text, String date) {
        VBox vBox = new VBox();
        HBox internalBox = new HBox();
        HBox icono = new HBox();

        Text icoFecha = GlyphsDude.createIcon(FontAwesomeIcon.CALENDAR_ALT);
        Text fecha = new Text(date);
        Text ico = GlyphsDude.createIcon(icon);
        Text textIco = new Text(text);
        fecha.getStyleClass().add("text1");
        textIco.getStyleClass().add("text1");
        ico.getStyleClass().add("iconosGrande");
        icoFecha.getStyleClass().add("iconosMenu");

        VBox.setMargin(ico, new Insets(10,0,10,0));
        vBox.setPadding(new Insets(0,20,10,20));
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(100,200);
        vBox.getStyleClass().add("tarMenu");
        internalBox.getChildren().addAll(fecha, icono);
        vBox.getChildren().addAll(internalBox, ico, textIco);

        icono.setAlignment(Pos.CENTER_RIGHT);
        icono.setPrefSize(200, Region.USE_COMPUTED_SIZE);
        icono.getChildren().add(icoFecha);
        return vBox;
    }

    private VBox graficasRecientes(String mes, FontAwesomeIcon ico, String txt) {
        VBox vBox = new VBox();
        HBox internalBox = new HBox();
        HBox icono = new HBox();

        Text icoFecha = GlyphsDude.createIcon(FontAwesomeIcon.CALENDAR_ALT);
        Text fecha = new Text(mes);
        Text textIco = new Text(txt);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        fecha.getStyleClass().add("text1");
        textIco.getStyleClass().add("text1");
        icoFecha.getStyleClass().add("iconosMenu");

        VBox.setMargin(chart, new Insets(10,0,10,0));
        vBox.setPadding(new Insets(0,20,10,20));
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(100,200);
        vBox.getStyleClass().add("tarMenu");
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
