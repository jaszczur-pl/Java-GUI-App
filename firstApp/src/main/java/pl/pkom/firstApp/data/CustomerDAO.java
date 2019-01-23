package pl.pkom.firstApp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO{
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/customers";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";
    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final int SEL_NAME_POSITION = 1;
    private final int SEL_NAME_POSITION_OR = 2;
    private final int SEL_SURNAME_POSITION = 3;
    private final int SEL_SURNAME_POSITION_OR = 4;
    private final int SEL_COUNTRY_POSITION = 5;
    private final int SEL_COUNTRY_POSITION_OR = 6;
    private final int SEL_ADDRESS_POSITION = 7;
    private final int SEL_ADDRESS_POSITION_OR = 8;
    private final int SEL_AGE_POSITION = 9;
    private final int SEL_AGE_POSITION_OR = 10;
    private final int INS_UPD_NAME_POSITION = 1;
    private final int INS_UPD_SURNAME_POSITION = 2;
    private final int INS_UPD_COUNTRY_POSITION = 3;
    private final int INS_UPD_ADDRESS_POSITION = 4;
    private final int INS_UPD_AGE_POSITION = 5;
    private final int UPD_ID_POSITION = 6;
    private final int DEL_ID_POSITION = 1;
    
    private Connection conn;
    private PreparedStatement selectStatement;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    
    public void init() {
        
        try {
            
            Class.forName(DB_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet selectDAO(final String name, final String surname, final String country, final String address, final String age) throws SQLException {
       
        ResultSet resultSet = null;
        
        selectStatement = conn.prepareStatement("SELECT * FROM customers WHERE " +
                                                    "(Name = ? OR ? = '') " + 
                                                    "AND (Surname = ? OR ? = '') " +
                                                    "AND (Country = ? OR ? = '') " +
                                                    "AND (Adres = ? OR ? = '') " +
                                                    "AND (Age = ? OR ? = '')");
        try {
            selectStatement.setString(SEL_NAME_POSITION, name);
            selectStatement.setString(SEL_NAME_POSITION_OR, name);
            selectStatement.setString(SEL_SURNAME_POSITION, surname);
            selectStatement.setString(SEL_SURNAME_POSITION_OR, surname);
            selectStatement.setString(SEL_COUNTRY_POSITION, country);
            selectStatement.setString(SEL_COUNTRY_POSITION_OR, country); 
            selectStatement.setString(SEL_ADDRESS_POSITION, address);
            selectStatement.setString(SEL_ADDRESS_POSITION_OR, address);
            selectStatement.setString(SEL_AGE_POSITION, age);
            selectStatement.setString(SEL_AGE_POSITION_OR, age); 
            
            resultSet = selectStatement.executeQuery();
            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return resultSet;
    }
    
    public int insertDAO(final String name, final String surname, final String country, final String address, final String age) throws SQLException {
        
        int result = java.sql.Types.INTEGER;
        insertStatement = conn.prepareStatement("INSERT INTO customers (Name, Surname, Country, Adres, Age) VALUES(?,?,?,?,?)");

        try {
            conn.setAutoCommit(false);
            
            insertStatement.setString(INS_UPD_NAME_POSITION, name);
            insertStatement.setString(INS_UPD_SURNAME_POSITION, surname);
            insertStatement.setString(INS_UPD_COUNTRY_POSITION, country);
            insertStatement.setString(INS_UPD_ADDRESS_POSITION, address);
            insertStatement.setString(INS_UPD_AGE_POSITION, age);

            result = insertStatement.executeUpdate();
            
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            if (insertStatement != null) {
                insertStatement.close();
            }
            
            conn.setAutoCommit(true);
        }
        
        return result;
    }
    
    public int updateDAO(int ID, final String name, final String surname, final String country, final String address, final String age) throws SQLException {
        
        int result = java.sql.Types.INTEGER;
        updateStatement = conn.prepareStatement("UPDATE customers SET " +
                                                       "Name = ?, " +
                                                       "Surname = ?, " +
                                                       "Country = ?, " +
                                                       "Adres = ?, " +
                                                       "Age = ? " +
                                                "WHERE ID = ?;");
        
        try {
            conn.setAutoCommit(false);
            
            updateStatement.setString(INS_UPD_NAME_POSITION, name);
            updateStatement.setString(INS_UPD_SURNAME_POSITION, surname);
            updateStatement.setString(INS_UPD_COUNTRY_POSITION, country);
            updateStatement.setString(INS_UPD_ADDRESS_POSITION, address);
            updateStatement.setString(INS_UPD_AGE_POSITION, age);
            updateStatement.setInt(UPD_ID_POSITION, ID);

            result = updateStatement.executeUpdate();
            
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            if (updateStatement != null) {
                updateStatement.close();
            }
            
            conn.setAutoCommit(true);
        }

        return result;
    }
    
    public int deleteDAO(int ID) throws SQLException {
        
        int result = java.sql.Types.INTEGER;
        deleteStatement = conn.prepareStatement("DELETE FROM customers WHERE ID = ?");
                                                    
        try {
            conn.setAutoCommit(false);
            
            deleteStatement.setInt(DEL_ID_POSITION, ID);
            
            result = deleteStatement.executeUpdate();
            
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            if (deleteStatement != null) {
                deleteStatement.close();
            }
            
            conn.setAutoCommit(true);
        }
        
        return result;
    }
    
    
    public void closeConnection() {
        if (conn != null) {
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
                conn.close();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }
    
}


    






