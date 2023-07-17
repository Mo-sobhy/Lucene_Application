package com.tutorialspoint.lucene;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LuceneTester extends Application  {
   
public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("search_page.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Search Engine");
        stage.setResizable(false);
        stage.show();
    }
   public static void main(String[] args) {
       launch(args);

   }



}