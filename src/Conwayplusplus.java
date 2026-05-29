import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;  
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import javafx.scene.input.KeyCode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter; 

public class Conwayplusplus extends Application {  
    ArrayList<ArrayList<Button>> cellDisplays;
    int sizeX;
    int sizeY;
    int cellsizeX;
    int cellsizeY;
   public static void main(String[] args) {
        launch(args);
    }
    public void makeConwayScreen(Stage stage){
        GridPane root = new GridPane();
        cellDisplays = new ArrayList<ArrayList<Button>>();
        for(int x=0;x<sizeY;x++){
            cellDisplays.add(new ArrayList<Button>());
            for(int y=0;y<sizeX;y++){
                Button c = new Button();
                c.setText(" ");
                c.setStyle("-fx-background-color: #000000;");
                c.setPrefSize(cellsizeX,cellsizeY);
                root.add(c,x,y);
                cellDisplays.get(x).add(c);
            }
        }
        stage.hide();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    public void start(Stage stage) {
        cellsizeX = 20;
        cellsizeY = 20;
        stage.setTitle("Conway++");
        GridPane root = new GridPane();
        File dataFile = new File(System.getenv("LOCALAPPDATA")+"/conwayplusplus/screensize.txt");
        if(dataFile.exists()){
            try{
            Scanner f = new Scanner(dataFile);
            sizeX = Integer.parseInt(f.nextLine());
            sizeY = Integer.parseInt(f.nextLine());
            stage.show();
            makeConwayScreen(stage);
            }
            catch(Exception e){
                System.out.println("Uhh we hate java.");
            }
        }
        else{
            sizeX = 0;
            sizeY = 0;
            GridPane second = new GridPane();
        Stage secondStage = new Stage();
        secondStage.setTitle("Instructions");
        Label l = new Label("Use the arrow keys to move the boxes to fit \nyour screen size, then press enter.");
        Button close = new Button("Close");
        second.add(l,0,0);
        second.add(close,0,1);
        close.setOnAction(event -> {
    secondStage.close();
});
        secondStage.setScene(new Scene(second));
            Button b1 = new Button(" ");
            b1.setPrefSize(cellsizeX,cellsizeY);
            b1.setStyle("-fx-background-color: #000000;");
            Button b2 = new Button(" ");
            b2.setStyle("-fx-background-color: #000000;");
            b2.setPrefSize(20,20);
            Button b3 = new Button(" ");
            b3.setStyle("-fx-background-color: #000000;");
            b3.setPrefSize(20,20);
            Button b4 = new Button(" ");
            b4.setStyle("-fx-background-color: #000000;");
            b4.setPrefSize(20,20);
            root.add(b1,0,0); //THIS ONE SHOULD NOT MOVE
            root.add(b2,0,0);
            root.add(b3,0,0);
            root.add(b4,0,0);
        root.setPadding(new Insets(10, 10, 10, 10)); 
        root.setVgap(20); 
        root.setHgap(20);
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if(root.getColumnIndex(b2)-1>1){
                root.setColumnIndex(b2,root.getColumnIndex(b2)-1);
                root.setColumnIndex(b3,root.getColumnIndex(b3)-1);
                sizeY--;
                }
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                root.setColumnIndex(b2,root.getColumnIndex(b2)+1);
                root.setColumnIndex(b3,root.getColumnIndex(b3)+1);
                sizeY++;
            }
            else if(event.getCode() == KeyCode.UP){
                if(root.getRowIndex(b2)-1>1){
                root.setRowIndex(b2,root.getRowIndex(b2)-1);
                root.setRowIndex(b4,root.getRowIndex(b4)-1);
                sizeX--;
                }
            }
            else if(event.getCode() == KeyCode.DOWN){
                root.setRowIndex(b2,root.getRowIndex(b2)+1);
                root.setRowIndex(b4,root.getRowIndex(b4)+1);
                sizeX++;
            }
            else if(event.getCode() == KeyCode.ENTER){
                sizeY+=2;
                sizeX-=5;
                try{
                Path path = Paths.get(System.getenv("LOCALAPPDATA")+"/conwayplusplus");
                Files.createDirectories(path);
                File screenSizeFile = new File(System.getenv("LOCALAPPDATA")+"/conwayplusplus/screensize.txt");
                screenSizeFile.createNewFile();
                FileWriter screenSize = new FileWriter(System.getenv("LOCALAPPDATA")+"/conwayplusplus/screensize.txt");
                screenSize.write(String.valueOf(sizeX)+"\n"+String.valueOf(sizeY));
                screenSize.close();
                }
                catch (Exception e){
                    System.out.println(e);
                }
                makeConwayScreen(stage);
            }
        });
        //root.setAlignment(Pos.CENTER); 
        stage.setScene(new Scene(root));
        root.setFocusTraversable(true);
        root.requestFocus();
        stage.setMaximized(true);
        stage.show();
        secondStage.show();
        }
    }
}