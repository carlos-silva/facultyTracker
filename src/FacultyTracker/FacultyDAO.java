/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacultyTracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Carlos
 */
public class FacultyDAO {
    
    private Connection connection;
	private Statement statement;
      
	public FacultyDAO() { }

	public List<Faculty> getFacultyData() throws SQLException {
            
		String query = "SELECT * FROM faculty ORDER BY id";
		List<Faculty> list = new ArrayList<Faculty>();
		Faculty faculty = null;
		ResultSet rs = null;
		try {
			connection = MysqlConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
		
                        
                        while (rs.next()) {
                        
                            //faculty.setFacId(rs.getInt("id"));
                            faculty = new Faculty();
                                
                            faculty.setFacId(rs.getInt("id"));
                            faculty.setFname(rs.getString("fname"));
                            faculty.setLname(rs.getString("lname"));
                            faculty.setEmail(rs.getString("email"));
                            faculty.setRank(rs.getString("rank"));
                            faculty.setSalary(rs.getString("salary"));
                            faculty.setHours(rs.getString("hours"));
                            faculty.setOffice(rs.getString("office"));
                            faculty.setOfficeHours(rs.getString("officehours"));

                            
                            list.add(faculty);
                            
                        }
				
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
                return list;
		
	}
        
        public void AddFaculty(Faculty faculty) throws SQLException{
            
            String query = "INSERT INTO faculty"
                    + "(`fname`, `lname`, `email`, `rank`,"
                    + " `salary`, `hours`, `office`, `officehours`) VALUES ('"
                    + faculty.getFname() + "', '"
                    + faculty.getLname() + "', '"
                    + faculty.getEmail() + "', '"
                    + faculty.getRank()  + "', '"
                    + faculty.getSalary() + "', '"
                    + faculty.getHours() + "', '"
                    + faculty.getOffice() + "', '"
                    + faculty.getOfficeHours() + "')";
                  
       
		try {
			connection = MysqlConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
                   

        }finally {
			
			DBUtil.close(statement);
			DBUtil.close(connection);
		}


    }
        
        public void DeleteFaculty(Faculty faculty) throws SQLException{
            String query = "DELETE FROM faculty WHERE email = '" + faculty.getEmail() + "'";
            
            try {
			connection = MysqlConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);

        }finally {
			
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
        }
        
        public void EditFaculty(Faculty faculty) throws SQLException{
            System.out.println(faculty.getFacId());
            String query = "UPDATE faculty SET "
                         + "fname = '" + faculty.getFname() +"',"        
                         + "lname = '" + faculty.getLname() + "',"
                         + "email = '" + faculty.getEmail() + "',"
                         + "rank = '"  + faculty.getRank()  + "',"
                         + "salary = '" + faculty.getSalary() + "',"
                         + "hours = '" + faculty.getHours() + "',"
                         + "office = '"+ faculty.getOffice() + "',"
                         + "officehours = '"+ faculty.getOfficeHours() + "'"
                         + "WHERE id = '" + faculty.getFacId() +"'";
            
            try {
			connection = MysqlConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);

            }finally {
			
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
        }
        
        public Faculty searchFaculty(String search) throws SQLException{
            Faculty result = null;
            ResultSet rs = null;
            String query = "SELECT * FROM faculty WHERE lname LIKE '%" + search +"%' LIMIT 1";
            
            try {
			connection = MysqlConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
                        
                        while(rs.next()){
                        
                         result = new Faculty();
                         result.setFname(rs.getString("fname"));
                         result.setLname(rs.getString("lname"));
                         result.setEmail(rs.getString("email"));
                         result.setRank(rs.getString("rank"));
                         result.setSalary(rs.getString("salary"));
                         result.setHours(rs.getString("hours"));
                         result.setOffice(rs.getString("office"));
                         result.setOfficeHours(rs.getString("officehours"));
                        }
            }finally {
			
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
            
            return result;
        }
                
        
        
}
