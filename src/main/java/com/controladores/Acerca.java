package com.controladores;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Acerca extends Stage {

    public Acerca() {
        final String msg = "Proyecto creado con fines educativos " +
                "por el equipo StreaMango para el local " +
                "de mototaxis ubicado en San Pablo Huitzo, " +
                "llamado Chino Jr.";

        BorderPane box = new BorderPane();
        TextArea txt = new TextArea(msg);
        JFXButton btnAceptar = new JFXButton();
        Text icoAceptar = GlyphsDude.createIcon(FontAwesomeIcon.CHECK);

        // Button properties and txt
        btnAceptar.setRipplerFill(Color.web("#90708c"));
        btnAceptar.getStyleClass().addAll("btnAceptar", "button-raised");
        btnAceptar.setGraphic(icoAceptar);
        btnAceptar.setStyle("-fx-pref-width: 90 !important;");
        icoAceptar.getStyleClass().add("ico");
        txt.setEditable(false);
        txt.getStyleClass().add("text");

        box.setPadding(new Insets(10));
        box.setCenter(txt);
        box.setBottom(btnAceptar);
        BorderPane.setAlignment(btnAceptar, Pos.CENTER);
        BorderPane.setMargin(btnAceptar, new Insets(10,0,3,0));

        btnAceptar.setOnAction(a -> this.close());

        // Stage properties
        Scene scene = new Scene(box,360,200);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        show();
        scene.getStylesheets().add(getClass().getResource("/estilos/acerca.css").toExternalForm());
    }
}
