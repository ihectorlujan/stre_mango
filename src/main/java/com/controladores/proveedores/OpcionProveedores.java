package com.controladores.proveedores;


import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.modelo.Proveedor;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OpcionProveedores extends VBox {
    HBox boxTitle;
    SplitPane split,split2;
    ScrollPane scroll;
    Insets defaultInsets=new Insets(5,10,10,10);
    Insets internalInsets=new Insets(2,5,5,5);
    List<Proveedor> proveedores;
    int total;
    public OpcionProveedores(){

        this.setPadding(defaultInsets);
        this.setSpacing(5);

        boxTitle=new HBox();
        initBoxTitle();

        initiSplit();

        initiSplit2();

        this.getChildren().addAll(boxTitle,split,split2);
    }


    private void initBoxTitle(){

        Text title=new Text("Provedores");
        boxTitle.setAlignment(Pos.CENTER);
        VBox.setVgrow(boxTitle,Priority.SOMETIMES);
        boxTitle.getChildren().addAll(title);
    }

    private void initiSplit(){
        BorderPane splitIzq=new BorderPane();

        //lado izquierdo
        HBox menu=new HBox();
        menu.setAlignment(Pos.CENTER);
        Text opciones=new Text("Opciones");
        menu.getChildren().add(opciones);
        splitIzq.setTop(menu);

        splitIzq.setPrefSize(USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
        splitIzq.setMaxWidth(280);
        VBox principal=new VBox();
        HBox boxAdd=new HBox();
        boxAdd.setPadding(internalInsets);
        boxAdd.setAlignment(Pos.CENTER);
        boxAdd.setSpacing(10);
        HBox.setHgrow(boxAdd,Priority.ALWAYS);
        Text icoProov=GlyphsDude.createIcon(FontAwesomeIcon.USER_SECRET,"60");
        Button btnAdd=GlyphsDude.createIconButton(FontAwesomeIcon.PLUS,"Agregar proovedor","18","14",ContentDisplay.LEFT);
        btnAdd.setTextAlignment(TextAlignment.LEFT);
        boxAdd.getChildren().addAll(btnAdd,icoProov);

        HBox boxEdit=new HBox();
        HBox.setHgrow(boxEdit,Priority.ALWAYS);
        boxEdit.setPadding(internalInsets);
        Button btnEdit=GlyphsDude.createIconButton(FontAwesomeIcon.PENCIL,"Editar Proveedor","18","14",ContentDisplay.LEFT);
        Text iconEdit=GlyphsDude.createIcon(FontAwesomeIcon.EDIT,"60");
        boxEdit.setAlignment(Pos.CENTER);
        boxEdit.setSpacing(10);
        boxEdit.getChildren().addAll(btnEdit,iconEdit);

        HBox boxDel=new HBox();
        boxDel.setPadding(internalInsets);
        boxDel.setAlignment(Pos.CENTER);
        boxDel.setPadding(internalInsets);
        boxDel.setSpacing(10);
        HBox.setHgrow(boxDel,Priority.ALWAYS);
        Button btnDel=GlyphsDude.createIconButton(FontAwesomeIcon.TRASH_ALT,"Eliminar Proovedor","18","14",ContentDisplay.LEFT);
        Text icoDel=GlyphsDude.createIcon(FontAwesomeIcon.TIMES_CIRCLE_ALT,"60");
        boxDel.getChildren().addAll(btnDel,icoDel);

        principal.setPadding(internalInsets);
        principal.setAlignment(Pos.TOP_CENTER);
        principal.getChildren().addAll(boxAdd,boxEdit,boxDel);
        splitIzq.setCenter(principal);



        //lado derecho
        TableView<Proveedor> provTable=new TableView<>();
        provTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Proveedor,String> colNombre=new TableColumn<>("Razón social");
        colNombre.setCellValueFactory(c -> c.getValue().razonProperty());

        TableColumn<Proveedor,String> colId=new TableColumn<>("ID");
        colId.setCellValueFactory(c -> c.getValue().idProperty());
        colId.setMaxWidth(70);
        colId.setMinWidth(60);

        TableColumn<Proveedor,String> colEmail=new TableColumn<>("Email");
        colEmail.setCellValueFactory(c -> c.getValue().emailProperty());

        TableColumn<Proveedor,String> colTel=new TableColumn<>("Teléfono");
        colTel.setCellValueFactory(c -> c.getValue().phoneProperty());

        provTable.getColumns().add(colNombre);
        provTable.getColumns().add(colId);
        provTable.getColumns().add(colEmail);
        provTable.getColumns().add(colTel);

        FilteredList<Proveedor> filteredList=new FilteredList<>(InitExamples(),p -> true);
        SortedList<Proveedor> sortedList=new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(provTable.comparatorProperty());

        provTable.setItems(sortedList);

        JFXTextField searchField=new JFXTextField();
        searchField.setPromptText("Buscar en todos los proveedores");
        searchField.textProperty().addListener((prop,old,text)->{
            filteredList.setPredicate(proveedor->{
                if(text==null||text.isEmpty())
                    return true;
                String name=proveedor.getRazon().toLowerCase();
                return name.contains(text.toLowerCase());
            } );
        });

        VBox splitDer=new VBox();
        splitDer.setPrefSize(200,150);
        splitDer.setMinSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        splitDer.setPadding(defaultInsets);
        HBox searchBox=new HBox();
        Text searchIco= GlyphsDude.createIcon(FontAwesomeIcon.SEARCH);
        HBox.setHgrow(searchField,Priority.ALWAYS);
        searchBox.getChildren().addAll(searchField,searchIco);
        VBox.setVgrow(provTable,Priority.ALWAYS);
        searchBox.setPadding(new Insets(0,10,10,10));
        HBox.setHgrow(searchBox,Priority.ALWAYS);
        splitDer.getChildren().addAll(searchBox,provTable);


        split=new SplitPane(splitIzq,splitDer);
        split.setOrientation(Orientation.HORIZONTAL);
        split.setDividerPositions(0.4);
        VBox.setVgrow(split,Priority.ALWAYS);
        HBox.setHgrow(split,Priority.ALWAYS);
        split.setMaxHeight(260);

        HBox totalesBox=new HBox();
        totalesBox.setAlignment(Pos.CENTER);
        totalesBox.setPadding(internalInsets);
        Text totales=new Text("Total Proveedores: "+proveedores.size()+".");
        totalesBox.getChildren().add(totales);
        splitIzq.setBottom(totalesBox);

    }

    public ObservableList<Proveedor> InitExamples(){
        proveedores=new ArrayList<>();
        /* WWWTTTTFFFFF¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡
        String[] nombres=new String[10];
        nombres[0]="Potter";nombres[0]="Pedro";nombres[0]="Morgan";nombres[0]="Cejotas";nombres[0]="Peke";nombres[0]="Alejandra";
        nombres[0]="Zoila";nombres[0]="Bruno";nombres[0]="Ospeck";nombres[0]="César";
        for(String i:nombres){proveedores.add(new Proveedor(i,"P2231M","Empresa.SA de CV.","exaple@outloock.com",3));}
        */

        proveedores.add(new Proveedor("Alejandra´s Shop","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Peke´s.INC","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("GaiSenseiParts","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Cejotas Machines","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Motto Surtidoras","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Mauricia´s shape","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Filemona parts","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Ospeck Bauer´s","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Morgan Filtrer","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));
        proveedores.add(new Proveedor("Zoila-Zerda","P2231M","Empresa.SA de CV.","exaple@outloock.com","9518646527"));

        return FXCollections.observableArrayList(proveedores);
    }

    private void initiSplit2(){
        HBox principal=new HBox();

        HBox titleBox=new HBox();
        titleBox.setAlignment(Pos.CENTER);
        Text title =new Text("Información detallada");
        titleBox.getChildren().add(title);
        titleBox.setMaxHeight(15);

        VBox box1=new VBox();
        box1.setPadding(internalInsets);
        box1.setAlignment(Pos.CENTER_LEFT);
        ImageView imageView=new ImageView("/imagenes/default_provider.png");
        imageView.isResizable();
        Button change=GlyphsDude.createIconButton(FontAwesomeIcon.FOLDER_OPEN_ALT,"Cambiar imagen");
        BorderPane spacePick=new BorderPane();
        VBox.setVgrow(spacePick,Priority.ALWAYS);
        VBox.setVgrow(imageView,Priority.ALWAYS);
        HBox botonBox=new HBox();
        botonBox.setAlignment(Pos.CENTER);
        botonBox.getChildren().add(change);
        spacePick.setCenter(imageView);
        spacePick.setBottom(botonBox);
        box1.setSpacing(5);
        JSeparator separator=new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        box1.getChildren().add(spacePick);



        VBox box2=new VBox();
        box2.setPadding(internalInsets);
        HBox.setHgrow(box2,Priority.ALWAYS);

        HBox razonBox=new HBox();
        VBox.setVgrow(razonBox,Priority.ALWAYS);
        razonBox.setAlignment(Pos.CENTER_LEFT);
        Text razon =new Text("Razón social: ");
        JFXTextField razon1=new JFXTextField("NOMBRE RESPECTIVO");
        razon.setDisable(true);
        razonBox.getChildren().addAll(razon,razon1);

        HBox idBox=new HBox();
        VBox.setVgrow(idBox,Priority.ALWAYS);
        razonBox.setAlignment(Pos.CENTER_LEFT);
        Text id =new Text("ID:  ");
        JFXTextField id1=new JFXTextField("NP2231M");
        id1.setDisable(true);
        idBox.getChildren().addAll(id,id1);

        HBox telBox=new HBox();
        VBox.setVgrow(telBox,Priority.ALWAYS);
        telBox.setAlignment(Pos.CENTER_LEFT);
        Text tel =new Text("Teléfono:  ");
        JFXTextField tel1=new JFXTextField("9647654234");
        tel1.setDisable(true);
        telBox.getChildren().addAll(tel,tel1);

        HBox emailBox=new HBox();
        VBox.setVgrow(emailBox,Priority.ALWAYS);
        emailBox.setAlignment(Pos.CENTER_LEFT);
        Text email =new Text("Correo elecrónico: ");
        JFXTextField email1=new JFXTextField("example@outloock.com");
        email1.setDisable(true);
        emailBox.getChildren().addAll(email,email1);

        box2.getChildren().addAll(razonBox,idBox,telBox,emailBox);

        VBox box3=new VBox();
        box3.setPadding(internalInsets);
        HBox.setHgrow(box3,Priority.ALWAYS);
        HBox detallesBox=new HBox();
        detallesBox.setAlignment(Pos.CENTER);
        Text detallesText=new Text("Comentarios");
        detallesBox.getChildren().add(detallesText);
        HBox detallesBoxArea=new HBox();
        detallesBoxArea.setAlignment(Pos.CENTER);
        VBox.setVgrow(detallesBoxArea,Priority.ALWAYS);
        JFXTextArea detallesArea= new JFXTextArea("Recibirá un correo electrónico comunicándole que un pago se está sometiendo" +
                " a Revisión de pagos y otro cuando se haya completado la revisión. También podrá encontrar esta" +
                " información en la página Detalles de la transacción. Si utiliza la Notificación de pago " +
                "instantánea de PayPal (IPN, Instant Payment Notification) ");
        detallesBoxArea.getChildren().add(detallesArea);
        box3.getChildren().addAll(detallesBox,detallesBoxArea);

        SplitPane info=new SplitPane(box2,box3);
        HBox.setHgrow(info,Priority.ALWAYS);
        info.setOrientation(Orientation.HORIZONTAL);

        principal.setSpacing(10);
        principal.getChildren().addAll(box1,info);
        split2=new SplitPane(titleBox,principal);
        split2.setPadding(defaultInsets);
        split2.setOrientation(Orientation.VERTICAL);
        VBox.setVgrow(split2,Priority.ALWAYS);

    }
}