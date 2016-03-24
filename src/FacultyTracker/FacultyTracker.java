/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacultyTracker;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class FacultyTracker extends Application {

    //pagination variable
    public static int page = 0;
    
    //instance variables
    FacultyDAO dao = new FacultyDAO(); //Faculty Data Access Object
    FacultyGUI textfield = new FacultyGUI(); //Faculty Variables Handler
    Graphical graph = new Graphical(); //Graphics handler
    MessageManager messageControl = new MessageManager(); 
  
  
    public void setPage(int page){
        this.page = page;
    }
    
    public int getPage(){
        return page;
    }
    
    //get data of textfields
    private Faculty getCurrentFaculty(){
        Faculty faculty = new Faculty();
        
        faculty.setFname(textfield.getFname().getText());
        faculty.setLname(textfield.getLname().getText());     
        faculty.setEmail(textfield.getEmail().getText());
        faculty.setRank(textfield.getRank().getText());
        faculty.setSalary(textfield.getSalary().getText());
        faculty.setHours(textfield.getHours().getText());
        faculty.setOffice(textfield.getOffice().getText());
        faculty.setOfficeHours(textfield.getOfficeHours().getText());
        
        return faculty;
    }
    
    //renew grid
    private void renew(){
        //List<Faculty> facultyList = getFacultyList();
        setPage(0);
        populateFields(getPage());
    }

    public  List<Faculty> getFacultyList() {
        List<Faculty> facultyList = null;
	FacultyDAO empDao = new FacultyDAO();
 
		try {
			facultyList = empDao.getFacultyData();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
                return facultyList;
	}
    
    
    private  void populateFields(int page) {
      
        List<Faculty> facultyList = getFacultyList();
        Faculty faculty = facultyList.get(page);

        textfield.getFname().setText(faculty.getFname());
        textfield.getLname().setText(faculty.getLname());
        textfield.getEmail().setText(faculty.getEmail());
        textfield.getRank().setText(faculty.getRank());
        textfield.getSalary().setText(faculty.getSalary());
        textfield.getHours().setText(faculty.getHours());
        textfield.getOffice().setText(faculty.getOffice());
        textfield.getOfficeHours().setText(faculty.getOfficeHours());
            
		
	}
    
    private void deleteFaculty() throws SQLException{
        //create a faculty object and store the data from the textfields
        Faculty faculty = getCurrentFaculty();
        //get current faculty data and send it to DAO delete method
        dao.DeleteFaculty(faculty);
        //refresh faculty data list and repopulate textfields
        renew();
    }
    
    private void addFaculty() throws SQLException{
        Faculty faculty = getCurrentFaculty();
        dao.AddFaculty(faculty);
        renew();
    }
    
    private void saveFaculty() throws SQLException{
        Faculty faculty = getFacultyList().get(getPage());
        
        //Synchronize Faculty Values with the ones in textfield
        faculty.setFname(textfield.getFname().getText());
        faculty.setLname(textfield.getLname().getText());     
        faculty.setEmail(textfield.getEmail().getText());
        faculty.setRank(textfield.getRank().getText());
        faculty.setSalary(textfield.getSalary().getText());
        faculty.setHours(textfield.getHours().getText());
        faculty.setOffice(textfield.getOffice().getText());
        faculty.setOfficeHours(textfield.getOfficeHours().getText());
        
        dao.EditFaculty(faculty);
   
        
    }
    
    private void searchFaculty(String search) throws SQLException{
        Faculty result = dao.searchFaculty(search);
         textfield.getFname().setText(result.getFname());
         textfield.getLname().setText(result.getLname());
         textfield.getEmail().setText(result.getEmail());
         textfield.getRank().setText(result.getRank());
         textfield.getSalary().setText(result.getSalary());
         textfield.getHours().setText(result.getHours());
         textfield.getOffice().setText(result.getOffice());
         textfield.getOfficeHours().setText(result.getOfficeHours());
        
    }
    
    private GridPane CenterPanel() throws IOException {
        
        GridPane grid = new GridPane();
        getFacultyList();
        
        Label name = new Label("Name:");
        grid.add(name, 0, 1);
        Label email = new Label("Email:");
        grid.add(email, 0, 2);
        Label rank = new Label("Rank:");
        grid.add(rank, 0, 3);
        Label salary = new Label("Salary:");
        grid.add(salary, 0, 4);
        Label hours = new Label("Sem. Credit Hours:");
        grid.add(hours, 0, 5);
        Label office = new Label("Office:");
        grid.add(office, 0, 6);
        Label officeHours = new Label("Office Hours:");
        grid.add(officeHours, 0, 7);
        
        
        
        grid.add(textfield.getFname(), 1, 1);
        grid.add(textfield.getLname(), 2, 1);
        grid.add(textfield.getEmail(), 1, 2);
        grid.add(textfield.getRank(), 1, 3);
        grid.add(textfield.getSalary(), 1, 4);
        grid.add(textfield.getHours(), 1, 5);
        grid.add(textfield.getOffice(), 1, 6);
        grid.add(textfield.getOfficeHours(), 1, 7);
        
        
        populateFields(getPage());
        
        return grid;
        
        
    }
    
    private HBox BottomPanel() {
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(10));
    hbox.setSpacing(50);
    
    Button first = new Button();
    first.setText("<<");
    first.setOnAction(e ->{
                setPage(0);
                populateFields(getPage());
    });
    
    Button previous = new Button();
    previous.setText("<");
    previous.setOnAction(e ->{
                if(getPage() > 0){
                setPage(getPage() - 1);                              
                populateFields(getPage());
                }
               
    });
    
    Button next = new Button();
    next.setText(">");
    next.setOnAction(e ->{
        
                if(page < getFacultyList().size() - 1){
                setPage(getPage() + 1);                              
                populateFields(getPage());}
                
    });
    
    Button last = new Button();
    last.setText(">>");
    last.setOnAction(e ->{
        
                setPage(getFacultyList().size() - 1);
                populateFields(getPage());
    });
    
 
    
    
    
    hbox.getChildren().addAll(first, previous, next, last);

    



    return hbox;
}
    
    private VBox MenuPanel(Stage stage){
         VBox vbox = new VBox();
         Image image = new Image("logo.png");
 
      
         ImageView iv1 = new ImageView();
         iv1.setImage(image);
         iv1.setFitWidth(200);
         iv1.setFitHeight(80);
         
       
        Menu fileMenu = new Menu("File");
        MenuItem save = new MenuItem("Save");
            save.setOnAction(e -> {
            try {
                saveFaculty();
            } catch (SQLException ex) {
                Logger.getLogger(FacultyTracker.class.getName()).log(Level.SEVERE, null, ex);
            }
                        });
     
                
            MenuItem close = new MenuItem("close");
            close.setOnAction(e -> {
                
            System.exit(0);
            });
            
        fileMenu.getItems().add(save);    
        //fileMenu.getItems().add(saveAs);
        fileMenu.getItems().add(close);
       
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        
        vbox.getChildren().addAll(menuBar, iv1);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
    
    private HBox RightPanel(){
        
        HBox hbox = new HBox();

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("Salary");
        comboBox.getItems().add("Hours");
   
        comboBox.setOnAction(e -> {
            
            if(hbox.getChildren().contains(graph.getPieChart())){
                graph.setPieChart(getFacultyList(), (String) comboBox.getValue());
                hbox.getChildren().set(0, graph.getPieChart());
            }else{ //Thus, BarChart type
                graph.setBarChart(getFacultyList(), (String) comboBox.getValue());
                 hbox.getChildren().set(0, graph.getBarChart());   
            }
        });

        Button pieButton = new Button();
        pieButton.setText("Pie Chart");
        pieButton.setOnAction(e ->{
            graph.setPieChart(getFacultyList(), (String) comboBox.getValue());
            hbox.getChildren().set(0, graph.getPieChart());
        });
    
       Button barButton = new Button();
       barButton.setText("Bar Chart");
       barButton.setOnAction(e ->{
           graph.setBarChart(getFacultyList(), (String) comboBox.getValue());
           hbox.getChildren().set(0, graph.getBarChart());    
    });

     hbox.getChildren().addAll(graph.getPieChart(), comboBox, pieButton, barButton);

       return hbox;
}
    
    private VBox LeftPanel() {
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
    
    Button add = new Button();
    add.setText("Add");
    add.setOnAction(e ->{
        try {
            messageControl.display("Faculty Successfully added", "Faculty Added");
            addFaculty();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    
    Button search = new Button();
    search.setText("Find");
    search.setOnAction(e ->{
        TextInputDialog dialog = new TextInputDialog("Search by Last Name");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        try {
            searchFaculty(result.get());
        } catch (SQLException ex) {
            Logger.getLogger(FacultyTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        } else{
            
        }
                
    });
    
    Button delete = new Button();
    delete.setText("Delete");
    delete.setOnAction(e ->{
       try {
           if(messageControl.confirmation())
                deleteFaculty();
       } catch (SQLException ex) {
            Logger.getLogger(FacultyTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    });
    

    vbox.getChildren().addAll(add, search, delete);
    return vbox;
}
    

 @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane border = new BorderPane();
        Stage window = primaryStage;
        
        border.setTop(MenuPanel(window));
        border.setCenter(CenterPanel());
        border.setRight(RightPanel());
        border.setLeft(LeftPanel());
        border.setBottom(BottomPanel());

        Scene scene = new Scene(border);
        window.setTitle("Faculty Data");
        window.setScene(scene);

        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
