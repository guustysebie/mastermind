/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.UC1Controller;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import domein.DomeinController;
import java.util.Scanner;
import ui.Applicatie_UC1;

/**
 *
 * @author guust
 */
public class StartUpGui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DomeinController dc = new DomeinController();
        UC1Controller root = new UC1Controller(dc);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Mastermind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
/*
       System.out.println("gelieve te kiezen tussen de CLI(1) of de GUI(2) ");
        Scanner input = new Scanner(System.in);
         System.out.print("keuze: ");
        int keuze = input.nextInt();
       
        if (keuze == 1) {
            DomeinController dc = new DomeinController();
            Applicatie_UC1 app = new Applicatie_UC1(dc);
            app.startOp();

        }else if(keuze == 2){
  */       launch(args);
        
//        }

       
    }

}
