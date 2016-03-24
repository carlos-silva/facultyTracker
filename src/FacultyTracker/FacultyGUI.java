/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacultyTracker;

import javafx.scene.control.TextField;

/**
 *
 * @author Carlos
 */
public class FacultyGUI {
    
    public TextField Fname = new TextField();
    public TextField Lname = new TextField();
    public TextField Email = new TextField();
    public TextField Rank = new TextField();
    public TextField Salary = new TextField();
    public TextField Hours = new TextField();
    public TextField Office = new TextField();
    public TextField OfficeHours = new TextField();

    public TextField getFname() {
        return Fname;
    }

    public void setFname(TextField Fname) {
        this.Fname = Fname;
    }

    public TextField getLname() {
        return Lname;
    }

    public void setLname(TextField Lname) {
        this.Lname = Lname;
    }

    public TextField getEmail() {
        return Email;
    }

    public void setEmail(TextField Email) {
        this.Email = Email;
    }

    public TextField getRank() {
        return Rank;
    }

    public void setRank(TextField Rank) {
        this.Rank = Rank;
    }

    public TextField getSalary() {
        return Salary;
    }

    public void setSalary(TextField Salary) {
        this.Salary = Salary;
    }

    public TextField getHours() {
        return Hours;
    }

    public void setHours(TextField Hours) {
        this.Hours = Hours;
    }

    public TextField getOffice() {
        return Office;
    }

    public void setOffice(TextField Office) {
        this.Office = Office;
    }

    public TextField getOfficeHours() {
        return OfficeHours;
    }

    public void setOfficeHours(TextField OfficeHours) {
        this.OfficeHours = OfficeHours;
    }
  
}
