/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package pp3.st;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import pp3.st.vistas.DashboardController;
import pp3.st.vistas.GestionController;
import pp3.st.vistas.LoginController;
import pp3.st.vistas.ModalUsuariosController;
import pp3.st.vistas.PDFController;
import pp3.st.vistas.PrincipalController;

/**
 *
 * @author Matyas
 */
public class Main extends Application {

    private Stage stage;
//    Stage stageLogin;
    private BorderPane rootLayout;
    private AnchorPane regCliente;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        verLogin();
    }

    public void verLogin() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/Login.fxml"));
            AnchorPane login;
            login = loader.load();

            Scene scene = new Scene(login);
            LoginController lc = loader.getController();
            lc.setMain(this);

            stage.setScene(scene);

//            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Gestión Servicio Técnico");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarModalUsuarios() {
        try {

            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/ModalUsuarios.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage cumple = new Stage();
            cumple.setTitle("Gestionar usuarios");
            cumple.initModality(Modality.WINDOW_MODAL);
            cumple.initOwner(stage.getOwner());
            Scene scene = new Scene(page);
            cumple.setScene(scene);

            ModalUsuariosController controller = loader.getController();

            controller.setMain(this);

            cumple.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarClientesBaja() {
        try {

            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/ModalUsuarios.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage cumple = new Stage();
            cumple.setTitle("Gestionar usuarios");
            cumple.initModality(Modality.WINDOW_MODAL);
            cumple.initOwner(stage.getOwner());
            Scene scene = new Scene(page);
            cumple.setScene(scene);

            ModalUsuariosController controller = loader.getController();

            controller.setMain(this);

            cumple.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarCargarPDF() {

        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/PDF.fxml"));
            StackPane page = loader.load();
            Stage pdf = new Stage();
            pdf.setTitle("Ver PDF");
//            pdf.initModality(Modality.WINDOW_MODAL);
            pdf.initOwner(stage.getOwner());
            Scene scene = new Scene(page);
            pdf.setScene(scene);

            PDFController controller = loader.getController();

            controller.setMain(this);

            pdf.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void verVistaPrincipal() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader
                .setLocation(Main.class
                        .getResource("vistas/Principal.fxml"));
        rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        PrincipalController pc = loader.getController();
        pc.setMain(this);
        stage.setResizable(true);
        stage.setHeight(720);
        stage.setWidth(1200);

        stage.setScene(scene);
        stage.show();

        centrar_scena_gestion();

    }

    public void centrar_scena_gestion() {
//        this.stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader
                .setLocation(Main.class
                        .getResource("vistas/Gestion.fxml"));
        try {
            regCliente = (AnchorPane) loader.load();
            GestionController c = loader.getController();
            c.setMain(this);
            rootLayout.setCenter(regCliente);

        } catch (IOException ex) {
            System.err.println("Error al cargar rootLayout" + ex.getMessage());
        }

    }

    public void centrar_scena_dashboard() {
//        this.stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader
                .setLocation(Main.class
                        .getResource("vistas/Dashboard.fxml"));
        try {
            regCliente = (AnchorPane) loader.load();
            DashboardController c = loader.getController();
            c.setMain(this);
            rootLayout.setCenter(regCliente);

        } catch (IOException ex) {
            System.err.println("Error al cargar rootLayout" + ex.getMessage());
        }

    }

}
