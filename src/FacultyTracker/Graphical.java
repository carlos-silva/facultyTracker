/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacultyTracker;


import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 *
 * @author Carlos
 */
public class Graphical {
 
    PieChart pieChart = new PieChart();
    BarChart barChart;
    

    public PieChart getPieChart(){
        return pieChart;
    }
    
    public BarChart getBarChart(){
        return barChart;
    }
    
    public void setPieChart(List<Faculty> facultyList, String option){
        ArrayList<PieChart.Data> pieChart = new ArrayList<PieChart.Data>();
        
        
        double salary = 0;
        double totalSalary = 0;
        float percentage = 0;
        
        if(option ==  "Salary"){
            for(int i = 0; i < facultyList.size(); i++){
            totalSalary = totalSalary + Double.parseDouble(facultyList.get(i).getSalary());
            }
            
            for(int i = 0; i < facultyList.size(); i++){
            salary = Double.parseDouble(facultyList.get(i).getSalary());
            percentage =   Math.round((float)(salary / totalSalary) * 100);
            pieChart.add( new PieChart.Data(facultyList.get(i).getLname() 
                            +" "+  percentage + " %", salary) );
           
            }
            
       
           
        }else{ //default option
            for(int i = 0; i < facultyList.size(); i++){
            totalSalary = totalSalary + Double.parseDouble(facultyList.get(i).getHours());
            }
            
            for(int i = 0; i < facultyList.size(); i++){
            salary = Double.parseDouble(facultyList.get(i).getHours());
            percentage =   Math.round((float)(salary / totalSalary) * 100);
            pieChart.add( new PieChart.Data(facultyList.get(i).getLname() 
                            +" "+  percentage + " %", salary) );
           
            }
        }
        ObservableList<PieChart.Data> pieChartData =
                         FXCollections.observableArrayList(pieChart);
        this.pieChart = new PieChart(pieChartData);
        this.pieChart.setTitle(option);
        
        }
    
    public void setBarChart(List<Faculty> facultyList, String option){
       
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
       
        Series<String, Double> aSeries = new Series<String, Double>();
        ObservableList<XYChart.Series<String, Double>> barSeries = FXCollections.observableArrayList();
        
        double salary;
        double hours;
     
        if(option == "Salary"){
            for(int i = 0; i < facultyList.size(); i++){
                salary = Double.parseDouble(facultyList.get(i).getSalary());
                aSeries.getData().add(new XYChart.Data(facultyList.get(i).getLname(), salary));
            }
       
        }else{
              for(int i = 0; i < facultyList.size(); i++){
                salary = Double.parseDouble(facultyList.get(i).getHours());
                aSeries.getData().add(new XYChart.Data(facultyList.get(i).getLname(), salary));
            }
         }
        barSeries.add(aSeries);
        
        barChart = new BarChart(xAxis, yAxis);
        barChart.setTitle(option);
        barChart.setData( barSeries);
        
       
    }
    }
        
        
    
  
    

